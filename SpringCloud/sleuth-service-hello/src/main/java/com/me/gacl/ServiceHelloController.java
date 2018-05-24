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
public class ServiceHelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello/remote")
    public String getRemote(){
        return restTemplate.getForObject("http://localhost:9302/user/remote", String.class);
    }
    @RequestMapping("/hello/local")
    public String getInfo(){
        return "This is service-hello";
    }

}
