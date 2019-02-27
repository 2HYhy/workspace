package com.me.gacl.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * @author CH-yfy
 * @date 2017/12/21
 * JavaBean可以感知自己被活化（反序列化）或钝化（序列化）
 */
public class BeanActivationListener implements HttpSessionActivationListener, Serializable {
    private static final long serialVersionUID = -7163292262242638063L;

    private String beanname;
    public BeanActivationListener(String var) {
        beanname = var;
    }
    public String getBeanname() {
        return beanname;
    }

    public void setBeanname(String beanname) {
        this.beanname = beanname;
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println(beanname+"和session一起从硬盘序列化(活化)到硬盘了，" +
                "session的id是："+httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println(beanname+"和session一起从硬盘反序列化(活化)回到内存了，" +
                "session的id是："+httpSessionEvent.getSession().getId());
    }
}


