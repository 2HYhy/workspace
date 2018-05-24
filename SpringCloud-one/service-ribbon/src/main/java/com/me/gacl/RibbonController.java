package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @RequestMapping("/ribbon/hello")
    public String sayHello() {
        return ribbonService.sayHello();
    }

    @RequestMapping("/ribbon/user")
    public String getUser(@RequestParam("name") String name) {
        return ribbonService.getUser(name);
    }
}
