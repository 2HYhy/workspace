package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author CH-yfy
 * 主类也同时作为资源服务器, 手动配置的授权码模式
 */
@RestController
@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class SecurityOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOneApplication.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

}
