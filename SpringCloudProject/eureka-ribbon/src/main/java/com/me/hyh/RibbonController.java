package com.me.hyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author CH-yfy
 * @date 2018/8/7
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/client")
    public String getClient() {
        return ribbonService.getClient();
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Integer id) {
        return ribbonService.getUser(id);
    }

    @GetMapping("/hello")
    public String getHello(@RequestParam Integer id) {
        return ribbonService.getHello(id);
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/strategy")
    public String byStrategy() {
        ServiceInstance instance = this.loadBalancerClient.choose("EUREKA-CLIENT");
        URI uri = URI.create(String.format("http://%s:%s/client", instance.getHost(), instance.getPort()));
        System.out.println("uri= "+uri);
        return new RestTemplate().getForObject(uri, String.class);
    }


    @Autowired
    private AnotherService anotherService;
    @GetMapping("/feign")
    public String getFeign() {
        return anotherService.getFeign();
    }
}
