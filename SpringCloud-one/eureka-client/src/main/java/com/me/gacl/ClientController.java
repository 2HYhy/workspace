package com.me.gacl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @RequestMapping("/user")
    public String getUser(@RequestParam("name")String name) {
        return "service producer, the user's name = "+name;
    }
}
