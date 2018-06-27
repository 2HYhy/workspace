package com.me.gacl.task;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

/**
 * @author CH-yfy
 * @date 2018/6/26
 */
@Component
public class TaskJob {

    DateFormatter df = new DateFormatter("yyyy-MM-dd HH:mm:ss");

    /**
     * 每隔10秒执行1次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void task1() {
        String time = df.print(new Date(), Locale.CHINA);
        System.out.println("job1开始执行:" + time);
    }

    /**
     * 间隔2s执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate = 2000)
    public void task2() {
        String time = df.print(new Date(), Locale.CHINA);
        System.out.println("job2开始执行:" + time);
    }

    /**
     * 延迟5s后间隔4s执行
     * 固定等待时间
     */
    @Scheduled(fixedDelay = 4000,initialDelay = 5000)
    public void task3() {
        String time = df.print(new Date(), Locale.CHINA);
        System.out.println("job3开始执行:" + time);
    }


}
