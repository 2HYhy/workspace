package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH-yfy
 * 仅作为资源服务器，通过security-one的认证授权获取本模块存储的资源
 */
@SpringBootApplication
public class SecurityThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityThreeApplication.class, args);
	}

}
