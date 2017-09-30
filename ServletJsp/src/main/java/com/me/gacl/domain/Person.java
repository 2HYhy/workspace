package com.me.gacl.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunhua.he on 2017/9/29.
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

    public static void main(String[] args){
        Person per =new Person();
        per.setName("hyh");
        per.setBirth(new Date());
        System.out.println("person="+ per);

        String p1= null;
        String p2= "apple";
        String p3 = "wodemama";
        JSONObject json = new JSONObject();
        json.put("key1",p1);
        json.put("key2",p2);
        json.put("key3",p3);
        System.out.println("json="+json.toJSONString());

        String pp1= null;
        String pp2= "apple";
        String pp3 = "wodemama";
        Map<String,String> map = new HashMap<>();
        map.put("keyy1",pp1);
        map.put("keyy2",pp2);
        map.put("keyy3",pp3);
        System.out.println("map="+map);

    }
}
