package com.me.gacl.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.gacl.InstanceDO;
import com.me.gacl.service.EurekaService;
import com.me.gacl.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/7/2
 */
@RestController
public class RemoteController {

    @Autowired
    RemoteService remoteService;
    @Autowired
    EurekaService eurekaService;

    /**
     * 访问http://localhost:9006/test/http,9001和9002交替出现
     * @return
     */
    @RequestMapping("/test/http")
    public String testHttp() {
        return remoteService.testHttp();
    }


    @RequestMapping("/test/eureka/apps")
    public List<InstanceDO> testEureka(@RequestParam("appName")String appName) {
        List<InstanceDO> list = new ArrayList<>();
        JSONObject jsonObject = eurekaService.testEureka(appName);
        JSONObject json = jsonObject.getJSONObject("application");
        JSONArray jsonArray = json.getJSONArray("instance");
        if (jsonArray.size() > 0 ) {
            for (int i=0;i<jsonArray.size();i++) {
                System.out.println("");
                JSONObject object = jsonArray.getJSONObject(i);
                InstanceDO instance = new InstanceDO();
                instance.setInstanceId(object.getString("instanceId"));
                instance.setHostName(object.getString("hostName"));
                instance.setApp(object.getString("app"));
                instance.setIpAddr(object.getString("ipAddr"));
                instance.setStatus(object.getString("status"));
                instance.setPort(object.getJSONObject("port").getInteger("$"));
                list.add(instance);
            }
        }
        return list;
    }
}
