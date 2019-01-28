package com.lii.cloud.upload.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FileUploadInterceptor implements HandlerInterceptor {
	
	private long maxSize = 1024*1024;  

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	/* 
	 * 进入controller层之前拦截请求   
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if(request!=null && ServletFileUpload.isMultipartContent(request)) {  
            ServletRequestContext ctx = new ServletRequestContext(request);  
            long requestSize = ctx.contentLength();  
            if (requestSize > maxSize) {  
                throw new MaxUploadSizeExceededException(maxSize);  
            }  
        }  
		return true;
	}

}
