package com.me.hyh;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CH-yfy
 * @date 2018/8/21
 */
public class MyFilter extends ZuulFilter {

    /**
     * 过滤器类型(pre:路由之前,routing:路由之时,post:路由之后,error:发送错误调用)
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，当过滤器很多时，这个方法会有意义
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行过滤操作，true为执行，false为不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器逻辑处理
     * @return
     */
    @Override
    public Object run() {
        //以简单地校验token是否存在为例
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        System.out.println("method="+req.getMethod()+", url="+ req.getRequestURI());
        String token = req.getParameter("token");
        if (token == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("Sorry, token cannot be empty !");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
