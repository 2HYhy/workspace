package com.me.hyh;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author CH-yfy
 * @date 2018/8/27
 * 测验hystrix的各种配置参数
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixConfigController {

    @Autowired
    private HystrixConfigService hystrixConfigService;

    /**
     * 测验 执行命令超时时间，默认1000ms
     * @return
     */
    @RequestMapping("/getClient1")
    @HystrixCommand(commandKey = "clientOne", fallbackMethod = "getError", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String getClient1() throws ExecutionException,InterruptedException {
        Integer id = 6;
        Thread.sleep(6000);
        return hystrixConfigService.getClientHello(id);
    }

    /**
     * 测验 执行是否启用/触发超时，默认true
     * @return
     * @throws InterruptedException
     * 为false时，会一直等到休眠结束，再正常返回远程接口的响应结果
     */
    @RequestMapping("/getClient2")
    @HystrixCommand(commandKey = "clientTwo", fallbackMethod = "getError", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name="execution.timeout.enabled",value = "false")
    })
    public String getClient2() throws InterruptedException {
        Integer id = 6;
        Thread.sleep(6000);
        return hystrixConfigService.getClientHello(id);
    }

    /**
     * 测验 执行发生超时时，是否进行中断操作，默认true
     * 只会中断处于阻塞状态(sleep,wait,join)的线程，正常的线程不会被中断
     * @return
     * @throws InterruptedException
     * 服务超时前提下，为true时，直接进入fallback，不再执行sleep后面的内容；为false时，进入fallback，但也会执行完sleep后面的内容
     */
    @RequestMapping("/getClient3")
    @HystrixCommand(commandKey = "clientThree", fallbackMethod = "getError", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name="execution.isolation.thread.interruptOnTimeout",value = "true")
    })
    public String getClient3() throws InterruptedException {
        Integer id = 6;
        Thread.sleep(6000);
        System.out.println(">>>休眠已经结束>>>");
        return hystrixConfigService.getClientHello(id);
    }

    /**
     * 测验 执行失败或请求被拒绝时，是否调用fallback降级，默认true
     * @return
     * @throws InterruptedException
     * 为false时，抛出500超时的错误
     */
    @RequestMapping("/getClient4")
    @HystrixCommand(commandKey = "clientFour", fallbackMethod = "getError", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name="fallback.enabled",value = "false")
    })
    public String getClient4() throws InterruptedException {
        Integer id = 6;
        Thread.sleep(6000);
        return hystrixConfigService.getClientHello(id);
    }

    /**
     * throwable可以捕获原方法抛出的异常
     * @param throwable
     * @return
     */
    public String getError(Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("------服务降级，异常是: "+throwable.getMessage());
        return "some error, this is fallback method!";
    }

}
