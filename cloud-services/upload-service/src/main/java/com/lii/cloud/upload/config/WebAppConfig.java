package com.lii.cloud.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 注册拦截器
 * @author Administrator
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Value(value="${fileUpload.imgUrl}")
	private String imgUrl;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径

        //addResourceLocations是图片存放的真实路径
		 registry.addResourceHandler("/image/**").addResourceLocations(imgUrl);
		 super.addResourceHandlers(registry);
}
	
	

}
