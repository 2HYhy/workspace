package com.me.gacl.service;

import feign.Body;
import feign.Param;
import feign.RequestLine;

/**
 * @author CH-yfy
 * @date 2018/4/27
 */
public interface FeignService {

    /**
     * post 提交
     * @param name
     * @param reqJson
     * @param token
     * @return
     */
    @RequestLine("POST /hyh/save")
    @Body("name={type}&reqJson={json}&token={token}")
    String saveInfo(@Param("name") String name,
                    @Param("reqJson") String reqJson,
                    @Param("token") String token);

    /**
     * get提交
     * @return
     */
    @RequestLine("GET /hyh/get")
    String func2();
}
