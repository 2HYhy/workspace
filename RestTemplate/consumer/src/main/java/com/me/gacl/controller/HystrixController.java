package com.me.gacl.controller;

import com.me.gacl.dto.BookDTO;
import com.me.gacl.hystrix.BookCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author momo
 * @date 2018/6/19
 */
@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/demo1")
    public BookDTO demo1() throws ExecutionException, InterruptedException {
        BookCommand command = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //异步调用
        Future<BookDTO> queue = command.queue();
        BookDTO book = queue.get();
        return book;
        //同步调用
//        BookDTO book1 = command.execute();
//        System.out.println("book1= "+book1);
    }
}
