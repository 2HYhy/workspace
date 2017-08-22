package com.me.gacl.service;

/**
 * Created by yunhua.he on 2017/8/22.
 */
public class RedisServiceImpl implements RedisService {

    @Override
    public String getTimestamp(){
        Long time = System.currentTimeMillis();
        return time.toString();
    }
}
