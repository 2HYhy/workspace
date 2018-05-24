package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author CH-yfy
 * feign上传文件以及feign作http客户端访问远程http服务
 * restful文件上传形式
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignServerUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignServerUploadApplication.class, args);
	}
}
