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
@WebServlet(value = "ServletCheckbox")
public class ServletCheckbox extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "<-读取复选框数据->";
        out.println(title
                + "\nJava标识：" + request.getParameter("java")
                + "\nPython标识：" + request.getParameter("python")
                + "\nGolang标识：" + request.getParameter("golang"));
    }
}
