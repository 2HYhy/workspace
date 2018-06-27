package com.me.gacl.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/6/26
 */
public class User implements Serializable {

    private static final long serialVersionUID = -7677014979883014372L;

    private Long id;
    private String name;
    private Date birthDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
