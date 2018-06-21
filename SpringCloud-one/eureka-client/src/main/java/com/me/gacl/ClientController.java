package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/user")
    public String getUser(@RequestParam("name")String name) {
        return "service producer, the user's name = "+name;
    }

    @RequestMapping("/service")
    public String service() {
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-client");
        for (int i = 0; i < instances.size(); i++) {
            System.out.println("host:" + instances.get(i).getHost() + ",service_id:" + instances.get(i).getServiceId());
        }
        int size = instances.size();
        return "打印服务eureka-client的相关信息,instance.size="+size;
    }

}
