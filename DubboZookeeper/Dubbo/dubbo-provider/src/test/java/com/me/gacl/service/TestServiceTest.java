package com.me.gacl.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by CH-yfy on 2017/8/15.
 */
public class TestServiceTest {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        System.out.println("提供者服务已注册成功");
        try{
            System.in.read(); //让程序一直跑，表示一直提供服务
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
