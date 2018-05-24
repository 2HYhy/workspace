package com.example.demo.dao;

import com.example.demo.bean.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author CH-yfy
 * @date 2017/12/5
 */
@Component
public interface UserInfoDAO {


    int add(UserInfo user);

    UserInfo get(String uid);

}
