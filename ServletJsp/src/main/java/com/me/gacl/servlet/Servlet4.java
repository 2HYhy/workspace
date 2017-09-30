package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "Servlet4")
public class Servlet4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用cookie记录用户上一次的访问时间
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            out.print("it is the first time to visit!");
        }else{
            out.write("the last time to visit is:");
            for(int i=0;i< cookies.length;i++){
                Cookie cook=cookies[i];
                if(cook.getName().equals("lastAccessTime")){
                    long accesstime=Long.parseLong(cook.getValue());
                    Date date=new Date(accesstime);
                    out.write(date.toLocaleString());
                }
            }
        }
        //更新用户的访问时间，并添加至cookie
        Cookie newcookie=new Cookie("lastAccessTime",System.currentTimeMillis()+"");
        //设置其有效期为1天，若有效期设为0，即为删除cookie
        newcookie.setMaxAge(24*60*60);
        response.addCookie(newcookie);

        //创建session,默认有效期为30分钟
        HttpSession session=request.getSession();
        session.setAttribute("data","sessionCreating");
        String sessId=session.getId();
        if(session.isNew()){
            response.getWriter().println("session创建成功，sessionId= "+sessId);
        }else{
            response.getWriter().println("该服务器已存在session，其sessionId= "+sessId);
        }
    }
}
