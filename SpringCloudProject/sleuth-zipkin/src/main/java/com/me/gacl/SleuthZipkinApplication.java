package com.me.gacl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import zipkin.server.EnableZipkinServer;

/**
 * @author CH-yfy
 * 分布式服务链路追踪监控
 * 跟踪一个请求从一个微服务到下一个微服务的传播过程，负责数据收集和信息展示
 * 通过http的方式传输链路数据
 * 通过创造和追踪一些标识符(spanId,traceId,parentId),构建一个request请求的完整流程树
 * zipkin自带的api(源码在ZipkinQueryApiV1.java)包括:
 * localhost:9011/api/v1/dependencies?endTs=1534398518998
 * localhost:9011/api/v1/services
 * localhost:9011/api/v1/spans?serviceName=xx
 * localhost:9011/api/v1/traces
 */
@EnableZipkinServer
@SpringBootApplication
public class SleuthZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthZipkinApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/sleuth")
	public String function() {
		return "This is cloud-sleuth, port = "+port;
	}
}

