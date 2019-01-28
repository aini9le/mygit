package com.lii.cloud.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  //启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@ComponentScan(basePackages={"com.lii.cloud.admin"
		,"com.lii.cloud.common.config"
		,"com.lii.cloud.common.base"
		,"com.lii.cloud.db"})
@ServletComponentScan(basePackages={"com.lii.cloud.common.ueditor"})
public class AdminServiceApplication {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
