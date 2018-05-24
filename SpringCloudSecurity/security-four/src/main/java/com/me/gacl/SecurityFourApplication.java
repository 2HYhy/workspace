package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author CH-yfy
 * 1. 主类也同时作为资源服务器(基于授权码模式),自动配置
 * 2. 手动配置的授权码模式
 */
@SpringBootApplication
@RestController
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityFourApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityFourApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
