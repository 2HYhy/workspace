package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @author CH-yfy
 * 服务链路追踪(Spring Cloud Sleuth)
 */
@EnableZipkinServer
@SpringBootApplication
public class SleuthZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthZipkinApplication.class, args);
	}
}
