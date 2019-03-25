package com.me.hyh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2019/3/13
 * 自定义过滤器工厂，要能读取到该类，要么在启动类中注册bean,要么在该类上添加component注解
 */
@Component
public class SimpleTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<SimpleTimeGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTimeGatewayFilterFactory.class);
    private static final String STARTTime = "startTime";
    private static final String KEY = "withParams";

    public SimpleTimeGatewayFilterFactory() {  //防止报错ClassCastException
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(STARTTime, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(STARTTime);
                        StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath()).append(":")
                                .append(":")
                                .append(System.currentTimeMillis() - startTime)
                                .append("ms");
                        if (config.withParams) {
                            sb.append(" params:").append(exchange.getRequest().getQueryParams());
                        }
                        logger.info(sb.toString());
                    })
            );
        };
    }


    static class Config {
        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }
    }
}
