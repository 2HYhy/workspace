package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CH-yfy
 *修改了git上的配置后，需要重启服务才可生效，走动生效的方法:1-spring cloud bus; 2-refresh.
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
