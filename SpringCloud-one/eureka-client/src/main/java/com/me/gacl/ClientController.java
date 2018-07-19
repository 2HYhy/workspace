package com.me.gacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public String getUser(@RequestParam("name")String name) throws InterruptedException {
        //休眠5秒TimeUnit.MILLISECONDS.sleep(5000)
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

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public String json(@RequestBody Map<String, String> json){
        return "json= "+ json;
    }

    @RequestMapping("/header")
    public String header(@RequestHeader String name, @RequestHeader String password){
        return "header contains "+ name+", "+ password;
    }

}
