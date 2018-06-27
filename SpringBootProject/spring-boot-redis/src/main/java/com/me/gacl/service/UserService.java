package com.me.gacl.service;

import com.me.gacl.entity.User;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/6/26
 */
public interface UserService {

    User save();

    User update(Long id);

    List<User> find();

    User find(Long id);

    User find(String name);

    User delete(Long id);
}
