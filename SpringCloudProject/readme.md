# Spring Cloud 架构
## 1. Spring Cloud Netflix
> Netflix Eureka(服务注册与发现中心)  
> Netflix Hystrix(断路器)  
> Netflix Zuul(路由和过滤器),是对外部请求做负载均衡，是netflix公司的产品，只是spring将其集成在spring cloud下了。 
> Netflix Feign(创建声明式、模板化的HTTP客户端)  
> Netflix Ribbon(客户端负载均衡器)，是服务之间的负载均衡；nginx是服务器端负载均衡  

### eureka:
关于服务注册中心，服务提供者，服务消费者的相关概念在[https://mp.weixin.qq.com/s/kAqOTKUt_qPlxzI4aGS5Pw]有详细讲解。

服务续约:
在注册完服务后，服务实例会每隔30秒向eureka server发送心跳，告诉它"我还在运行"，以防止eureka server将该服务实例从服务列表中剔除。  

服务下线:
服务实例在运行过程中正常关闭或者重启时，会向eureka server发送一个rest请求，eureka server收到请求后将该服务状态设置为down，表示该服务已经下线。  

失效剔除:
eureka server在启动时会创建一个定时任务，每隔60s就去当前服务列表中将超过90s还没续约的服务剔除出去，避免服务消费者调用了一个无效的服务。

自我保护:
如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么eureka server就认为客户端与注册中心出现了网络故障，自动进入自我保护机制,不再从注册列表中移除长时间没收到心跳的服务。但是
在保护期内如果刚好这个服务非正常下线了，此时服务消费者就会拿到一个无效的服务实例，调用失败，对于这个问题需要服务消费者端要有一些容错机制，如重试，断路器等。
当开启自我保护机制时，lease expiration enabled=false,表示不能自动过期租约;当关闭自我保护机制时，lease expiration enabled=true,表示可以自动过期租约。

### eureka cluster(集群)
启动方式1:
通过配置spring.profiles.active=peer1 / peer2, 分别启动两个服务;
通过配置VM options为 -Dspring.profiles.active=peer1 / peer2, 分别启动两个服务;
通过运行jar包,分别启动两个服务
> cd /Users/CH-yfy/myproject/SpringCloud-one/EurekaServer/target
> java -jar EurekaServer-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer1 / peer2

### ribbon(负载均衡)
> 服务端负载均衡：当浏览器向后台发出请求的时候，会首先向反向代理服务器发送请求，反向代理服务器会根据客户端部署的ip:port映射表以及负载均衡策略，来决定向哪台服务器发送请求，一般会使用到nginx反向代理技术。  
> 客户端负载均衡：当浏览器向后台发出请求的时候，客户端会向服务注册器(例如：Eureka Server)，拉取注册到服务器的可用服务信息，然后根据负载均衡策略，直接命中哪台服务器发送请求。这整个过程都是在客户端完成的，并不需要反向代理服务器的参与。 

### feign(声明式，模块化的http客户端)
三种使用方式:
(1)作为http客户端调用远程http服务，不依赖spring cloud框架;
(2)不注册到eureka，通过@FeignClient(name="",url="")直接指定服务;
(3)注册到eureka，通过@FeignClient(name="")调用远程服务的接口。
原理:         
Spring Cloud应用在启动时，Feign会扫描标有@FeignClient注解的接口，生成代理，并注册到spring容器中。
生成代理时eign会为每个接口方法创建一个RequestTemplate对象，它封装了该http请求所需要的全部信息(请求方法，请求参数等)。
Feign的模板化就体现在这里。      
微服务调用远程服务提供者接口使用方式:
JDK原生的URLConnection,  
Apache的Http Client,  
Spring的RestTemplate,  
Feign

### Hystrix(熔断器)
默认的超时时间是1s,如果超过这个时间尚未响应,将会进入fallback代码。首次请求往往比较慢(因为Spring的懒加载机制,要实例化一些类),这样导致响应时间可能大于1秒。
解决方法有三:
(1) 将超时时间改的久一些10s  
`hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=10000`
(2) 禁用hystrix的超时时间  
hystrix.command.default.execution.timeout.enabled: false
(3) 禁用feign的hystrix(不推荐)  
feign.hystrix.enabled=false
(4)  
hystrix的超时时间要大于 (1 + MaxAutoRetries + MaxAutoRetriesNextServer) * ReadTimeout 比较好

### dashboard & turbine
效果如图:![alt-text](/images/dashboard.png)  

### zuul route & zuul ratelimit
> 有两个服务:consumer(/feign-consumer/**), consumer-hello(/feign-consumer/hello/**)
> Zuul中的路径匹配方式是一种线性匹配方式，即按照路由匹配规则的存储顺序依次匹配
> 要正确匹配consumer-hello的路径，只要保证consumer-hello的匹配规则被先定义,consumer的匹配规则被后定义
> properties文件不能保证这个先后顺序，所以必须用yml文件来配置
注册到eureka的服务，请求转发既可以通过serviceId, 也可以通过单个url。
不注册到eureka的服务，请求转发只能通过单个url。
不管是注册到eureka的服务，还是不注册到eureka的服务，负载均衡要么都交给eureka(ribbon.eureka.enabled=true,这是默认的方式)，要么都交给listOfServers(前提是手动设置ribbon.eureka.enabled=false)。

## 2. Spring Cloud Config(分布式配置文件中心)
> config server  
> config client


## 3. Spring Cloud Sleuth(服务链路追踪)
> 一个zipkin,收集调用数据
> 两个service,相互调用,只有调用了，才能收集到数据
> 涉及到的zipkin-span1和zipkin-span2图源自`https://blog.csdn.net/manzhizhen/article/details/53865368`


## 4. Spring Cloud Security

## 5. Spring Cloud Gateway
> 主要功能是路由转发、权限校验、限流控制
> 有2种配置方式: 1是在Bean中，2是在application文件中
> 工作流程如下:
客户端发送请求 ——> Gateway Handler Mapping,确定与请求匹配的路由(要用到predicate) ——> 
Gateway web handler,经过一系列的过滤器(往往进行鉴权、限流、日志输出、协议转换、请求头修改等操作) ——> 响应 
> Predicate:
决定了请求由哪一个路由处理。具体步骤是：先经过"pre"类型的过滤器，然后路由进行处理，再经过"post"类型的过滤器。
有很多类型，包括Time,Cookie,Header,Host,Method,Path...
只有满足predicate要求的，才会进入其router进行处理。若有多个predicate，并且一个请求满足多个predicate，则按照配置的顺序第一个生效。
> Filter:
不只有"pre"和"post"类型的过滤器，还可以从作用范围分为两种，包括针对单个路由的GatewayFilter和针对所有路由的GlobalFilter。
过滤器工厂总共可以分为7大类: Header, Parameter, Path, Status, Redirect, Hystrix, Ratelimiter。
> 1 自定义过滤器(无需配置文件)
自定义过滤器需要实现GatewayFilter和Ordered两个接口。
gateway是基于Project Reactor和Webflux的，只有一个filter方法。所以一般而言，chain.filter()之前就是"pre"部分，then()之后就是"post"部分。
将自定义过滤器注册到router中即可。
> 2 自定义过滤器工厂(依赖配置文件)
顶级接口GatewayFilterFactory有2个实现类:AbstractGatewayFilterFactory(接收一个参数)和AbstractNameValueGatewayFilterFactory(接收2个参数)
> 以上两种过滤器使用方式在实际中选择一种即可。
> spring-cloud-gateway反向代理的原理是，首先读取原请求的数据(只会读取一次)，然后构造一个新的请求，将原请求的数据封装到新的请求中，然后再转发出去。
> 限流：
1. Spring Cloud Gateway使用Redis+Lua技术实现高并发和高性能的限流方案。

