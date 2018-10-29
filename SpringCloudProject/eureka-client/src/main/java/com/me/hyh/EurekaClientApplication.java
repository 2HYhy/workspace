package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * 服务提供者
 * jar包运行:进入target目录,执行java -jar eureka-client-0.0.1-SNAPSHOT.jar --server.port=xxx,用不同的端口启动
 * docker容器启动命令:docker run --link eureka-server:9000 --name <容器名> -p 9001:9001 -t <镜像名>
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@Value("${server.port}")
	private int port;
	@GetMapping("/client")
	public String function() {
		//设置休眠时间1000ms,TimeUnit.MILLISECONDS.sleep(1000)
		return "This is eureka-client, port = "+port;
	}
}
