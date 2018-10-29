package com.me.hyh.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CH-yfy
 * @date 2018/8/10
 * @FeignClient 代理的是注册在服务中心的任一服务,本工程也必须注册在同一服务中心,自动实现负载均衡
 * 本工程是注册在服务中心的
 */
@FeignClient(value = "eureka-client", fallback = HystrixService.class)
public interface FeignService {

    /**
     * 调用远程eureka-client的接口
     * @return
     */
    @RequestMapping("/client")
    String getClient();

    /**
     * 请求远程eureka-client的接口
     * feign使用@RequestParam注解的方式与spring mvc不一样,若value=null会报错
     * @param id
     * @return
     */
    @RequestMapping("/client/hello")
    String getHello(@RequestParam("id") Integer id);

    /**
     * 请求远程eureka-client的接口
     * feign使用@PathVariable注解的方式与spring mvc不一样,若value=null会报错
     * @param id
     * @return
     */
    @RequestMapping("/client/user/{id}")
    String getUser(@PathVariable("id") Integer id);
}
