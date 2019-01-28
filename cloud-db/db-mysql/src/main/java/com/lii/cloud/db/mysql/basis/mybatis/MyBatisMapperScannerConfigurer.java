package com.lii.cloud.db.mysql.basis.mybatis;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lii.cloud.db.mysql.basis.TkMapper;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(MybatisConfigurer.class)
public class MyBatisMapperScannerConfigurer {
	
	
	@Bean(name="basisMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("basisSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.lii.cloud.db.mysql.mapper.iMapper");
//        mapperScannerConfigurer.setBasePackage(basePackage);
        //配置通用mappers
        Properties properties = new Properties();
//        properties.setProperty("mappers", "com.lii.cloud.db.mysql.basis.TkMapper");
        properties.setProperty("mappers", TkMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("identity", "mysql");
//        properties.setProperty("ORDER","BEFORE");
//        properties.setProperty("IDENTITY", "identity");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
