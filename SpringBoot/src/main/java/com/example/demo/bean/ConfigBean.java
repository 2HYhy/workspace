package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */

@ConfigurationProperties(prefix = "own")
public class ConfigBean {

    private int uid;
    private String major;
    private String message;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "uid=" + uid +
                ", major='" + major + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
