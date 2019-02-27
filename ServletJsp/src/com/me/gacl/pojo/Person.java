package com.me.gacl.pojo;

import java.util.Date;

/**
 * Created by CH-yfy on 2017/9/29.
 */
public class Person {
    private String name;
    private int age;
    private boolean marry ;
    private Date birth;
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarry() {
        return marry;
    }

    public void setMarry(boolean marry) {
        this.marry = marry;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }
}
