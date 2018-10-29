package com.me.hyh;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * @date 2018/8/7
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 远程调用eureka-client的3个接口
     * @return
     */
    @HystrixCommand(fallbackMethod = "cError")
    String getClient() {
        return restTemplate.getForObject("http://EUREKA-CLIENT/client", String.class);
    }

    @HystrixCommand(fallbackMethod = "uError")
    String getUser(Integer id) {
        return restTemplate.getForEntity("http://EUREKA-CLIENT/client/user/{id}", String.class, id).getBody();
    }

    @HystrixCommand(fallbackMethod = "hError")
    String getHello(Integer id) {
        return restTemplate.getForEntity("http://EUREKA-CLIENT/client/hello?id={1}", String.class, id).getBody();
    }

    /**
     * 服务降级函数
     * 必须和@HystrixCommand处于同一个类中，形参以及返回值必须与原方法保持一致
     * @return
     */
    private String cError() {
        return "client occurred error !";
    }

    private String uError(Integer id) {
        return "user occurred error !";
    }

    private String hError(Integer id) {
        return "hello occurred error !";
    }
}
