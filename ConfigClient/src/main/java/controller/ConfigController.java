package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mmb
 * @date 2016/7/30
 */
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