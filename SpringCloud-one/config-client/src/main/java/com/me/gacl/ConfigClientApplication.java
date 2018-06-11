package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CH-yfy
 *修改了git上的配置后，若不使用cloud bus,需要重启服务才可生效;若使用cloud bus,无需重启服务,先发送http://localhost:8082/bus/refresh POST请求后,自动生效
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
