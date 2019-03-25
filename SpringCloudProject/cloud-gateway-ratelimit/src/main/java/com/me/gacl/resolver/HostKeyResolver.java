package com.me.gacl.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author CH-yfy
 * @date 2019/3/15
 * 根据主机名进行限流,在redis中通过 keys * 可以查看对应数据
 */
public class HostKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        System.out.println("进入resolver");
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostName());
//        return Mono.just("10.3.30.130");  //要限流的host
    }


}
