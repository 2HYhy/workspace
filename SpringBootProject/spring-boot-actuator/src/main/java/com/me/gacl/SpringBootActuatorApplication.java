package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CH-yfy
 * springboot应用的健康监控,默认的endpoints有:/env,/health,/metrics,/info,/configprops,/trace etc.
 */
@SpringBootApplication
public class SpringBootActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActuatorApplication.class, args);
	}
}
