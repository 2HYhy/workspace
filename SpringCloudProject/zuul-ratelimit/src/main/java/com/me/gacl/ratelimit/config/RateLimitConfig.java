package com.me.gacl.ratelimit.config;

import com.me.gacl.ratelimit.RateLimitFilter;
import com.me.gacl.ratelimit.RateLimiter;
import com.me.gacl.ratelimit.RedisRateLimiter;
import com.me.gacl.ratelimit.pojo.RateLimitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.me.gacl.ratelimit.pojo.RateLimitProperties.PREFIX;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
@Configuration
@EnableConfigurationProperties(RateLimitProperties.class)
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true")
public class RateLimitConfig {

    /**
     * 注册限流过滤器
     * @param rateLimiter
     * @param rateLimitProperties
     * @param routeLocator
     * @return
     */
    @Bean
    public RateLimitFilter rateLimiterFilter(RateLimiter rateLimiter,
                                             RateLimitProperties rateLimitProperties,
                                             RouteLocator routeLocator) {
        return new RateLimitFilter(rateLimiter, rateLimitProperties, routeLocator);
    }

    /**
     * 注册Redis限流器
     * @return
     */
    @Bean
    public RateLimiter redisRateLimiter() {
        return new RedisRateLimiter();
    }

}
