package com.me.gacl.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author CH-yfy
 * @date 2017/12/18
 * 拦截跳转过滤器(拦截对成功页面的请求)
 * 浏览器访问http://localhost:8080/success
 */

@WebFilter(initParams = { @WebInitParam(name = "encoding", value = "UTF-8"), @WebInitParam(name = "logining", value = "/login.jsp")},
        urlPatterns = "/success")
public class LoginFilter implements Filter {

    private String encoding;
    private String logining;
    private static final String ROOT = "root";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        logining = filterConfig.getInitParameter("logining");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        HttpServletRequest requ = (HttpServletRequest) request;
        String user = requ.getParameter("username");
        String password = requ.getParameter("password");
        if (ROOT.equals(password) && ROOT.equals(user)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("用户名或密码错误，请重新输入!");
            RequestDispatcher dispatcher = request.getRequestDispatcher(logining);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("======销毁filter，资源回收======");
    }
}
