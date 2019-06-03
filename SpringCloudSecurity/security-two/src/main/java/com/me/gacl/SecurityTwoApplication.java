package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH-yfy
 * 手动配置的password，client_credentials模式
 */
@SpringBootApplication
public class SecurityTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTwoApplication.class, args);
	}
}
