package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * springboot2启用/actuator/prometheus端点，供Prometheus来抓取指标
 * prometheus + influxdb
 */
@EnableScheduling
@RestController
@SpringBootApplication
public class SpringBootMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMonitoringApplication.class, args);
	}

	@GetMapping("/monitoring")
	public String show() {
		return "hello, Spring Boot Micrometer";
	}
}
