package com.example.demo;

import com.example.demo.service.AsyncTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @author CH-yfy
 * @date 2017/12/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskServiceTest {

    @Autowired
    private AsyncTaskService task;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                //当三个任务都完成时，退出循环
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("异步执行，三个任务全部完成，总耗时: " + (end-start)+ "ms");
    }
}
