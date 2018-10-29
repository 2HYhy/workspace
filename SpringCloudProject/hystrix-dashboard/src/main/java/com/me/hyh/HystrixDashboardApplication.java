package com.me.hyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author CH-yfy
 * 监控单体应用（eureka-ribbon）
 * 被监控服务须满足：添加hystrix和actuator依赖，@EnableCircuitBreaker开启断路器，监控的接口使用了@HystrixCommand
 * dashboard服务需满足：添加hystrix和dashboard依赖，@EnableHystrixDashboard注解
 * 1. http://localhost:9009/hystrix.stream,出现仪表盘界面
 * 2. http://localhost:9005/hystrix.stream,出现一系列ping
 * 3. http://localhost:9005/ribbon/client接口访问后再访问http://localhost:9005/hystrix.stream时就会有详情
 * 4. 在界面中输入http://localhost:9005/hystrix.stream和自定义的title后点击montior stream即可监控访问接口的详情
 */
@EnableFeignClients
@EnableHystrix

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}
}


