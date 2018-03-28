package com.hyh.gacl.security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CH-yfy
 */
public class VueInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //解决前后端跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        //解决中文乱码问题
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String authHeader = request.getParameter("Authorization");
        if (authHeader == null) {
            throw new ServletException("invalid Authorization header");
        }
        try {
            System.out.println("应该去验证前端所传token的合法性");
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
