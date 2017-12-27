package com.me.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author CH-yfy
 * @date 2017/12/27
 */
//开启事务注解管理
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String [] args) {
        SpringApplication.run(Application.class, args);
    }
}
