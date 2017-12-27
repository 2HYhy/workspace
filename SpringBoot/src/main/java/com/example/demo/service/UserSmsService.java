package com.example.demo.service;

import com.example.demo.bean.UserSms;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2017/12/5
 */
public interface UserSmsService {

    int add(UserSms userSms);

    List<UserSms> get(String pkgName);

    int update(String content, String sendTime);
}
