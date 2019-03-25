package com.me.hyh;

import com.me.hyh.filter.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CH-yfy
 */

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@RequestMapping("cloudGateway")
	public String test() {
		return "-----Welcome to Spring Cloud Gateway-----";
	}


//	@Bean
	public RouteLocator testTimeRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/time/**")
						.filters(f -> f
								.filter(new SimpleTimeGatewayFilter())
								.addResponseHeader("X-Response-Name", "ToTime"))
						.uri("http://httpbin.org:80/get")
						.order(0)
						.id("time_filter_router"))
				.build();
	}

	/**
	 * 向springIOC容器注册该过滤器类的bean
	 * 正确访问http://localhost:8081/auth/get?token=123123
	 * @param builder
	 * @return
	 */
//	@Bean
	public RouteLocator testTokenRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/auth/**")
						.filters(f -> f
								.filter(new AuthTokenGatewayFilter()))
						.uri("http://httpbin.org:80/get")
						.id("token_filter_route"))
				.build();
	}



//	@Bean
	public SimpleTimeGatewayFilterFactory registerBean() {
		System.out.println("inject SimpleTimeGatewayFilterFactory while start application");
		return new SimpleTimeGatewayFilterFactory();
	}

	/**
	 * 向springIOC容器注册该过滤器工厂类的bean
	 * http://localhost:8081/get?token=123123
	 * @return
	 */
	@Bean
	public AuthTokenGatewayFilterFactory registerBeans() {
		System.out.println("inject AuthTokenGatewayFilterFactory while start application");
		return new AuthTokenGatewayFilterFactory();
	}
}


