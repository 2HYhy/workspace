package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author CH-yfy
 * 启动方式：1. mvn spring-boot:run   2. cd target, java -jar 项目.jar
 */
@SpringBootApplication
@EnableScheduling
//@EnableAsync  //启用异步调用

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//查看Spring Boot启动时自动注入的bean
//	@Bean
//	public CommandLineRunner func(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("The following are the beans provided by Spring Boot:");
//			String [] beans = ctx.getBeanDefinitionNames();
//			Arrays.sort(beans);
//			for(String bean : beans) {
//				System.out.println(bean);
//			}
//		};
//	}
}
