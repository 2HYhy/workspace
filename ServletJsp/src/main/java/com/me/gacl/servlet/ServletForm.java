package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "ServletForm")
public class ServletForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String title="<-读取数据->";
        //处理中文
        String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        String url=request.getParameter("url");
        out.println(title+ "\n" +"站点名："+name+ "\n" +"网址："+url);
        System.out.println("菜鸟教程");

    }
}
