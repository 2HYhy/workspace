package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CH-yfy
 * 提供负载均衡，反向代理，权限(过滤)认证
 * 必须注册在eureka上
 * 直接借助zuul-ratelimit依赖实现限流功能
 */
@EnableZuulProxy
@SpringBootApplication
public class CloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/zuul")
	public String function() {
		return "This is cloud-zuul, port = "+port;
	}
}
