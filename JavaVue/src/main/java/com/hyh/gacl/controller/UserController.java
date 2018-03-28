package com.hyh.gacl.controller;

import com.hyh.gacl.model.User;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import java.util.UUID;

/**
 * @author CH-yfy
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static String NAME = "root";
    private final static String PWD = "123456";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) throws ServletException {
        if (! NAME.equals(user.getUsername())) {
            throw new ServletException("wrong username");
        }
        if (! PWD.equals(user.getPassword())) {
            throw new ServletException("wrong password");
        }
        String token = String.valueOf(UUID.randomUUID());
        return token;
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public String getEmail() {
        return "vue and java, the token has been verified connect";
    }
}
