package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author CH-yfy
 * git上配置我呢间发生更新时，server端会自动读取最新提交的内容，无需重启.
 * 将配置中心注册到eureka-server之后，config-client端获取文件就不再通过config-server的IP地址，而是通过服务名从注册中心调用config-server
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
