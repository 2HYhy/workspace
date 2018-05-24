package com.me.gacl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/17
 */
@RestController
@RequestMapping("/user-demo")
public class UserController {

    @RequestMapping(value = "/getOne")
    public String available() {
        return "Access by Spring Cloud Zuul One";
    }

    @RequestMapping(value = "/getTwo")
    public String checkedOut() {
        return "Access Spring Cloud Zuul Two";
    }

}
