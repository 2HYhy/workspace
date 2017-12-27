package com.me.gacl.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author CH-yfy
 * @date 2017/12/20
 * 监听ServletRequest域对象的创建和销毁
 * 每次访问创建，访问结束销毁
 */
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("===监听"+servletRequestEvent.getServletRequest()+"的创建===");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("===监听ServletRequest的销毁===");
    }
}
