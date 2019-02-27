package com.me.gacl.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 要使用hystrix的缓存功能，必须管理HystrixRequestContext。若一个请求要用到另一个请求的缓存，则必须满足两个请求处于同一个context中。
 * initializeContext和close这两条语句中间的所有请求都处于同一个context
 * @author CH-yfy
 * @date 2018/8/24
 */
@WebFilter(filterName = "hystrixConfigFilter", urlPatterns = "/*")
public class HystrixConfigFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------initialize filter------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("------start filter------");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        } finally {
            context.close();
        }
        System.out.println("------end filter------");
    }

    @Override
    public void destroy() {
        System.out.println("------destroy filter------");
    }
}
