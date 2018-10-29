package com.me.hyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.client.RestTemplate;
import ribbon.config.RibbonConfig;

/**
 * @author CH-yfy
 * @date 2018/8/8
 * 3. 通过@RibbonClient指定策略的方式
 */
@RibbonClient(name = "eureka-feign", configuration = RibbonConfig.class)
public class AnotherService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 该接口使用RibbonConfig中定义的负载均衡策略
     * @return
     */
    String getFeign() {
        return restTemplate.getForObject("http://eureka-feign/feign", String.class);
    }
}
