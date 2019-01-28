package com.lii.cloud.db.mysql.basis.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 */
@Configuration
@EnableTransactionManagement
public class BasisDataSourceConf {
    private static final Log log = LogFactory.getLog(BasisDataSourceConf.class);

//	配置数据源
    @Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource initDataSource(BasisDataSourceConnectionProperties bscp,
                                          BasisDataSourceOperationProperties bdop) {
        log.info("初始化basisDataSource");
        DruidDataSource dds = new DruidDataSource();
//        com.mysql.cj.jdbc.Driver
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl(bscp.getUrl());
        dds.setUsername(bscp.getUsername());
        dds.setPassword(bscp.getPassword());
        dds.setInitialSize(bdop.getInitialSize());
        dds.setMinIdle(bdop.getMinIdle());
        dds.setMaxActive(bdop.getMaxActive());
        dds.setMaxWait(bdop.getMaxWait());
        dds.setTimeBetweenEvictionRunsMillis(bdop.getTimeBetweenEvictionRunsMillis());
        dds.setMinEvictableIdleTimeMillis(bdop.getMinEvictableIdleTimeMillis());
        dds.setValidationQuery(bdop.getValidationQuery());
        dds.setTestWhileIdle(bdop.getTestWhileIdle());
        dds.setTestOnBorrow(bdop.getTestOnBorrow());
        dds.setTestOnReturn(bdop.getTestOnReturn());
        dds.setPoolPreparedStatements(bdop.getPoolPreparedStatements());
        dds.setMaxPoolPreparedStatementPerConnectionSize(bdop.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dds.setFilters(bdop.getFilters());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }

    /**
     * druid数据源状态监控.
     * @return
     */
    @Bean(name = "basisServletRegistrationBean")
    public ServletRegistrationBean druidServlet(BasisDataSourceOperationProperties bdop) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置登录查看信息的账号密码.
//        servletRegistrationBean.addInitParameter("loginUsername",bdop.getLoginUsername());//登录名 
//        servletRegistrationBean.addInitParameter("loginUsername",bdop.getLoginUsername());//登录密码  
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        servletRegistrationBean.addInitParameter("allow", "10.1.4.195,127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
        servletRegistrationBean.addInitParameter("deny", "192.168.16.111"); //IP黑名单 (存在共同时，deny优先于allow)  
//        servletRegistrationBean.addInitParameter("loginUsername", this.druidLoginName);//登录名  
//        reg.addInitParameter("loginPassword", this.druidPassword);//登录密码  
        servletRegistrationBean.addInitParameter("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能  
        return servletRegistrationBean;
    }

    /**
     * druid过滤器.
     * @return
     */
    @Bean(name = "basisFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        
//        filterRegistrationBean.addInitParameter("profileEnable", "true");  
//        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");  
//        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }
    
    @Bean(name = "basisTransactionManager")
    public PlatformTransactionManager appTransactionManager(@Qualifier("basisDataSource") DruidDataSource dataSource){
    	System.out.println("开始注入事务管理..............");
    	DataSourceTransactionManager datas = new DataSourceTransactionManager();
    	datas.setDataSource(dataSource);
    	return datas;
    }
    
}
