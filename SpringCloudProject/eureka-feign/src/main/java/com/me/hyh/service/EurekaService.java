package com.me.hyh.service;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author CH-yfy
 * @date 2018/8/10
 * @FeignClient 代理的必须是服务注册中心的地址，本工程是否注册到eureka-server都可以
 */
@FeignClient(value = "eureka-server", url = "http://127.0.0.1:9000")
public interface EurekaService {

    /**
     * 根据微服务名(必须注册在服务中心)，获取其所有实例的信息
     * @param appName
     * @return
     */
    @RequestMapping(value = "/eureka/apps/{appName}",
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE)
    @Headers("Accept:application/json")
    JSONObject getInstances(@PathVariable("appName") String appName);
}