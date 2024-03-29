package com.me.hyh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getOne")
    public String available() {
        return "user-center,Access by Spring Cloud Zuul One";
    }

    @RequestMapping(value = "/getTwo")
    public String checkedOut() {
        return "user-center,Access Spring Cloud Zuul Two";
    }

}
