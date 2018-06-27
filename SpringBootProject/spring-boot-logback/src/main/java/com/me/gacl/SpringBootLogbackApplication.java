package com.me.gacl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 */
@RestController
@SpringBootApplication
public class SpringBootLogbackApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootLogbackApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootLogbackApplication.class, args);
		int length = context.getBeanDefinitionNames().length;
		logger.trace("Spring boot启动初始化了 {} 个 Bean", length);
		logger.debug("Spring boot启动初始化了 {} 个 Bean", length);
		logger.info("Spring boot启动初始化了 {} 个 Bean", length);
		logger.warn("Spring boot启动初始化了 {} 个 Bean", length);
		logger.error("Spring boot启动初始化了 {} 个 Bean", length);
		//启动时进入异常，console打印 /by zero
//		try {
//			int i = 0;
//			int j = 1 / i;
//			System.out.println("i = " + j);
//		} catch (Exception e) {
//			logger.error("【SpringBootDemoLogbackApplication】启动异常：{}", e);
//		}
	}

	@GetMapping("/home")
	public String show() {
		return "Hi, Spring Boot Logback";
	}
}
