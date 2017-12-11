package com.example.demo.bean;

/**
 * @author yunhua.he
 * @date 2017/12/11
 */
public class Student {

    private int stuId;
    private String username;
    private String sex;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
