package com.me.gacl;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/23
 * 自定义Indicator 扩展 HealthEndPoint, 访问http://localhost:8088/health时就包含了自定义的健康信息
 */
@RestController
public class ActuatorController implements HealthIndicator{

    @Override
    public Health health() {
        return new Health.Builder()
                .withDetail("projectName", "spring-boot-actuator")
                .withDetail("createTime", "2018-04-23 10:30:06")
                .withDetail("author", "Linda James").up().build();
    }
}
