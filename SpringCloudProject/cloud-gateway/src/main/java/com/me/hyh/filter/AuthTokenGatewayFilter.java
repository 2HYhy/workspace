package com.me.hyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author CH-yfy
 * @date 2019/3/13
 * 本示例出自https://segmentfault.com/a/1190000016227780
 */
public class AuthTokenGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenGatewayFilter.class);
    private static final String TOKEN = "token";
    private static final Integer LENGTH = 6;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(TOKEN);
        if (token == null) {
            token = request.getQueryParams().getFirst(TOKEN);
        }
        ServerHttpResponse response = exchange.getResponse();
        if (StringUtils.isEmpty(token)) {
            logger.info("token is null...");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //结束请求
            return response.setComplete();
        }
        if (!LENGTH.equals(token.length())) {
            logger.info("token is wrong...");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //结束请求
            return response.setComplete();
        }
        logger.info("pass the filter...");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
