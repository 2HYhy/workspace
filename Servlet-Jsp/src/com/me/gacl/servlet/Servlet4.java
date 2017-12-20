package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yunhua.he
 * @date 2017/12/18
 */
@WebServlet("/servlet4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求转发与重定向
        String data1 = "请求转发是指：一个web资源收到客户端请求后，通知服务器去调用另一个web资源进行处理！";
        String data2 = "请求重定向是指：一个web资源收到客户端请求后，通知浏览器去调用另一个web资源进行处理！";
        request.setAttribute("Data1", data1);
        request.setAttribute("Data2", data2);
        request.getRequestDispatcher("/servlet.jsp").forward(request, response);
    }
}
