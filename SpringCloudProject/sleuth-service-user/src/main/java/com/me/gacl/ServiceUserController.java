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
        long sTime = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:9012/hello/local",String.class);
        long eTime = System.currentTimeMillis();
        return sTime + ",\t" + result + ",\t" + eTime;
    }

    @RequestMapping("/user/local")
    public String getLocal(){
        return "This is service-user";
    }

    /**
     * 作为服务提供者供hello的rest访问
     * @return
     */
    @RequestMapping("/user/getUser")
    public String getUser(){
        return "This is sleuth-service-user's getUser";
    }
}
