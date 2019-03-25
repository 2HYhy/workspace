package com.me.gacl;

import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author CH-yfy
 * @date 2019/3/14
 */
//@Component
public class RequestGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestGatewayFilterFactory.Config> {

    private static final String KEY = "enabled";
    private static final String METHOD = "POST";
    private static final Logger logger = LoggerFactory.getLogger(RequestGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("------enter filter------");
        return (exchange, chain) -> {
            URI uri = exchange.getRequest().getURI();
            URI ex = UriComponentsBuilder.fromUri(uri).build(true).toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(ex).build();
            if(METHOD.equalsIgnoreCase(request.getMethodValue())){
                Flux<DataBuffer> body = request.getBody();
                // 缓存读取的request body信息
                AtomicReference<String> bodyRef = new AtomicReference<>();
                // 读取request body到缓存
                body.subscribe(dataBuffer -> {
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
                    DataBufferUtils.release(dataBuffer);
                    bodyRef.set(charBuffer.toString());
                });
                // 获取request body
                String bodyStr = bodyRef.get();
                System.out.println(bodyStr);
                DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
                Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
                // 重新封装request
                request = new ServerHttpRequestDecorator(request){
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return bodyFlux;
                    }
                };
            }
            return chain.filter(exchange.mutate().request(request).build());
        };
}

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public RequestGatewayFilterFactory() {
        super(Config.class);
    }

    protected DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
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
