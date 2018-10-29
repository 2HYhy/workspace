package com.me.hyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 服务注册中心的高可用(集群)
 */
@RestController
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerClusterApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/cluster")
	public String function() {
		return "This is eureka-server-cluster, port = "+port;
	}

	/**
	 * 模拟注册服务的状态由up变为down的过程
	 */
	@Autowired
	HealthChecker healthChecker;
	@GetMapping("/status/{flag}")
	public String changeStatus(@PathVariable boolean flag) {
		healthChecker.setStatus(flag);
		return String.valueOf(flag);
	}

}
