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
 */
@Component
public class UserDao {

    private static List<User> users = Lists.newArrayList();
    private static AtomicLong SEQ = new AtomicLong(0L);

    public User save() {
        User user = new User();
        user.setId(SEQ.get());
        user.setName("hyh" + SEQ.get());
        user.setBirthDay(new Date());
        users.add(user);
        SEQ.getAndIncrement();
        return user;
    }

    public User update(Long id) {
        for (User u : users) {
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
