package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * @date 2018/4/19
 */
@RestController
public class ServiceUserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/user/remote")
    public String getRemote(){
        return restTemplate.getForObject("http://localhost:9301/hello/local",String.class);
    }

    @RequestMapping("/user/local")
    public String getLocal(){
        return "This is service-user";
    }
}
