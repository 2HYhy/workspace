package com.me.gacl.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author CH-yfy
 * @date 2017/12/18
 * servlet用于交互式地浏览和修改数据，生成动态web内容
 */
//等价于<servlet-name>
@WebServlet(name = "Servlet1")
public class Servlet1 extends HttpServlet {
    private static final long serialVersionUID = 8363941073341951936L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取web.xml，<servlet>的单个<init-param>
        ServletConfig scg = this.getServletConfig();
        String value1 = scg.getInitParameter("username");
        response.getWriter().println("username="+value1);
        //获取web.xml中servlet标签中的init-param集合
        Enumeration e = scg.getInitParameterNames();
        while(e.hasMoreElements()){
            String key = (String) e.nextElement();
            String value = scg.getInitParameter(key);
            response.getWriter().println(key+" = "+value);
        }
        //获取web.xml中的context-param
        ServletContext sct = this.getServletContext();
        String value2 = sct.getInitParameter("localUrl");
        response.getWriter().println("localUrl= "+value2);
    }
}
