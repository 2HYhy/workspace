Spring Cloud 架构:
1. Spring Cloud Netflix
> Netflix Eureka(服务注册与发现中心)  
> Netflix Hystrix(断路器)  
> Netflix Zuul(路由和过滤器),是对外部请求做负载均衡  
> Netflix Feign(创建声明式、模板化的HTTP客户端)  
> Netflix Ribbon(客户端负载均衡器)，是服务之间的负载均衡；nginx是服务器端负载均衡  

2. Spring Cloud Config(分布式配置文件中心)
> config server  
> config client

3. Spring Cloud Sleuth(服务链路追踪)
> 一个zipkin,收集调用数据
> 两个service,相互调用,只有调用了，才能收集到数据

4. Spring Cloud Security
  

......

服务端负载均衡：当浏览器向后台发出请求的时候，会首先向反向代理服务器发送请求，反向代理服务器会根据客户端部署的ip：port映射表以及负载均衡策略，来决定向哪台服务器发送请求，一般会使用到nginx反向代理技术。

客户端负载均衡：当浏览器向后台发出请求的时候，客户端会向服务注册器(例如：Eureka Server)，拉取注册到服务器的可用服务信息，然后根据负载均衡策略，直接命中哪台服务器发送请求。这整个过程都是在客户端完成的，并不需要反向代理服务器的参与

ribbon负载均衡策略:轮循方式(默认), 随机方式

Spring 提供两种服务调度方式：Ribbon+restful和Feign

zuul的路由规则使用的通配符:
1. ?
> 匹配单个字符，/app/?
>- /app/a,  /app/b

2. *
> 匹配任意数量字符， /app/*
>- /app/a,  /app/bb,  /app/ccc, 但是不匹配/app/a/b/c

3. **
> 匹配任意数量字符， /app/**
>- /app/a,  /app/bb,  /app/ccc, /app/a/b/c