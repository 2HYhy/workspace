package com.me.hyh;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CH-yfy
 * @date 2018/8/27
 */
@FeignClient(name = "eureka-client", url = "http://localhost:9001")
public interface HystrixConfigService {

    /**
     * 调用远程eureka-client的hello方法
     * @param id
     * @return
     */
    @RequestMapping("/client/hello")
    String getClientHello(@RequestParam("id") Integer id);
}
