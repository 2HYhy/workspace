package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * 仅作为资源服务器，通过security-four的认证授权获取本模块存储的资源
 */
@SpringBootApplication
@EnableResourceServer
@RestController
public class SecuritySixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySixApplication.class, args);
	}

	Map<Integer, Object> myMap = new HashMap<>();

	/**
	 * 要访问该接口，必须拥有access_token(从security-four中获取)
	 * @return
	 */
	@RequestMapping("/hyh/message")
	public Collection<Object> showInfo() {
		myMap.put(1006, "October 6th");
		myMap.put(609, "June 9th");
		return myMap.values();
	}
}
