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
 * @author momo
 * @date 2018/6/19
 */
@RestController
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 关于ResponseEntity的返回
     * @return
     */
    @RequestMapping("/getRemote")
    public String getRemote() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://EUREKA-CLIENT/client/user?name=apple",String.class);
        String body = response.getBody();
        HttpStatus statusCode = response.getStatusCode();
        HttpHeaders headers = response.getHeaders();
        StringBuffer buffer = new StringBuffer();
        buffer.append(body).append("<br/>").append(statusCode).append("<br/>").append(headers);
        return buffer.toString();
    }

    /**
     * 关于url中传递参数
     * @return
     */
    @RequestMapping("/withParam")
    public String withParam() {
        ResponseEntity<String> response1 = restTemplate.getForEntity("http://EUREKA-CLIENT/client/user?name={1}",String.class, "apple");
        Map<String, String> map = new HashMap<>(10);
        map.put("my_name", "apple");
        ResponseEntity<String> response2 = restTemplate.getForEntity("http://EUREKA-CLIENT/client/user?name={my_name}",String.class, map);
        String code = response1.getStatusCodeValue() + "&" + response2.getStatusCodeValue();
        return code;
    }

    /**
     * 关于第一个参数是构造的url
     * @return
     */
    @RequestMapping("/createUri")
    public String createUri() {
        UriComponents components = UriComponentsBuilder.fromUriString("http://EUREKA-CLIENT/client/user?name={1}").build().expand("apple").encode();
        URI uri = components.toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

}
