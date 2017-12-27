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
 * @author CH-yfy
 * @date 2017/12/18
 */
//等价于<url-pattern>, 属性value=属性UrlPatterns,不能同时使用
@WebServlet(name = "Servlet2", urlPatterns = "/s2")
public class Servlet2 extends HttpServlet {
    private static final long serialVersionUID = -4468470849561146854L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //完整的求情URL
        String url = request.getRequestURL().toString();
        //请求行中的资源名部分
        String uri = request.getRequestURI().toString();
        //URL中的参数
        String param = request.getQueryString();
        //客户端IP地址
        String raddr = request.getRemoteAddr();
        //客户端主机名
        String rhost = request.getRemoteHost();
        //网络端口号
        int port = request.getRemotePort();
        //服务器IP地址
        String laddr = request.getLocalAddr();
        //服务器主机名
        String lname = request.getLocalName();
        //将字符以UTF-8编码输出至客户端浏览器
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-ype","text/html;charset=UTF-8");
        response.getWriter().println("url="+url);
        PrintWriter out = response.getWriter();
        out.println("uri="+uri);
        out.write("rhost="+rhost);
        out.write("<br/>");
        out.println("raddr="+raddr);
        out.println("laddr="+laddr);
        out.println("lname="+lname);
        out.println("port="+port);
        out.println("param="+param);

        //两种输出流
        funWriterStream(response);
        funOutputStream(response);
    }

    private void funWriterStream(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        //字符流输出
        PrintWriter out1 = response.getWriter();
        String data1 = "陕西西安";
        out1.write("<br/>"+data1);
        out1.write("<br/>" + 20170414 + "");
    }

    private void funOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        //字节流输出
        OutputStream out2 = response.getOutputStream();
        String data2 = "四川成都";
        byte[] by = data2.getBytes("UTF-8");
        out2.write(by);
        out2.write(("<br/>" + 20170414 + "").getBytes());
    }
}
