package com.me.gacl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CH-yfy
 * @date 2017/12/18
 * 日志过滤器
 * 对用户请求进行预处理，对用户响应进行后处理
 */

//等价于在web.xml中的配置
@WebFilter(filterName = "log-filter", urlPatterns = "/filter")
public class LogFilter implements Filter {
    private static String logDir = "/Users/CH-yfy/myproject/logs/";
    private PrintStream logger;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { //创建log输出流
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String logFile = logDir+year+"_"+month+"_"+day+".log";
        try {
            logger = new PrintStream(new FileOutputStream(logFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException { // 打印日志
        HttpServletRequest req = (HttpServletRequest) request;
        logger.println("[REQUEST] "+ sdf.format(new Date()));
        logger.println("[URL] "+ req.getScheme()+"//"+req.getServerName()+":"
                +req.getServerPort()+"/"+req.getRequestURI());
        chain.doFilter(request, response);
        logger.println("[RESPONSE] "+ sdf.format(new Date()));
        logger.println("[CONTENTTYPE] "+ response.getContentType());
        logger.println("===================");
    }

    @Override
    public void destroy() { //关闭log输出流
        if (logger != null) {
            logger.flush();
            logger.close();
        }
    }
}
