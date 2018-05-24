package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * 服务消费者，基于ribbon+resTemplate的调度方式
 */
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced  //表示开启负载均衡功能
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
