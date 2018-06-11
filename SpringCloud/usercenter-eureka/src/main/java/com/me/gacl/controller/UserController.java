package com.me.gacl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/17
 */
@RestController
public class UserController {

    @RequestMapping(value = "/getOne")
    public String available() {
        return "user-center-eureka:8092,Access by Spring Cloud Zuul One";
//        return "user-center-eureka:8093,Access by Spring Cloud Zuul One";
//        return "user-center-eureka:8094,Access by Spring Cloud Zuul One";
    }

    @RequestMapping(value = "/getTwo")
    public String checkedOut() {
        return "user-center-eureka,Access Spring Cloud Zuul Two";
    }

}
