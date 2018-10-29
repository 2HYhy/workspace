package com.me.hyh.service;

import com.me.hyh.UserDO;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author CH-yfy
 * @date 2018/8/13
 */
public interface RemoteService {

    /**
     * 调用eureka-feign的API接口
     * 参数使用JSONObject或者Map时，会报错406
     * @param name
     * @param reqJson
     * @param token
     * @return
     */
    @RequestLine("POST feign/save")
    @Body("name={name}&reqJson={reqJson}&token={token}")
    String saveInfo(@Param("name") String name,
                    @Param("reqJson") String reqJson,
                    @Param("token") String token);

    /**
     * 调用eureka-feign的API接口
     * @return
     */
    @RequestLine("GET feign/get")
    String getInfo();

    /**
     * 调用eureka-feign的API接口
     * @param user
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST feign/post")
    void postUser(UserDO user);
}
