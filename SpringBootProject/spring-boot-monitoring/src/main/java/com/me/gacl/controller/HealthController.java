package com.me.gacl.controller;

import com.me.gacl.IHealthClient;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/6/1
 */
@RestController
public class HealthController {

    @Autowired
    private IHealthClient healthClient;

    @RequestMapping(value = "/getHealth", method = RequestMethod.GET)
    public String health() throws Exception {
        String resultString;
        JSONObject json = healthClient.get("http://localhost:8899/actuator/health", JSONObject.class);
        String status = (String) json.get("status");
        /**
         * spring boot 1.xx /health
         */
//        Map<String, String> space = (Map<String, String>) json.get("diskSpace");
//        String status_one = space.get("status");
//        Object total = space.get("total");
//        Object free = space.get("free");
//        Object threshold =space.get("threshold");
//        Map<String, String> db = (Map<String, String>) json.get("db");
//        String status_two = db.get("status");
//        String database = db.get("database");
//        Object hello = db.get("hello");
//        resultString = status+","+status_one+","+total+","+free+","+threshold+","+status_two+","+database+","+hello;
        /**
         * spring boot 2.xx /health
         */
        Map<String, Object> details = (Map<String, Object>) json.get("details");
        Map<String, Object> diskSpace = (Map<String, Object>) details.get("diskSpace");
        Object status2 = diskSpace.get("status");
        Map<String, Object> details2 = (Map<String, Object>) diskSpace.get("details");
        Object total = details2.get("total");
        Object free = details2.get("free");
        Object threshold =details2.get("threshold");
        resultString = status+", "+status2+", "+total+", "+free+", "+threshold;
        return resultString;
    }
}
