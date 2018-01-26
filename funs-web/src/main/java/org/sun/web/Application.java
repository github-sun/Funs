package org.sun.web;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages={"org.sun.admin"})
@MapperScan(basePackages = "org.sun.dao")
@PropertySource(ignoreResourceNotFound=true, value={"classpath:application-dao.properties","classpath:application-admin.properties"})
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		System.out.println("===Web Application started");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) { 
			System.out.println("===Web Application beanName "+beanName);
		}
	}
}
