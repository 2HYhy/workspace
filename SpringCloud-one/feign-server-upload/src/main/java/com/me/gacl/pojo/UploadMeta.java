package com.me.gacl.pojo;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
public class UploadMeta {

    private String username;

    private Integer position;

    public UploadMeta() {
    }

    public UploadMeta(String username, Integer position) {
        this.username = username;
        this.position = position;
    }

    public UploadMeta(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
