package com.me.hyh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author CH-yfy
 * @date 2018/8/20
 * 注入ZuulRouteLocator的bean，在程序启动时就加载全部路由信息
 */
@Configuration
public class ZuulConfig {

    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public ZuulRouteLocator zuulRouteLocator() {
        ZuulRouteLocator routeLocator = new ZuulRouteLocator(this.serverProperties.getServletPrefix(), this.zuulProperties, this.jdbcTemplate);
        return routeLocator;
    }

}
