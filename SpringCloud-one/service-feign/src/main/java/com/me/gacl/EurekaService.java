package com.me.gacl;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CH-yfy
 * @date 2018/7/5
 * 要求: @FeignClient代理的必须是服务注册中心的地址, 该工程本身是否注册到服务中心都可以
 */
@FeignClient(value = "eureka-server", url = "http://127.0.0.1:9000")
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface EurekaService {

    /**
     * 根据某个应用名获取其所有实例的信息
     * 该接口返回格式默认为xml，改成json
     * @param appName
     * @return
     */
    @RequestMapping(value = "/eureka/apps/{appName}", method = RequestMethod.GET)
    @Headers("Accept:application/json")
    JSONObject testEureka(@PathVariable("appName")String appName);
}
