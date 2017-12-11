package com.me.gacl.domain;

/**
 * Created by yunhua.he on 2017/8/23.
 */
public class Person {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "person{id="+id+",name="+name+",age="+age+"}";
    }
}
