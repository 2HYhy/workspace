package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * ./consul agent -dev,先启动consul,再启动工程,访问localhost:8500,发现服务已注册
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudConsulApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/getAllServices")
	public Object services() {
		System.out.println("-------获取所有服务------");
		return discoveryClient.getServices();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConsulApplication.class, args);
	}
}
