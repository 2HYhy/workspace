package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author yunhua.he
 * @date 2017/12/18
 * cookie, 一种将会话数据保存在浏览器客户端的技术
 * session, 一种将会话数据保存在服务器端的技术
 */
@WebServlet(value = "/s3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        //服务端获取Cookie
        Cookie [] cookies = request.getCookies();
        if (cookies == null) {
            response.getWriter().println("It is first enter, no cookie!");
        } else {
            for (Cookie c : cookies) {
                String name = c.getName();
                String value = c.getValue();
                response.getWriter().println("cookie-name= "+name+", cookie-value= "+value);
            }
        }
        //服务端添加Cookie
        Cookie cookie = new Cookie("lastTime", System.currentTimeMillis()+"");
        //设置有效期为1天，若为0即是删除cookie
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);

        //创建session
        HttpSession session = request.getSession();
        session.setAttribute("session-key", "session-value");
        //获取session
        String sessionId = session.getId();
        String attribute = (String)session.getAttribute("session-key");
        response.getWriter().println("id= "+sessionId+", attribute= "+attribute);
    }
}
