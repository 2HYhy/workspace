package com.me.gacl.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by CH-yfy on 2017/8/22.
 */
public class MethodCacheInterceptor implements MethodInterceptor {

    private RedisTemplate<Serializable, Object> redisTemplate;
    private Long defaultCacheExpireTime = 10l;  //缓存默认过期时间为10s

    public Object invoke(MethodInvocation invocation) throws Throwable{
        Object value = null;
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object [] arguments = invocation.getArguments();
        String key = getCacheKey(targetName, methodName, arguments);
        try{
            //判断是否有缓存
            if(exists(key)) {
                return getCache(key);
            }
            //写入缓存
            value = invocation.proceed();
            if(value != null) {
                final String tkey = key;
                final Object tvalue = value;
                new Thread(new Runnable() {
                    public void run(){
                        setCache(tkey,tvalue,defaultCacheExpireTime);
                    }
                }).start();
            }
        }catch (Exception e) {
            e.printStackTrace();
            if(value == null) {
                return invocation.proceed();
            }
        }
        return value;
    }

    //创建缓存key
    public String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append("_").append(methodName);
        if(arguments != null && arguments.length != 0) {
            for(int i=0; i<arguments.length; i++){
                sb.append("_").append(arguments[i]);
            }
        }
        return sb.toString();
    }
    //判断缓存中是否有对应的value
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    //读取缓存
    public Object getCache(final String key){
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    //写入缓存
    public boolean setCache(final String key, Object value, Long expireTime){
        boolean result = false;
        try{
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setRedisTemplate( RedisTemplate<Serializable, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
}
