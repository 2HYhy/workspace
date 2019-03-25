package com.me.hyh.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Ch-yfy
 * @date 2019/3/12
 */
@RestController
@RequestMapping("/gateway")
public class TestGatewayController {

    @GetMapping("/header")
    public String test1(HttpServletRequest request) {
        System.out.println("---enter---");
        return request.getHeader("Username") + "\n" + request.getHeader("UserAddress");

//        Enumeration<String> enums =  request.getHeaderNames();
//        while (enums.hasMoreElements()) {
//            String key = enums.nextElement();
//            String value = request.getHeader(key);
//            System.out.println(key + " : " + value);
//        }
    }

    @GetMapping("/other")
    public String test2(HttpServletRequest request) {
        System.out.println("---enter---");
        String nickname = request.getParameter("nickname");
        if (nickname != null) {
            return request.getRequestURI() + "\n" + nickname;
        } else {
            return "Path /gateway/other In TestGatewayController";
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String test3(@RequestBody Map map) {
        System.out.println("map = " + map);
        return map.toString();
    }

    @RequestMapping(value = "/string", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String test4(@RequestBody String data) {
        System.out.println("data = " + data);
        return data;
    }


}
