package com.me.gacl.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/7/9
 */
@FeignClient("eureka-client")
public interface FeignService {

    /**
     * 调用远程eureka-client服务的hello接口
     * @return
     */
    @RequestMapping("/client/hello")
    String getHello();

    /**
     * spring mvc中，若@RequestParam注解不指定value,就默认使用参数名作为其value,但feign中若不指定Value，启动就会报错
     * @param name
     * @return
     */
    @RequestMapping("client/user")
    String getUser(@RequestParam("name") String name);

    /**
     * post请求参数传递
     * @param map
     * @return
     */
    @RequestMapping(value = "/client/json", method = RequestMethod.POST)
    String getJson(@RequestBody Map<String,String> map);

    /**
     * spring mvc中，若@RequestHeader注解不指定value,就默认使用参数名作为其value,但feign中若不指定Value，启动就会报错
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("/client/header")
    String getHeader(@RequestHeader("name") String name, @RequestHeader("password") String pwd);
}
