package com.me.gacl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author CH-yfy
 * 断路器聚合(集群)监控
 * 1. 访问http://localhost:9010/turbine.stream
 * 2. 访问http://localhost:9003/ribbon/hello, http://localhost:9004/feign/hello
 * 3. 访问http://localhost:9003/hystrix 或者 http://localhost:9004/hystrix，输入监控流http://localhost:9010/turbine.stream和title
 * 4. 即看到两个服务监控
 * 在访问/turbine.stream之前，应先访问被监控应用的任意接口，才能看到监控到的详情信息，否则看到的是一系列的ping,ping......
 */
//该注解包含@EnableDiscoveryClient
@EnableTurbine
@SpringBootApplication
public class HystrixTurbineApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixTurbineApplication.class).web(true).run(args);
	}
}
