package com.me.hyh;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 不注册到服务中心
 */
@EnableFeignClients
@RestController
@SpringBootApplication
public class FeignServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignServiceApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/feign")
	public String function() {
		return "This is feign-service, port = "+port;
	}

	@Bean
	public Encoder springFormEncoder() {
		return new SpringFormEncoder();
	}
}
