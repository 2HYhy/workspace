package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author CH-yfy
 */
@SpringBootApplication
public class CloudGatewayPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayPostApplication.class, args);
	}

    /**
     * 应用该过滤器工厂的方式有两种:(1)RequestGatewayFilterFactory类上添加@Component;(2)在启动类中注入bean。
     * @return
     */
//	@Bean
	public RequestGatewayFilterFactory registerBeans() {
		System.out.println("inject RequestGatewayFilterFactory while start application");
		return new RequestGatewayFilterFactory();
	}


	/**
	 * 自定义过滤器PostRequestFilter接收post请求参数
	 * http://localhost:8083/gateway/filter
	 * @param builder
	 * @return
	 */
	@Bean
	public RouteLocator testTokenRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r
						.path("/gateway/**")
						.filters(f -> f
								.filter(new PostRequestFilter()))
						.uri("http://127.0.0.1:9000")
						.id("post_filter_route"))
				.build();
	}
}
