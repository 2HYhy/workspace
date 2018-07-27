package com.me.gacl;

import com.me.gacl.source.RibbonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @RequestMapping("/ribbon/hello")
    public String sayHello() {
        return ribbonService.sayHello();
    }

    @RequestMapping("/ribbon/user")
    public String getUser(@RequestParam("name") String name) {
        return ribbonService.getUser(name);
    }

    /**
     * eureka-client的9001，9002不是轮询出现，而是随机出现的
     * @return
     * 此处不能使用自动注入的restTemplate，否则会报错(no instance...)
     */
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/ribbon/strategy")
    public String byStrategy() {
        ServiceInstance instance = this.loadBalancerClient.choose("EUREKA-CLIENT");
        URI uri = URI.create(String.format("http://%s:%s/ribbon/test", instance.getHost(),instance.getPort()));
        System.out.println("uri= "+uri);
        return new RestTemplate().getForEntity(uri, String.class).getBody();
    }

    @Autowired
    RibbonConfig ribbonConfig;

    @GetMapping("/ribbon/sourceCode")
    public String bySourceCode() {
        ServiceInstance instance = ribbonConfig.choose("EUREKA-CLIENT");
        URI uri = URI.create(String.format("http://%s:%s/ribbon/test", instance.getHost(),instance.getPort()));
        System.out.println("uri= "+uri);
        return new RestTemplate().getForEntity(uri, String.class).getBody();
    }

}
