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
@WebServlet(value = "Servlet2")
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getRequestURL().toString();//完整的求情URL
        String uri=request.getRequestURI().toString();//请求行中的资源名部分
        String param=request.getQueryString();//URL中的参数
        String raddr=request.getRemoteAddr();//客户端IP地址
        String rhost=request.getRemoteHost();//客户端主机名
        int port=request.getRemotePort();//网络端口号
        String laddr=request.getLocalAddr();//服务器IP地址
        String lname=request.getLocalName();//服务器主机名
        response.setCharacterEncoding("UTF-8");//将字符以UTF-8编码输出至客户端浏览器
        response.setHeader("content-ype","text/html;charset=UTF-8");
        response.getWriter().println("url="+url);//输出
        PrintWriter out=response.getWriter();//输出
        out.println("uri="+uri);
        out.write("rhost="+rhost);
        out.write("<br/>");
        out.println("raddr="+raddr);
        out.println("laddr="+laddr);
        out.println("lname="+lname);
        out.println("port="+port);
        out.println("param="+param);

        //请求转发与重定向
        String data1 = "请求转发是指：一个web资源收到客户端请求后，通知服务器去调用另一个web资源进行处理！";
        String data2 = "请求重定向是指：一个web资源收到客户端请求后，通知浏览器去调用另一个web资源进行处理！";
        request.setAttribute("Data1", data1);
        request.setAttribute("Data2", data2);
        request.getRequestDispatcher("/six.jsp").forward(request, response);
    }
}
