package com.me.gacl.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author CH-yfy
 * @date 2018/4/16
 */

@Configuration
public class ZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public ZuulRouteLocator routeLocator() {
        ZuulRouteLocator routeLocator = new ZuulRouteLocator(
                this.serverProperties.getServletPrefix(), this.zuulProperties, this.jdbcTemplate);
        return routeLocator;
    }
}
