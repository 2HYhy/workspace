package com.me.gacl;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
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
 * Ribbo是一个基于HTTP和TCP的客户端负载均衡器
 * 服务消费者，基于ribbon+resTemplate的调度方式
 * 仪表盘监控流程:
 * 1. 访问http://localhost:9003/hystrix
 * 2. 填入http://localhost:9003/hystrix.stream和title,点击monitor stream进入监控信息页面
 * 3. 访问http://localhost:9003/ribbon/hello 任一接口
 * 4. 监控页面出现监控详情信息
 * 在访问/hystrix.stream之前，应先访问被监控应用的任意接口，才能看到监控到的详情信息，否则看到的是一系列的ping,ping......
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
	@LoadBalanced  //表示开启负载均衡功能，默认的是轮询策略
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IRule randomRule() {  //随机选择策略,还有加权轮询策略WeightedResponseTimeRule
		return new RandomRule();
	}
}
