package com.example.demo.controller;

import com.example.demo.bean.UserInfo;
import com.example.demo.dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH-yfy
 * @date 2017/12/5
 * spring boot使用jdbc访问mysql
 */

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    UserInfoDAO userInfoDAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UserInfo user) {
        int flag = userInfoDAO.add(user);
        if (flag == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam String uid){
        UserInfo user = userInfoDAO.get(uid);
        if (user != null) {
            return user.toString();
        } else {
            return "This user is not exist !";
        }
    }

}
