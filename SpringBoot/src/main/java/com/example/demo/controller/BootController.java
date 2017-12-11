package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yunhua.he
 * @date 2017/12/4
 * spring boot DEMO
 */
@RestController
public class BootController {

    private static final Logger logger = LoggerFactory.getLogger(BootController.class);

    @RequestMapping("/hyh/boot")
    public String func() {
        return "Greeting, Spring Boot !";
    }

    /**
     * spring boot启用定时任务，两步:1. 入口类注解@EnableScheduling；2. 要定时的方法注解@Scheduled
     */
    @Scheduled(fixedRate = 5000) //隔5s打印
    public void testSchedule() {
        logger.info("The time now is [{}]", new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

