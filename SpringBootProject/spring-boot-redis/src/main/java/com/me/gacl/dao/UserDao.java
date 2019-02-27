package com.me.gacl.dao;

import com.google.common.collect.Lists;
import com.me.gacl.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author CH-yfy
 * @date 2018/6/26
 * AtomicLong是长原子类，相当于用synchronized的long型
 * 32位的操作系统中会将64位的long和double分离成两个32位操作而不具备原子性，使用AutomicLong是为了保证long的原子性
 * 不涉及数据库操作
 */

@Component
public class UserDao {

    private static List<User> users = Lists.newArrayList();
    private static AtomicLong SEQ = new AtomicLong(0L);

    public User save() {
        User user = new User();
        user.setId(SEQ.get());
        user.setName("hyh" + SEQ.get());
        user.setBirthday(new Date());
        users.add(user);
        SEQ.getAndIncrement();
        return user;
    }

    public User update(Long id) {
        //以此形式省略SQL查询语句
        for(User u : users) {
            if (u.getId().equals(id)) {
                u.setName("newName");
            }
            return u;
        }
        return null;
    }

    public List<User> find() {
        return users;
    }

    public User find(String name) {
        for(User u : users) {
            if(u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public User find(Long id) {
        for(User u : users) {
            if(u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public User delete(Long id) {
        for(User u : users) {
            if(u.getId().equals(id)) {
                users.remove(u);
                return u;
            }
        }
        return null;
    }
}
