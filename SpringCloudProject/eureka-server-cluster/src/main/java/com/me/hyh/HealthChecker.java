package com.me.hyh;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author CH-yfy
 * @date 2018/8/6
 */
@Component
public class HealthChecker implements HealthIndicator {

    private boolean status = true;

    @Override
    public Health health() {
        if(status) {
           return new Health.Builder().withDetail("status is", "up").up().build();
        } else {
            return new Health.Builder().withDetail("status is", "down").down().build();
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
