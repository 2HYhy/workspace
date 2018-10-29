package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CH-yfy
 */
@SpringBootApplication
public class FeignUploadServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignUploadServerApplication.class, args);
	}

	@Value("${server.port}")
	private Integer port;
	@RequestMapping("/feign-upload")
	public String function() {
		return "This is feign-upload-server of port = " + port;
	}
}
