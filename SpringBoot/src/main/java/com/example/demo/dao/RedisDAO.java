package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author yunhua.he
 * @date 2017/12/6
 */

@Repository
public class RedisDAO {

    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        //过期时间为1分钟
        ops.set(key, value, 1, TimeUnit.MINUTES);
    }

    public String getKey(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        String value = ops.get(key);
        return value;
    }
}
