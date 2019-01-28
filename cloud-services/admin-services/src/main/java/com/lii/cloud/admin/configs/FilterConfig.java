//package com.lii.cloud.admin.configs;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.lii.cloud.common.config.filters.SsoFilter;
//
//import redis.clients.jedis.JedisPool;
//
//@Configuration
//public class FilterConfig {
//	
//	@Resource
//    private JedisPool jedisPool;
//    @Value("${sso.server}")
//    private String ssoServer;
//	
//	/**
//     * 注册过滤器
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean getFilterRegistration(){
//    	FilterRegistrationBean bean = new FilterRegistrationBean();
//    	bean.setName("ssoFilter");
//    	bean.setOrder(1);
//    	bean.addUrlPatterns("/*");
//    	bean.setFilter(new SsoFilter(jedisPool));
//    	bean.addInitParameter("ssoServer", ssoServer);
//    	bean.addInitParameter("exclusions", "*.*,/druid");
//    	return bean;
//    }
//
//}
