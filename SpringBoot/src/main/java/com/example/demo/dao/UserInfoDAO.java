package com.example.demo.dao;

import com.example.demo.bean.UserInfo;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */
public interface UserInfoDAO {

    int add(UserInfo user);

    UserInfo get(String uid);

}
