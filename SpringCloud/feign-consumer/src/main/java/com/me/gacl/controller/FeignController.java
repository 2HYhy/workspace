package com.me.gacl.controller;

import com.me.gacl.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/7/9
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping("/cloud/feign/one")
    public String getHello() {
        return feignService.getHello();
    }

    @RequestMapping("/cloud/feign/two")
    public String getUser() {
        return feignService.getUser("pineapple");
    }

    @RequestMapping("/cloud/feign/three")
    public String getJson() {
        Map<String,String> map = new HashMap<>(2);
        map.put("project", "FEIGN");
        return feignService.getJson(map);
    }

    @RequestMapping("/cloud/feign/four")
    public String getHeader() {
        return feignService.getHeader("Jessica", "098765");
    }
}
