package com.example.demo.controller;

import com.example.demo.bean.UserSms;
import com.example.demo.service.UserSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2017/12/5
 * spring boot整合mybatis
 */

@RestController
@RequestMapping("/sms")
public class UserSmsController {

    @Autowired
    UserSmsService userSmsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UserSms user) {
        int flag = userSmsService.add(user);
        if (flag == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam String pkgName){
        List<UserSms> user = userSmsService.get(pkgName);
        if (user != null) {
            System.out.println("size=" + user.size());
            return user.get(0).toString();
        } else {
            return "This user is not exist !";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam String content, @RequestParam String sendTime) {
        int flag = userSmsService.update(content, sendTime);
        if (flag == 1) {
            return "success";
        } else {
            return "fail";
        }
    }
}
