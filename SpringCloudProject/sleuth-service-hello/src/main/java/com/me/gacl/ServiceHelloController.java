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
        long sTime = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:9013/user/local", String.class);
        long eTime = System.currentTimeMillis();
        return sTime + ",\t" + result + ",\t" + eTime;
    }

    @RequestMapping("/hello/local")
    public String getInfo(){
        return "This is service-hello";
    }

    /**
     * 作为服务消费者调用user的rest服务
     * @return
     */
    @RequestMapping("/hello/getUser")
    public String getUser(){
        return restTemplate.getForObject("http://localhost:9013/user/getUser", String.class);
    }

}
