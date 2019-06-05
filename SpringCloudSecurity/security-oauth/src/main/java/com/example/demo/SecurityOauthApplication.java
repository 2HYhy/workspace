package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH-yfy
 * 手动配置的password，client_credentials模式
 */
@SpringBootApplication
public class SecurityOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauthApplication.class, args);
	}
}
