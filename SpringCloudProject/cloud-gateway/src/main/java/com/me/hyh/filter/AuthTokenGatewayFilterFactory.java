package com.me.hyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2019/3/13
 * 过滤器工厂的类名必须规范，以GatewayFilterFactory结尾，需要在配置文件中配置filters
 * 本示例出自https://segmentfault.com/a/1190000016227780
 */
//@Component
public class AuthTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthTokenGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenGatewayFilterFactory.class);
    private static final String TOKEN = "token";
    private static final Integer LENGTH = 6;
    private static final String KEY = "enabled";

    public AuthTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //若不校验，直接通过过滤器
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().getFirst(TOKEN);
            if (token == null) {
                token = request.getQueryParams().getFirst(TOKEN);
            }
            ServerHttpResponse response = exchange.getResponse();
            if (StringUtils.isEmpty(token)) {
                logger.info("...token cannot be null...");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //结束请求
                return response.setComplete();
            }
            if (!LENGTH.equals(token.length())) {
                logger.info("...token should be six...");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //结束请求
                return response.setComplete();
            }
            logger.info("...filter is passed...");
            return chain.filter(exchange);

        };
    }

    static class Config {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
