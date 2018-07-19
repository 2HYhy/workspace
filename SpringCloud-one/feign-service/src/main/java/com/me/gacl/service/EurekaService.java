package com.me.gacl.service;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CH-yfy
 * @date 2018/7/6
 */
@FeignClient(value = "eureka-server", url = "http://127.0.0.1:9000")
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface EurekaService {

    /**
     * 调用远程服务注册中心接口，获取某个服务的注册实例信息
     * @param appName
     * @return
     */
    @RequestMapping(value = "/eureka/apps/{appName}", method = RequestMethod.GET)
    @Headers("Accept:application/json")
    JSONObject testEureka(@PathVariable("appName")String appName);
}
