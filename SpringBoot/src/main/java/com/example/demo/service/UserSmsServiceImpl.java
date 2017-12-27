package com.example.demo.service;

import com.example.demo.bean.UserSms;
import com.example.demo.dao.UserSmsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2017/12/5
 */

@Service
public class UserSmsServiceImpl implements UserSmsService {

    @Autowired
    private UserSmsMapper userSmsMapper;

    @Override
    public int add(UserSms userSms) {
        return userSmsMapper.add(userSms);
    }

    @Override
    public List<UserSms> get(String pkgName) {
        return userSmsMapper.get(pkgName);
    }

    @Override
    public int update(String content, String sendTime) {
        return userSmsMapper.update(content, sendTime);
    }
}
