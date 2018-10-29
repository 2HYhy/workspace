package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * 客户端负载均衡+服务降级
 * @SpringCloudApplication=@SpringBootApplication+@EnableDiscoveryClient+@EnableCircuitBreaker
 */
//@SpringCloudApplication
@RestController
@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRibbonApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/ribbon")
	public String function() {
		return "This is eureka-ribbon, port = "+port;
	}

	/**
	 * 开启负载均衡功能(默认采用的是轮询策略)
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * 1.注解方式，自定义采用随机选择策略
	 * 一旦设置，则所有服务均执行此负载均衡策略，而不再是默认的
	 * 不能与RibbonConfig同时存在，否则请求接口报错
	 * @return
	 */
//	@Bean
//	public IRule randomRule() {
//		return new RandomRule();
//	}
}
