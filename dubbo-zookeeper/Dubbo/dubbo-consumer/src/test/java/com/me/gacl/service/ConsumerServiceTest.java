package com.me.gacl.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by yunhua.he on 2017/8/17.
 */
public class ConsumerServiceTest {
    public static void main(String [] args){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String []{"applicationContext.xml"});
        context.start();
        TestService testService = (TestService)context.getBean("testService");
        System.out.println("==provider===: "+testService.getName());
        try{
            System.in.read();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
