package com.me.gacl;

import com.me.gacl.filter.MyLocalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author CH-yfy
 * Spring Cloud Zuul的作用是提供负载均衡，反向代理，权限认证
 */
@EnableEurekaClient
@EnableZuulProxy   //支持网关路由
@SpringBootApplication
public class CloudzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudzuulApplication.class, args);
	}

	@Bean
	MyLocalFilter myLocalFilter() {
		return new MyLocalFilter();
	}

}
