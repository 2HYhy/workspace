package com.me.gacl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过restTemplate来消费服务的接口
     * 创建了熔断器，并指定了熔断方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "sayError")
    public String sayHello() {
        String result = restTemplate.getForObject("http://EUREKA-CLIENT/client/hello", String.class);
        return result;
    }

    @HystrixCommand(fallbackMethod = "getError")
    public String getUser(String name) {
        return restTemplate.getForObject("http://EUREKA-CLIENT/client/user?name="+name, String.class);
    }

    /**
     * 当eureka-client服务不可用时，返回该字符串
     * @return
     */
    public String sayError() {
        return "Sorry, service error, ribbon";
    }

    /**
     * 异常方法的形参必须与原方法保持一致
     * @param name
     * @return
     */
    public String getError(String name) {
        return "Sorry, service error, ribbon + "+name;
    }

}