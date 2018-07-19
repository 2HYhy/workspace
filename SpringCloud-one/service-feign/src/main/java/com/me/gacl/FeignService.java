package com.me.gacl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CH-yfy
 * @date 2018/4/18
 * 通过@FeignClient(name/value ="服务名")指定调用哪个服务,以下2种方式效果一样，url是client服务的访问地址
 */
//@FeignClient(name = "my-service-demo", url = "http://localhost:9001", fallback = HystrixServiceImpl.class)
@FeignClient(name = "eureka-client", fallback = HystrixServiceImpl.class)
public interface FeignService {

    /**
     * 此处调用eureka-client的/client/hello接口
     * @return
     */
    @RequestMapping(value = "/client/hello", method = RequestMethod.GET)
    String sayHello();

    /**
     * 此处调用eureka-client的/client/user接口,@RequestParam注解不能缺少
     * @param name
     * @return
     */
    @RequestMapping("/client/user")
    String getUser(@RequestParam("name") String name);

    /**
     *  此处调用eureka-client的/client/deleteIt接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/client/delete/{id}", method = RequestMethod.DELETE)
    String deleteIt(@PathVariable("id") Integer id);
}
