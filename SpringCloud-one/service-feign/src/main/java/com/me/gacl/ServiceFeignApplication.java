package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author CH-yfy
 * 服务消费者，基于feign的调度方式,feign默认集成了ribbon
 * feign的使用方式:
 * (1)作为http客户端调用远程http服务，不依赖springcloud框架;
 * (2)不用配置eureka，但必须有服务端提供接口，客户端通过@FeignClient(url="")直接指定服务url;
 * (3)注册到服务中心，通过@FeignClient(name="")调用远程接口
 */
//@EnableCircuitBreaker //表示开启断路器功能
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}
}
