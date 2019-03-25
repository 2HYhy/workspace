package com.me.gacl;

import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author CH-yfy
 * @date 2019/3/15
 */
//@Component
public class PostRequestFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(PostRequestFilter.class);
    private static final String POST = "POST";
    private static final String GET = "GET";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("进入过滤器......");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();
        if (POST.equals(method)) {
            String bodyStr = resolveBodyFromRequest(serverHttpRequest);
            URI uri = serverHttpRequest.getURI();
            ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
            request = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            return chain.filter(exchange.mutate().request(request).build());
        } else if (GET.equals(method)) {
            Map requestQueryParams = serverHttpRequest.getQueryParams();
            logger.info("请求参数包含:{}", requestQueryParams);
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取请求报文
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        logger.info("字符串转换");
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
