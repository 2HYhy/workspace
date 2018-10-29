package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 */
@SpringBootApplication
public class SleuthServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthServiceUserApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	/**
	 * 指定100%的输出日志，效果和spring.sleuth.sampler.percentage=1.0是一样的
	 * @return
	 */
//	@Bean
//	public AlwaysSampler defaultSampler(){
//		return new AlwaysSampler();
//	}
}
