package com.example.demo.controller;

import com.example.demo.bean.ConfigBean;
import com.example.demo.bean.FruitBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2017/12/5
 * spring boot自定义属性的各种获取方式
 */

@RestController
@EnableConfigurationProperties({ConfigBean.class, FruitBean.class})
public class FieldController {

    @Value("${hyh.name}")
    private String name;
    @Value("${hyh.sex}")
    private String sex;

    @RequestMapping("/hyh/field")
    public String func() {
        return name + " && " + sex;
    }

    @Autowired
    private ConfigBean configBean;
    @RequestMapping("/hyh/attributes")
    public String func1() {
        String res = configBean.getUid()+" : "+configBean.getMajor()+" : "+configBean.getMessage();
        return res;
    }

    @Autowired
    private FruitBean fruitBean;
    @RequestMapping("/hyh/test")
    public String func2() {
        return fruitBean.getVariety()+" >> "+fruitBean.getColor()+" >> "+fruitBean.getSize();
    }
}
