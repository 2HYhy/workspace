package com.me.gacl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/19
 * 更新git配置后，先访问POST请求:http://localhost:8082/bus/refresh 手动触发,然后访问http://localhost:8082/git,配置已更新
 * @RefreshScope用于动态更新配置
 */
@RefreshScope
@RestController
public class ConfigController {

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
