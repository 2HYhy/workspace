package com.me.gacl.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CH-yfy
 * @date 2018/4/18
 * zuul还能充当过滤器，进行安全性验证
 */
@Component
public class MyLocalFilter extends ZuulFilter{

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
     * 是否要执行过滤器，true是执行，false是不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器逻辑
     * @return
     */
    @Override
    public Object run() {
      //以有无token为例
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        System.out.println("method="+req.getMethod()+", url="+req.getRequestURI().toString());
        String token = req.getParameter("token");
        if (token == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("Sorry, token is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
