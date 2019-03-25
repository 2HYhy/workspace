package com.me.gacl;

import com.me.gacl.resolver.HostKeyResolver;
import com.me.gacl.resolver.UrlKeyResolver;
import com.me.gacl.resolver.UserKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author CH-yfy
 */
@SpringBootApplication
public class CloudGatewayRatelimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayRatelimitApplication.class, args);
	}

//	@Bean
	public HostKeyResolver hostKeyResolver() {
		return new HostKeyResolver();
	}

//	@Bean
	public UrlKeyResolver urlKeyResolver() {
		return new UrlKeyResolver();
	}

	@Bean
	public UserKeyResolver userKeyResolver() {
		return new UserKeyResolver();
	}
}
