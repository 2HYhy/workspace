package com.me.gacl.controller;

import com.me.gacl.zuul.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/17
 */
@RestController
public class RouteController {


    @Autowired
    RefreshRouteService refreshRouteService;

    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("/refreshRoute")
    public String refresh() {
        refreshRouteService.refreshRoute();
        return "zuul refreshRoute";
    }

    @RequestMapping("/watchRoute")
    public String watch() {
        Map<String, Object> zuulMap = zuulHandlerMapping.getHandlerMap();
        //entrySet是键-值对的集合，Set里面的类型是Map.Entry
        for(Map.Entry entry : zuulMap.entrySet()) {
            System.out.println("entry.getKey: "+entry.getKey()+", entry.getValue: "+entry.getValue());
        }
        return "zuul watchRoute";
    }

}
