package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * 服务消费者，基于ribbon+resTemplate的调度方式
 * 仪表盘监控流程:
 * 1. 访问http://localhost:9003/hystrix
 * 2. 填入http://localhost:9003/hystrix.stream和title,点击monitor stream进入监控信息页面
 * 3. 访问http://localhost:9003//ribbon/hello 任一接口
 * 4. 监控页面出现监控详情信息
 */
@EnableHystrixDashboard
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
