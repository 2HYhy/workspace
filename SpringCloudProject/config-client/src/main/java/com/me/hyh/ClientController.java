package com.me.hyh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/8/23
 */
@RefreshScope
@RestController
public class ClientController {

    @Value("${type}")
    String type;
    @Value("${info}")
    String info;
    @Value("${database}")
    String database;

    @RequestMapping("/git")
    public String getGit() {
        return "info=" +info+", type="+type+", database="+database;
    }

}
