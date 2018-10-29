package com.me.hyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/8/6
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @RequestMapping("/header")
    public String header(@RequestHeader String name,
                         @RequestHeader String password){
        return "header contains "+ name+", "+ password;
    }

    @RequestMapping("/hello")
    public String getHello(@RequestParam Integer id) {
        return "This is eureka-client hello method, id = "+id;
    }

    @RequestMapping(value ="/user/{id}")
    public String getUser(@PathVariable("id") Integer id) {
        return "This is eureka-client user method, id = " + id;
    }

    /**
     * 使用eureka的DiscoveryClient类，通过服务实例名从注册中心获取该服务实例的具体信息
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/service")
    public String service() {
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-client");
        for (int i = 0; i < instances.size(); i++) {
            System.out.println("host:" + instances.get(i).getHost() + ", port:"+instances.get(i).getPort()+", service_id:" + instances.get(i).getServiceId());
        }
        return "---打印服务eureka-client的相关信息---";
    }
}
