package com.me.gacl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 服务提供者
 * @EnableEurekaClient基于spring-cloud-netflix,@EnableDiscoveryClient基于spring-cloud-commons,一般注册中心为eureka时用前者，其他注册中心(consul,zookeeper)用后者
 * 打包后，进入target目录，分别执行java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=xxx,用不同的端口启动
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	/**
	 * 此处打印端口，以便于验证负载均衡是否起作用
	 */
	@Value("${server.port}")
	String port;

	@RequestMapping("/client/hello")
	public String sayHello() {
		return "This is eureka-client from port=" + port + " registered to eureka-server !";
	}


	@RequestMapping("/ribbon/test")
	public String show() {
		return "This is from 127.0.0.1:" + port;
	}
}
