package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author CH-yfy
 * @date 2017/12/18
 * 不进入filter中
 */
@WebServlet(name = "/servlet3", urlPatterns = "/servlet3")
public class Servlet3 extends HttpServlet {
    private static final long serialVersionUID = -1776393649471013918L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        //服务端获取Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.getWriter().println("It is first enter, no cookie!");
        } else {
            for (Cookie c : cookies) {
                String name = c.getName();
                String value = c.getValue();
                response.getWriter().println("cookie-name=" + name + ", cookie-value=" + value);
            }
            //服务端添加Cookie
            Cookie cookie = new Cookie("token", System.currentTimeMillis() + "");
            //表示将其存储到哪个域，对哪个域是有效的
            cookie.setDomain("127.0.0.1");
            //表示其影响到的路径
            cookie.setPath("/");
            //表示其多少秒过期
            cookie.setMaxAge(3600);
            //设置安全属性为true后，cookie只能在https等安全性协议中传输,不能在http非安全协议中传输
            //cookie.setSecure(true);
            response.addCookie(cookie);

            //创建session
            HttpSession session = request.getSession();
            session.setAttribute("session-key", "session-value");
            session.setAttribute("username", "pineapple");
            //设置超时时间为60s，0或负数表示永不超时
            session.setMaxInactiveInterval(60);
            //获取session
            String sessionId = session.getId();
            String attribute = (String) session.getAttribute("session-key");
            response.getWriter().println("sessionId=" + sessionId + ", attribute=" + attribute);
            //遍历session的key集合
            Enumeration<String> list = session.getAttributeNames();
            while(list.hasMoreElements()) {
                String key = list.nextElement();
                response.getWriter().println("key in session=" + key);
            }
            //删除session
            //session.invalidate();
        }
    }
}

