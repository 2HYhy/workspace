package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "ServletParams")
public class ServletParams extends HttpServlet {
    public ServletParams(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "<-读取所有的表单数据->";
        Enumeration enums=request.getParameterNames();
        while(enums.hasMoreElements()){
            String pname=enums.nextElement().toString();
            out.print(pname+" : ");
            String[] pvalues=request.getParameterValues(pname);
            if(pvalues.length==1){
                String pvalue=pvalues[0];
                if(pvalue.length()==0){
                    out.print("空值");
                }else{
                    out.print(pvalue+";");
                }
            }else{
                for(int i=0;i<pvalues.length;i++){
                    out.print(pvalues[i]+",");
                }
            }
        }
    }
}
