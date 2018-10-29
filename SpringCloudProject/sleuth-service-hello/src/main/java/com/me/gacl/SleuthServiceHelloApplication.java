package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * pom中依赖spring-cloud-starter-sleuth包后，每次链路请求都会添加一串追踪信息，格式是[server-name, main-traceId,sub-spanId,boolean]
 * 依次为服务结点名称；一条链路唯一的TraceID；链路中每一环的SpanID；是否将信息输出到Zipkin等服务收集和展示
 * 可以在控制台日志DEBUG [sleuth-service-user,23fe9298f5610090,bb2e6ef855412a17,false]查看
 * 当各个微服务设置的数据采样率不相同时，是由请求链第一环的概率来决定的
 */
@SpringBootApplication
public class SleuthServiceHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthServiceHelloApplication.class, args);
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
