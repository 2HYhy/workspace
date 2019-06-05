package com.example.demo;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
public class UserDO {

    private Integer uid;
    private String name;
    private Integer age;
    private String address;

    public UserDO(Integer uid, String name, int age, String address) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
