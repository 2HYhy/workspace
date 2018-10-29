package com.me.gacl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/6/19
 * RestTemplate的四种请求方式,基于SpringCloud
 */
@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 关于ResponseEntity的返回
     * @return
     */
    @RequestMapping("/getEntity")
    public String getRemote() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://EUREKA-CLIENT/client/user/6",String.class);
        String body = response.getBody();
        HttpStatus statusCode = response.getStatusCode();
        HttpHeaders headers = response.getHeaders();
        return body + "<br/>" + statusCode + "<br/>" + headers;
    }

    /**
     * 关于url中传递参数
     * @return
     */
    @RequestMapping("/withParam")
    public String withParam() {
        ResponseEntity<String> response1 = restTemplate.getForEntity("http://EUREKA-CLIENT/client/hello?id={1}",String.class, 6);
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put("my_id", 6);
        ResponseEntity<String> response2 = restTemplate.getForEntity("http://EUREKA-CLIENT/client/hello?id={my_id}",String.class, map);
        String code = response1.getStatusCodeValue() + " & " + response2.getStatusCodeValue();
        return code;
    }

    /**
     * 关于第一个参数是构造的url
     * @return
     */
    @RequestMapping("/createUri")
    public String createUri() {
        UriComponents components = UriComponentsBuilder.fromUriString("http://EUREKA-CLIENT/client/hello?id={1}").build().expand(6).encode();
        URI uri = components.toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

}
