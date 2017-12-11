package com.example.demo.dao;

import com.example.demo.bean.MongoBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */

@Component
public interface MongoDAO extends MongoRepository<MongoBean, String> {

    /**
     * 根据用户名查找
     * @param userName
     * @return
     */
    MongoBean findByUserName(String userName);

}
