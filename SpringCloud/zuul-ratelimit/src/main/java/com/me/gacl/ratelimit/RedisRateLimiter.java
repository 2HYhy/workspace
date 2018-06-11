package com.me.gacl.ratelimit;

import com.me.gacl.ratelimit.pojo.Policy;
import com.me.gacl.ratelimit.pojo.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
public class RedisRateLimiter implements RateLimiter {

    @Autowired
    private StringRedisTemplate template;

    @Override
    public Rate consume(Policy policy, String key) {
        final Long limit = policy.getLimit();
        final Long refreshInterval = policy.getRefreshInterval();
        //当前请求数+1并返回
        final Long current = this.template.boundValueOps(key).increment(1L);
        Long expire = this.template.getExpire(key);
        //如果当前请求已到刷新周期, redis会自动移除技术器, 重新为当前请求初始化限流信息
        if (expire == null || expire == -1) {
            this.template.expire(key, refreshInterval, SECONDS);
            expire = refreshInterval;
        }
        return new Rate(limit, Math.max(-1, limit - current), SECONDS.toMillis(expire));
    }
}
