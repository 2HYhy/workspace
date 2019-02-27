package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author CH-yfy
 */
@EnableCaching
@SpringBootApplication
public class SpringBootEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEhcacheApplication.class, args);
	}

}
