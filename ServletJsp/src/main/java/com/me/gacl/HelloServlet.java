package com.me.gacl;

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
@WebServlet(value = "HelloServlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public void init() throws ServletException{
        message="Hello World !!!";
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.write("<h4>"+message+"</h4>");
    }
}
