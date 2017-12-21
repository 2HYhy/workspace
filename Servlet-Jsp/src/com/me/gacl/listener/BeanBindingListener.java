package com.me.gacl.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author yunhua.he
 * @date 2017/12/21
 * 实现该接口的javabean对象可以感知自己被绑定到session中或者从session中删除
 */
public class BeanBindingListener implements HttpSessionBindingListener {

    private String address;
    public BeanBindingListener(String var) {
        this.address = var;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println(address+" is binding into session");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println(address+" is out of session");
    }
}
