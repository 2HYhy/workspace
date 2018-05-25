package com.me.gacl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/23
 */
@RestController
public class AdminController {

    @GetMapping("/")
    public Object print() {
        Map<String, Object> map = new HashMap<>(100);
        map.put("message", "Hello,here is spring boot admin");
        return map;
    }
}
