package com.example.demo.bean;

/**
 * @author CH-yfy
 * @date 2017/12/5
 * spring boot 整合mybatis(基于注解)
 */
public class UserSms {

    private String pkgName;
    private String content;
    private String sendTime;

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "UserSms{" +
                "pkgName='" + pkgName + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }
}
