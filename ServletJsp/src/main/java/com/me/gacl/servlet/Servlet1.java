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
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "Servlet")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取单个web.xml中servlet标签中的init-param
        ServletConfig scg=this.getServletConfig();
        String value1=scg.getInitParameter("username");
        response.getWriter().println("username="+value1);
        //获取web.xml中servlet标签中的init-param集合
        Enumeration e=scg.getInitParameterNames();
        while(e.hasMoreElements()){
            String key= (String) e.nextElement();
            String value=scg.getInitParameter(key);
            response.getWriter().println(key+" = "+value);
        }
        //获取web.xml中的context-param
        ServletContext sct=this.getServletContext();
        String value2=sct.getInitParameter("myUrl");
        response.getWriter().println("myUrl= "+value2);
    }
}
