package com.me.hyh.service;

import org.springframework.stereotype.Service;

/**
 * @author CH-yfy
 * @date 2018/8/10
 *
 */
@Service
public class HystrixService implements FeignService {

    @Override
    public String getClient() {
        return "Sorry, getClient occurred error";
    }

    @Override
    public String getHello(Integer id) {
        return "Sorry, getHello occurred error";
    }

    @Override
    public String getUser(Integer id) {
        return "Sorry, getUser occurred error";
    }
}
