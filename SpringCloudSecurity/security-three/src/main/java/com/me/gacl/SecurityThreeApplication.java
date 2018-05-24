package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author CH-yfy
 * 自动配置的，基于授权码模式
 */
@EnableAuthorizationServer
@SpringBootApplication
public class SecurityThreeApplication {

	private static final String CLIENT_ID = "client";
	private static final String SECRET_ID = "secret";

	public static void main(String[] args) {
		SpringApplication.run(SecurityThreeApplication.class, args);
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


	/**
	 * gitHub(要注释@EnableAuthorizationServer)
	 * 配置spring cloud security OAuth2(git-application是client，git账号是user)
	 */
//	@Component
//	//基于OAuth2的单点登录
//	@EnableOAuth2Sso
//	public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			//所有请求都得经过认证和授权
//			http.antMatcher("/**")
//					.authorizeRequests().anyRequest().authenticated()
//					.and().authorizeRequests().antMatchers("/").permitAll()
//					.and().csrf().disable()
//					.logout().logoutUrl("/logout").permitAll()
//					.logoutSuccessUrl("/");
//		}
//	}
}
