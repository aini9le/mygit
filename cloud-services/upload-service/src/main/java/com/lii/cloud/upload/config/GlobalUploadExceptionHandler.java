package com.lii.cloud.upload.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.alibaba.fastjson.JSONObject;
import com.lii.cloud.common.tools.result.ResultBody;

@ControllerAdvice
public class GlobalUploadExceptionHandler {
	
	@ExceptionHandler({MaxUploadSizeExceededException.class,Exception.class})
    @ResponseBody
    public String handle(Exception ex,HttpServletRequest request) {
    	System.out.println("...............header.............");
//    	String message =  getFileMB(((MaxUploadSizeExceededException) ex).getMaxUploadSize());
//    	System.out.println("文件不能大于"+message);
    	
    	ResultBody body = new ResultBody();
    	body.setStatus(ResultBody.ERROR);
    	body.setMessage("文件上传文件出错:"+ex.getMessage());
    	if(ex instanceof MaxUploadSizeExceededException){
    		body.setMessage("文件上传文件超出文件限制大小,错误信息:"+ex.getMessage());
    		return JSONObject.toJSONString(body);
    	}else{
    		body.setMessage("文件上传文件程序异常,错误信息:"+ex.getMessage());
    		return JSONObject.toJSONString(body);
    	}
    }

}
