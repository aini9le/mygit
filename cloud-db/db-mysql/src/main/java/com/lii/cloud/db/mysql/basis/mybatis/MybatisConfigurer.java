package com.lii.cloud.db.mysql.basis.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageHelper;

@Configuration
public class MybatisConfigurer {
	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;  // 实体类  包路径
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;  // xml资源文件路径
	@Value("${pagehelper.helperDIalect}")
	private String helperDIalect;//mysql
	@Value("${pagehelper.reasonable}")
	private String reasonable;
	@Value("${pagehelper.supportMethodsArgument}")
	private String supportMethodsArgument;
	@Value("${pagehelper.returnPageInfo}")
	private String returnPageInfo;
	@Value("${pagehelper.params}")
	private String params;
	
	@Autowired
	@Qualifier("basisDataSource")
    private DataSource dataSource;
	@Autowired(required = false)
    private Interceptor[] interceptors;
	
	@Bean(name="basisSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		System.out.println("实例化SqlSessionFactory...........");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("com.lii.cloud.common.entity.rent.po");
        bean.setTypeAliasesPackage(typeAliasesPackage);

        //添加插件
        if (this.interceptors != null && this.interceptors.length > 0) {
        	bean.setPlugins(this.interceptors);
        }

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        bean.setMapperLocations(resolver.getResources(mapperLocations));
        return bean.getObject();
    }
	
	/**
     * 分页插件
     * @param dataSource
     */
    @Bean
    public PageHelper pageHelper() {
    	//分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDIalect", helperDIalect);
        properties.setProperty("reasonable", reasonable);
        properties.setProperty("supportMethodsArguments", supportMethodsArgument);
        properties.setProperty("returnPageInfo", returnPageInfo);
        properties.setProperty("params", params);
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean(name="basisSqlSessionTemplate")
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("basisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
    }

}
