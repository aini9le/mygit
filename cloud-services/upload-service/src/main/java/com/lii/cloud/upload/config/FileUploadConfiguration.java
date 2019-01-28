package com.lii.cloud.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
public class FileUploadConfiguration {
		@Value(value="${fileUpload.multipart.max-file-size}")
		private String fileSize;  //单个文件限制大小
		@Value(value="${fileUpload.multipart.max-request-size}")
		private String requestSize;  //总的上传文件大小


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件大小限制 ,超出设置页面会抛出异常信息，
		// 这样在文件上传的地方就需要进行异常信息的处理了;
		factory.setMaxFileSize(fileSize); // KB,MB
		//		/// 设置总上传数据总大小
		factory.setMaxRequestSize(requestSize);
		// Sets the directory location where files will be stored.
		//		 factory.setLocation(fileUploadPrefix);
		return factory.createMultipartConfig();
	}


	//显示声明CommonsMultipartResolver为mutipartResolver
	/*@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
		resolver.setMaxInMemorySize(1*1024*1024);
		resolver.setMaxUploadSize(1*1024*1024);//上传文件大小 50M 50*1024*1024
		return resolver;
	}  

	@Bean
	public WebAppConfig webAppConfig(){
		return new WebAppConfig();
	}*/
}
