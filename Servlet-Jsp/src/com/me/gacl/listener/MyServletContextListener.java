package com.me.gacl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author yunhua.he
 * @date 2017/12/20
 * 监听ServletContext域对象的创建和销毁
 * 控制台看到打印输出：服务启动时创建，服务关闭时销毁
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("===监听事件源ServletContext对象的创建===");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("===监听事件源ServletContext对象的销毁===");
    }
}
