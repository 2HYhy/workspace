package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CH-yfy
 * 不注册到eureka上
 */
@SpringBootApplication
public class UserCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCenterApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/user")
	public String function() {
		return "This is user-center, port = "+port;
	}
}
