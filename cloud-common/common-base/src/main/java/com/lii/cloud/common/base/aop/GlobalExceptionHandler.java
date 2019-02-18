package com.lii.cloud.common.base.aop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lii.cloud.common.base.core.exception.RentException;
import com.lii.cloud.common.base.core.mongo.BrowserEntity;
import com.lii.cloud.common.base.core.mongo.ExceptionEntity;
import com.lii.cloud.common.base.core.mongo.OperatingSystemEntity;
import com.lii.cloud.common.base.tools.IPUtil;
import com.lii.cloud.common.tools.result.ResponseInfo;
import com.lii.cloud.common.tools.result.ResultBody;
import com.lii.cloud.common.tools.result.extend.BuessExceptions;
import com.lii.cloud.common.tools.result.extend.ExceptionResponseInfo;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

/**
 * 统一异常处理
 */
//表示 GlobalExceptionHandler 是一个全局的异常处理器.
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
	private MongoTemplate template;
    
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	System.out.println("执行绑定数据......................");
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        format.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody handle(Exception e,HttpServletRequest request,HttpServletResponse response) {
    	ExceptionEntity entity = new ExceptionEntity();
    	entity.setIp(IPUtil.getIPAddress(request));
    	System.out.println("异常处理............");
    	System.out.println("路径="+request.getRequestURI());
    	System.out.println("方法："+request.getMethod());
    	entity.setPath(request.getRequestURI());
    	entity.setMethod(request.getMethod());
    	System.out.println("...............header.............");
    	HashMap<String, Object> headers = new HashMap<String, Object>();
    	Enumeration<String> headEnums = request.getHeaderNames();
    	for (Enumeration<String> en = headEnums ; en.hasMoreElements();) {
    		String thisName=en.nextElement().toString();
    		Object thisValue=request.getHeader(thisName);
    		headers.put(thisName, thisValue);
    	} 
    	entity.setHeaders(headers);
    	
    	UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        Browser browser = agent.getBrowser();
        OperatingSystem system = agent.getOperatingSystem();
        BrowserEntity browserEntity = new BrowserEntity();
        browserEntity.setManufacturer(browser.getManufacturer().getName());
        browserEntity.setName(browser.getName());
        browserEntity.setType(browser.getBrowserType().getName());
        Version version = agent.getBrowserVersion();
        if(null != version){
        	browserEntity.setVersion(version.getVersion());
        }
        entity.setBrowser(browserEntity);
        OperatingSystemEntity sys = new OperatingSystemEntity();
        sys.setName(system.getName());
        sys.setDeviceType(system.getDeviceType().getName());
        entity.setSystem(sys);
        
    	System.out.println("...............parmeter.............");
    	HashMap<String, Object> parameters = new HashMap<String, Object>();
    	Enumeration<String> enums = request.getParameterNames();
    	for (Enumeration<String> en = enums;en.hasMoreElements();) {
    		String thisName=en.nextElement().toString();
    		Object [] thisValue=request.getParameterValues(thisName);
    		if(thisValue.length == 1){
    			parameters.put(thisName, thisValue[0]);
    		}
    		if(thisValue.length > 1){
    			parameters.put(thisName, thisValue);
    		}
    	}
    	entity.setParameters(parameters);

    	try {
    		ServletInputStream stream = request.getInputStream();
    		if(null != stream){
    			entity.setBody(new String(StreamUtils.copyToByteArray(stream), "utf-8"));
    		}
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}

    	StackTraceElement [] messages = e.getStackTrace();  
    	StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
    	int length = messages.length;  
    	String [] mes = new String[length];
    	for (int i = 0; i < length; i++) {  
    		stringBuffer.append("\t"+messages[i].toString()+"\n");  
    		mes[i] = messages[i].toString();
    	}
//    	System.out.println("------------------");
//    	System.out.println(stringBuffer);
//    	System.out.println("------------------");
    	ExceptionResponseInfo  info = new ExceptionResponseInfo();
    	info.setMessagesInfo(mes);
    	entity.setExceptionInfo(info);
    	entity.setMessage(e.getMessage());
    	entity.setCreate(new Date());
    	template.save(entity);  //保存至  mongdb
		if (e instanceof  BuessExceptions){
			return ResultBody.error(ResultBody.business_error,((BuessExceptions) e).getBody());
		}
    	if (e instanceof RentException) {
    		RentException rentException = (RentException) e;
			ResponseInfo responseInfo = rentException.getResponseInfo();
			if (responseInfo instanceof BuessExceptions){
				return ResultBody.error(ResultBody.business_error,((BuessExceptions) responseInfo).getBody(),responseInfo);
			}
			return ResultBody.error(ResultBody.business_error,"业务异常",responseInfo);
    	} /*else if(e instanceof UnauthenticatedException){
    		return ResultBody.error(ResultBody.LOGIN_CODE,"【请先登录系统】"+e.getMessage());
    	} else if(e instanceof UnauthorizedException){
    		return ResultBody.error(ResultBody.error_per_oper,"对不起，您无权限操作此功能！");
    	} */else if(e instanceof  HttpRequestMethodNotSupportedException){
    		return ResultBody.error(ResultBody.error_meth,"【没有此方法】"+e.getMessage(),info);
    	} else if(e instanceof IndexOutOfBoundsException){
    		return ResultBody.error(ResultBody.error_index_out,"【下标越界】"+ e.getMessage(),info);
    	} else if(e instanceof ArrayIndexOutOfBoundsException ){
    		return ResultBody.error(ResultBody.error_array_index_out,"【数组下标越界】"+e.getMessage(),info);
    	} else if(e instanceof ClassCastException){
    		return ResultBody.error(ResultBody.error_Class_Cast,"【类型转换异常】"+ e.getMessage(),info);
    	} else if(e instanceof IllegalArgumentException){
    		return ResultBody.error(ResultBody.error_Illegal_Argument,"【非法计算异常】"+ e.getMessage(),info);
    	} else if(e instanceof NullPointerException){
    		return ResultBody.error(ResultBody.error_Null_Pointer,"【空指针异常】"+ e.getMessage(),info);
    	} else if(e instanceof ArithmeticException ){
    		return ResultBody.error(ResultBody.error_Arithmetic,"【数学运算异常】"+ e.getMessage(),info);
    	} else if(e instanceof IllegalAccessException ){
    		return ResultBody.error(ResultBody.error_Illegal_Access,"【没有权限访问对象】"+ e.getMessage(),info);
    	} else{
    		return ResultBody.error("【系统异常】" + e.getMessage(),info);
    	}
    }

//    /**
//     * 拦截业务异常
//     */
//    @ExceptionHandler(RentException.class)
//    @ResponseBody
//    public ResultBody notFount(RentException e) {
//        return ResultBody.error(e.getResponseInfo());
//    }
    
}
