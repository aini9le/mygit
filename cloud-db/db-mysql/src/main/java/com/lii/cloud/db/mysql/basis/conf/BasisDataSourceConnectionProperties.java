package com.lii.cloud.db.mysql.basis.conf;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class BasisDataSourceConnectionProperties extends DataSourceConnectionProperties {

}
