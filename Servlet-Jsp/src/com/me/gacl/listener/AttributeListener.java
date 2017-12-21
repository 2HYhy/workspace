package com.me.gacl.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.text.MessageFormat;

/**
 * @author yunhua.he
 * @date 2017/12/20
 * 监听域对象中属性的变更的监听器(一次性继承三者包含)
 */
public class AttributeListener implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {

    //ServletContextAttributeListener监听器
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        String result = MessageFormat.format("ServletContext域对象 add attribute: key={0}, value={1}",
                scae.getName(), scae.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        String result = MessageFormat.format("ServletContext域对象 remove attribute: key={0}, value={1}",
                scae.getName(), scae.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        String result = MessageFormat.format("ServletContext域对象 replace attribute: key={0}", scae.getName());
        System.out.println(result);
    }

    //ServletRequestAttributeListener监听器
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String result = MessageFormat.format("ServletRequest域对象 add attribute: key={0}, value={1}",
                srae.getName(), srae.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        String result = MessageFormat.format("ServletRequest域对象 remove attribute: key={0}, value={1}",
                srae.getName(), srae.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        String result = MessageFormat.format("ServletRequest域对象 replace attribute: key={0}", srae.getName());
        System.out.println(result);

    }

    //HttpSessionAttributeListener监听器
    @Override
    public void attributeAdded(HttpSessionBindingEvent hbe) {
        String result = MessageFormat.format("HttpSession域对象 add attribute: key={0}, value={1}",
                hbe.getName(), hbe.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent hbe) {
        String result = MessageFormat.format("HttpSession域对象 remove attribute: key={0}, value={1}",
                hbe.getName(), hbe.getValue());
        System.out.println(result);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent hbe) {
        String result = MessageFormat.format("HttpSession域对象 replace attribute: key={0}", hbe.getName());
        System.out.println(result);
    }
}
