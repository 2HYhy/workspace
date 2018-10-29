package com.me.gacl.controller;

import com.me.gacl.BookCommand;
import com.me.gacl.BookDTO;
import com.me.gacl.hystrix.HystrixService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author CH-yfy
 * @date 2018/6/19
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    HystrixService hystrixService;

    @RequestMapping("/sync")
    public BookDTO sync() throws ExecutionException, InterruptedException {
        //初始化请求上下文,使用缓存时如果不加,就会报错Observable onError
        HystrixRequestContext.initializeContext();
        BookCommand command = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //异步调用
        Future<BookDTO> queue = command.queue();
        BookDTO book = queue.get();
        return book;
        //同步调用
//        BookDTO book = command.execute();
//        System.out.println("book= "+book);
    }

    /**
     * 1. 连着发起3个请求，验证缓存是否生效
     * @return
     * 通过console日志，provider只执行了一次，其余两次都是缓存数据
     */
    @RequestMapping(value = "/cache1")
    public BookDTO getById() {
        HystrixCommandKey command = HystrixCommandKey.Factory.asKey("commandKey");
        HystrixRequestContext.initializeContext();
        BookCommand bcOne = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 1L);
        BookDTO book1 = bcOne.execute();
        BookCommand bcTwo = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 1L);
        BookDTO book2 = bcTwo.execute();
        BookCommand bcThree = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 3L);
        BookDTO book3 = bcThree.execute();
        System.out.println("book1= " + book1);
        System.out.println("book2= " + book2);
        System.out.println("book3= " + book3);
        return book3;
    }

    /**
     * 连着发起3个请求，验证缓存是否生效
     * 使用注解开启的hystrix缓存
     * @return
     */
    @RequestMapping("/cache2")
    public String getBookOne() {
        String book1 = hystrixService.getBookOne(6, "apple");
        String book2 = hystrixService.getBookOne(6, "apple");
        String book3 = hystrixService.getBookOne(9, "apple");
        System.out.println("book1= " + book1);
        System.out.println("book2= " + book2);
        System.out.println("book3= " + book3);
        return book1;
    }

    @RequestMapping("/cache3")
    public String getBookTwo() {
        String book4 = hystrixService.getBookTwo(6, "apple");
        String book5 = hystrixService.getBookTwo(6, "apple");
        String book6 = hystrixService.getBookTwo(9, "apple");
        System.out.println("book4= " + book4);
        System.out.println("book5= " + book5);
        System.out.println("book6= " + book6);
        return book4;
    }

    /**
     * 请求合并,指定时间内连续发送的请求被一并处理
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/collapse/{id}")
    public String findBook1(@PathVariable("id") Integer id) throws ExecutionException, InterruptedException {
        Future<String> res = hystrixService.func1(id);
        return res.get();
    }

    /**
     * 请求合并,指定时间内连续发送的请求被一并处理
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping("/collapses")
    public List<String> findBook2() throws ExecutionException, InterruptedException {
        Future<String> res1 = hystrixService.func2(66);
        Future<String> res2 = hystrixService.func2(99);
        List<String> list = new ArrayList<>();
        list.add(res1.get());
        list.add(res2.get());
        return list;
    }

}
