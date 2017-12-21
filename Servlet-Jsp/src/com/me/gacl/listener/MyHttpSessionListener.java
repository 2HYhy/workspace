package com.me.gacl.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author yunhua.he
 * @date 2017/12/20
 * 监听HttpSession域对象的创建和销毁
 * 访问jsp页面时创建，timeout时间到时销毁
 */
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println(hse.getSession()+" has built");
        System.out.println("创建好的HttpSession的id= "+hse.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println(hse.getSession()+" has destroyed");
    }
}
