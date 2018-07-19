package com.me.gacl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/17
 */
@RestController
public class UserController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/getOne")
    public String available() {
        return "user-center-eureka port = "+ port +", Access by Spring Cloud Zuul One";
    }

    @RequestMapping(value = "/getTwo")
    public String checkedOut() {
        return "user-center-eureka,Access Spring Cloud Zuul Two";
    }

}
