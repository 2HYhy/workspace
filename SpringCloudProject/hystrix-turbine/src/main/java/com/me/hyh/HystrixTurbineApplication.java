package com.me.hyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author CH-yfy
 * 集群监控应用（eureka-ribbon开启至少2个端口）
 * 1.http://localhost:9009/hystrix.stream,出现仪表盘界面(需要启动hystrix-dashboard)
 * 2.http://localhost:9005/ribbon/client, http://localhost:9006/ribbon/client
 * 3.界面输入http://localhost:9010/turbine.stream和自定义title,即可监控到2个接口的详情
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineApplication.class, args);
	}
}
