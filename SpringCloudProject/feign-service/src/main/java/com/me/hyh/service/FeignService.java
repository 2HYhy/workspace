package com.me.hyh.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CH-yfy
 * @date 2018/8/10
 * @FeignClient 代理的是远程服务(是否注册到服务中心都可以),本工程是否注册到服务中心都可以
 * 本工程没有注册到服务中心，eureka-client-too是eureka-client的引用,指向地址在配置文件中
 * 没有配置fallback方法，若发生超时则直接抛出异常
 */
//@FeignClient(value = "eureka-client-too", url = "http://127.0.0.1:9001/")  //无法实现负载均衡
@FeignClient(value = "eureka-client-too")
public interface FeignService {

    /**
     * 调用远程服务接口
     * @return
     */
    @RequestMapping("/client")
    String getClient();

    /**
     * 调用远程服务接口
     * @param id
     * @return
     */
    @RequestMapping("/client/hello")
    String getHello(@RequestParam("id") Integer id);

    /**
     * 调用远程服务接口
     * @param id
     * @return
     */
    @RequestMapping("/client/user/{id}")
    String getUser(@PathVariable("id") Integer id);
}
