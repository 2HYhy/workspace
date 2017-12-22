package com.me.gacl.domain;

import java.util.Date;

/**
 * Created by yunhua.he on 2017/9/29.
 */
public class User {
    private String username;
    private String password;
    private String id;
    private Date regtime;

    public String getId() {
        return id;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String uid,String name,String pwd){
        id=uid;
        username=name;
        password=pwd;
    }
}
