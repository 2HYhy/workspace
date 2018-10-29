package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 创建声明式、模板化的HTTP客户端
 */
@EnableFeignClients
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaFeignApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/feign")
	public String function() {
		return "This is eureka-feign, port = "+port;
	}
}