package com.example.demo.dao;

import com.example.demo.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int add(UserInfo user) {
        return jdbcTemplate.update("insert into user_social(uid,realname,province,city) values(?,?,?,?)",
                user.getUid(),user.getRealName(),user.getProvince(),user.getCity());
    }

    @Override
    public UserInfo get(String uid) {
        List<UserInfo> list = jdbcTemplate.query("select * from user_social where uid=?",
                new Object[]{uid}, new BeanPropertyRowMapper<>(UserInfo.class));
        if (list.size()> 0) {
            UserInfo user = list.get(0);
            return user;
        } else {
            return null;
        }
    }
}
