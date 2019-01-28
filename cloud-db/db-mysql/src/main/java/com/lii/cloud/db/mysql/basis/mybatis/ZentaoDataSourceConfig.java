package com.lii.cloud.db.mysql.basis.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.lii.cloud.db.mysql.basis.TkMapper;

import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

//@Configuration
//@MapperScan(basePackages = "com.lii.cloud.db.mysql.mapper.iMapper.test", sqlSessionTemplateRef = "zentaoSqlSessionTemplate")
public class ZentaoDataSourceConfig {

	@Bean(name = "zentaoDataSource")
	//    @ConfigurationProperties(prefix = "spring.datasource.zentao")
	public DataSource setDataSource() {
		DruidDataSource druid = new DruidDataSource();
		druid.setDriverClassName("com.mysql.cj.jdbc.Driver");
		druid.setDbType("mysql");
		druid.setUrl("jdbc:mysql://10.1.20.229:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Hongkong");
		druid.setUsername("root");
		druid.setPassword("root");
		return druid;
	}

	@Bean(name = "zentaoTransactionManager")
	public DataSourceTransactionManager setTransactionManager(@Qualifier("zentaoDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "zentaoSqlSessionFactory")
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("zentaoDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.lii.cloud.common.entity.test.po");
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "zentaoSqlSessionTemplate")
	public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("zentaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean(name="zentaoMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("zentaoSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.lii.cloud.db.mysql.mapper.test");
        //配置通用mappers
        Properties properties = new Properties();
        properties.setProperty("mappers", TkMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("identity", "mysql");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
