package com.me.gacl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;
    @Autowired
    EurekaService eurekaService;

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
    public String sayHello() {
        return feignService.sayHello();
    }

    @RequestMapping("/feign/user")
    public String getUser(@RequestParam("name") String name) {
        return feignService.getUser(name);
    }

    @DeleteMapping("/feign/delete")
    public String deleteIt(@RequestParam("id") Integer id) {
        return feignService.deleteIt(id);
    }

    @RequestMapping("/feign/eureka/apps")
    public List<InstanceDO> testEureka(@RequestParam("appName")String appName) {
        List<InstanceDO> list = new ArrayList<>();
        JSONObject jsonObject = eurekaService.testEureka(appName);
        JSONObject jsonTemp = jsonObject.getJSONObject("application");
        JSONArray jsonArray = jsonTemp.getJSONArray("instance");
        if (jsonArray.size() > 0 ) {
            for (int i=0;i<jsonArray.size();i++) {
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
