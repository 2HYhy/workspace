package com.me.gacl.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author CH-yfy
 * @date 2019/3/15
 * 根据用户传入的参数进行限流(必须带uid查询参数)
 */
public class UserKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getQueryParams().getFirst("uid"));
    }
}
