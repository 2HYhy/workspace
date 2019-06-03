package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author CH-yfy
 */
@EnableAuthorizationServer
@SpringBootApplication
public class CodeApplication {

	private static final String CLIENT_ID = "c123456";
	private static final String SECRET_ID = "s098765";

	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

	/**
	 * 浏览器
	 * 配置spring cloud security OAuth2
	 */
	@Configuration
	public static class SecurityConfiguration extends AuthorizationServerConfigurerAdapter {
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			//使用内存存储
			clients.inMemory()
					.withClient(CLIENT_ID)
					.secret(SECRET_ID)
					//client允许的授权类型为授权码模式
					.authorizedGrantTypes("authorization_code")
					//client允许的授权范围
					.scopes("app");
		}
	}

}
