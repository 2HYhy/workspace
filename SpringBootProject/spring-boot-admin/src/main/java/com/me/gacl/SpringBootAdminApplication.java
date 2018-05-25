package com.me.gacl;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CH-yfy
 * 可视化监控，分为服务端和客户端(本例既是服务端又是客户端)
 * 若为单个springboot应用，需在每个被监控的应用中配置adminServer地址信息；若应用都注册在eureka中则不需要，adminServer会自动从注册中心抓取应用信息
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminApplication.class, args);
	}
}
