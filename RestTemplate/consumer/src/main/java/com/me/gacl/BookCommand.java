package com.me.gacl;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/6/19
 * 自定义hystrix请求命令
 * hystrix抛出异常的情况:1主方法抛出异常，2主方法执行超时，3线程池拒绝，4断路器打开
 */
public class BookCommand extends HystrixCommand<BookDTO> {

    private RestTemplate restTemplate;
    private Long id;

    public BookCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    public BookCommand(Setter setter, RestTemplate restTemplate, long l) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = l;
    }

    /**
     * 执行请求/hystrix/sync时调用,指定一个可访问的服务接口
     * @return
     * @throws Exception
     */
    @Override
    protected BookDTO run() throws Exception {
        //用于测试缓存
        return restTemplate.getForObject("http://PROVIDER/book_pro/getById/{id}", BookDTO.class, id);
        //用于测试同步异步
//        return restTemplate.getForObject("http://PROVIDER/book_pro/getById", BookDTO.class);
    }

    /**
     * /hystrix/sync请求异常时调用,若多个服务提供者有一个挂掉，则与正常返回交替出现
     * @return
     */
    @Override
    protected BookDTO getFallback() {
        //获取抛出的异常
        Throwable throwable = getExecutionException();
        System.out.println("异常是-->" + throwable.getMessage());
        return new BookDTO("服务调用失败了","响应码为500","这是回调函数", new Date());
    }

    /**
     * (一)通过方法重载实现hystrix的缓存
     * 2. 在run方法之前执行，若有缓存，就不会去请求服务提供者
     * 根据返回值来判断这个请求是否和之前执行过的请求一样
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
