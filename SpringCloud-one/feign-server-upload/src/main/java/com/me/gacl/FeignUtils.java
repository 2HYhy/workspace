package com.me.gacl;

import com.me.gacl.service.FeignService;
import feign.Feign;
import feign.Request;
import feign.Retryer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/27
 */
public class FeignUtils {

    public static void main(String [] args) {
        FeignService service = Feign.builder()
                //指定连接超时时长及响应超时时长
                .options(new Request.Options(1000, 3500))
                //指定重试策略
                .retryer(new Retryer.Default(5000, 5000, 3))
//                .decoder(new GsonDecoder())
//                .encoder(new GsonEncoder())
                //绑定接口与服务端地址
                .target(FeignService.class, "http://localhost:8080");

        Map<String, String> reqMap = new HashMap<>(10);
        reqMap.put("id","1006");
        reqMap.put("code","xx060910");
        reqMap.put("level","medium");
        String token = "token-token-token";
        String reqJson = String.valueOf(reqMap);
        System.out.println(service.saveInfo("saveInfo", reqJson, token));
        System.out.println(service.func2());
    }
}
