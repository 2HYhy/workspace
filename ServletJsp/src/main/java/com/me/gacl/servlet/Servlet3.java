package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "Servlet3")
public class Servlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //两种输出流
        funWriterStream(response);
        funOutputStream(response);
    }

    public void funWriterStream(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out1 = response.getWriter();
        String data1 = "陕西西安";
        out1.write(data1);
        out1.write("<br/>" + 20170414 + "");
    }

    public void funOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        OutputStream out2 = response.getOutputStream();
        String data2 = "四川成都";
        byte[] by = data2.getBytes("UTF-8");
        out2.write(by);
        out2.write(("<br/>" + 20170414 + "").getBytes());
    }
}
