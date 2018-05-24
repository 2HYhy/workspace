package com.me.gacl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 服务提供者
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	/**
	 * 此处打印端口，以便于验证负载均衡是否起作用
	 */
	@Value("${server.port}")
	String port;
	@RequestMapping("/client/hello")
	public String sayHello() {
		return "This is eureka-client from port=" + port + " registered to eureka-server !";
	}
}
