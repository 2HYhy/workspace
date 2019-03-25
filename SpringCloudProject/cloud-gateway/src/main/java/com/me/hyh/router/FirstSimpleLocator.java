package com.me.hyh.router;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author CH-yfy
 * @date 2019/3/7
 * 本示例来自https://spring.io/guides/gs/gateway/#scratch
 * 通过访问curl http://localhost:8080/get (/get是http://httpbin.org服务中的api)
 * 通过访问curl --dump-header - --header 'Host: www.hystrix.com' http://localhost:8080/delay/3  (/delay/3是http://httpbin.org服务中的api)
 * 使用@Configuration将该类标注为配置类，可以在服务启动时自动加载其内容
 * 等同于在配置文件中的配置内容
 */
//@Configuration
@RestController
@EnableConfigurationProperties(UriConfiguration.class)
public class FirstSimpleLocator {

    //@Bean
    public RouteLocator testRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String httpBin = uriConfiguration.getHttpBin();
        return builder.routes()
                .route(p -> p
                        .path("/get")   //实际会转发到http://httpbin.org/get
                        .filters(f -> f
                                .addRequestHeader("name", "spring cloud gateway"))
                        .uri(httpBin)
                )
                .route(p -> p
                        .host("*.hystrix.com")    //主机中含有该字段时进入该route
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setName("localTest")
                                        .setFallbackUri("forward:/fallback")))
                        .uri("http://httpbin.org:80")
                )
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("===fallback===");
    }
}

@ConfigurationProperties
class UriConfiguration {

    private String httpBin = "http://httpbin.org:80";

    public String getHttpBin() {
        return httpBin;
    }

    public void setHttpBin(String httpBin) {
        this.httpBin = httpBin;
    }
}

