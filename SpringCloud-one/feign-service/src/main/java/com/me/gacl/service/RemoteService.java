package com.me.gacl.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CH-yfy
 * @date 2018/7/2
 */
@FeignClient(name = "remote-client-service")
public interface RemoteService {

    /**
     * Spring Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到Spring容器中
     * @return
     */
    @RequestMapping(value = "/client/hello", method = RequestMethod.GET)
    String testHttp();
}
