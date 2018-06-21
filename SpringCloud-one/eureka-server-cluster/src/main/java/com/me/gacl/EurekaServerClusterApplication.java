package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author CH-yfy
 * 客户端将自己注册到集群EurekaServer中,分别访问http://localhost:9100/，http://localhost:9200/,都会有已注册服务eureka-server-cluster:8888
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaServerClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerClusterApplication.class, args);
	}
}
