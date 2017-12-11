package com.hyh.gacl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author CH-yfy
 */

@EnableConfigServer
@SpringBootApplication
public class Application {   //服务端

    public static void main(String [] args) {
        SpringApplication.run(Application.class, args);
    }
}
