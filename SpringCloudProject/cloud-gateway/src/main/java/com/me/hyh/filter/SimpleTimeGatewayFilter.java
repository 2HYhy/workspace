package com.me.hyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author CH-yfy
 * @date 2019/3/13
 */
public class SimpleTimeGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTimeGatewayFilter.class);
    private static final String STARTTime = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) { //定义过滤器内容
        exchange.getAttributes().put(STARTTime, System.currentTimeMillis());
        logger.info("-----pre filter before chain.filter-----");
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(STARTTime);
                    String path = exchange.getRequest().getURI().getRawPath();
                    Long endTime = System.currentTimeMillis();
                    logger.info(path + ":" + (endTime - startTime) + "ms");

                    logger.info("-----post filter in then-----");
                })
        );
    }

    @Override
    public int getOrder() {  //设置优先级，值越大，优先级越低
        return 0;
    }
}
