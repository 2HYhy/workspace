package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
    public String sayHello() {
        return feignService.sayHello();
    }

    @RequestMapping("/feign/user")
    public String getUser(@RequestParam("name") String name) {
        return feignService.getUser(name);
    }
}
