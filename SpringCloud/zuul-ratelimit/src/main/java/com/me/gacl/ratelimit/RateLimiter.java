package com.me.gacl.ratelimit;

import com.me.gacl.ratelimit.pojo.Policy;
import com.me.gacl.ratelimit.pojo.Rate;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
public interface RateLimiter {

    /**
     * 限流计算
     * @param policy
     * @param key
     * @return
     */
    Rate consume(Policy policy, String key);

}
