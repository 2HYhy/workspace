package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author yunhua.he
 * @date 2017/12/11
 * spring boot异步调用方法
 */
@Component
public class AsyncTaskService {

    public static final Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception{  // Future<String>用于返回异步调用的结果
        System.out.println("first task is beginning");
        Long stime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long etime = System.currentTimeMillis();
        System.out.println("first task is finishing");
        System.out.println("task one 耗时: "+ (etime-stime)+ "ms");
        return new AsyncResult<>("任务一完成！");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception{
        System.out.println("two task is beginning");
        Long stime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long etime = System.currentTimeMillis();
        System.out.println("two task is finishing");
        System.out.println("task two 耗时: "+ (etime-stime)+ "ms");
        return new AsyncResult<>("任务二完成！");
    }

    @Async
    public Future<String> doTaskThree() throws Exception{
        System.out.println("three task is beginning");
        Long stime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        Long etime = System.currentTimeMillis();
        System.out.println("three task is finishing");
        System.out.println("task three 耗时: "+ (etime-stime)+ "ms");
        return new AsyncResult<>("任务三完成！");
    }
}
