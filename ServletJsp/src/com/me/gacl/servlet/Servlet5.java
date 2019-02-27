package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author CH-yfy
 * @date 2017/12/18
 */
@WebServlet(name = "Servlet5", urlPatterns = "/servlet/servlet5")
public class Servlet5 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/one.jsp").forward(request,response);
//        request.getRequestDispatcher("/listener.jsp").forward(request,response);
        request.getRequestDispatcher("/attributeListen.jsp").forward(request,response);
    }
}
