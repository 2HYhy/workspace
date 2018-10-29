package com.me.hyh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.hyh.service.EurekaService;
import com.me.hyh.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/8/10
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/client")
    public String getClient() {
        return feignService.getClient();
    }

    @GetMapping("/hello")
    public String getHello(@RequestParam Integer id) {
        return feignService.getHello(id);
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Integer id) {
        return feignService.getUser(id);
    }

    @Autowired
    EurekaService eurekaService;
    @GetMapping("/eureka/apps/{appName}")
    public List<InstanceDO> getInstances(@PathVariable("appName") String appName) {
        List<InstanceDO> list = new ArrayList<>();
        JSONObject jsonObject = eurekaService.getInstances(appName);
        JSONObject jsonOne = jsonObject.getJSONObject("application");
        JSONArray jsonArray = jsonOne.getJSONArray("instance");
        if (jsonArray.size() > 0) {
            for (int i=0; i < jsonArray.size(); i++) {
                InstanceDO instance = new InstanceDO();
                JSONObject object = jsonArray.getJSONObject(i);
                instance.setHostName(object.getString("hostName"));
                instance.setInstanceId(object.getString("instanceId"));
                instance.setIpAddr(object.getString("ipAddr"));
                instance.setApp(object.getString("app"));
                instance.setPort(object.getJSONObject("port").getInteger("$"));
                list.add(instance);
            }
        } else {
           System.out.println("---微服务[ "+ appName + " ]实例不存在---");
        }
        return list;
    }

    /**
     * 作为服务提供方，供feign-service通过feign远程调用
     * @param reqJson
     * @param token
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveInfo(@RequestParam(value = "name") String name,
                           @RequestParam(value = "reqJson") String reqJson,
                           @RequestParam(value = "token") String token) {
        return "feign调用restful服务 By POST: "+name+", "+reqJson+", "+token;
    }
    /**
     * 作为服务提供方，供feign-service通过feign远程调用
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getInfo() {
        return "feign调用restful服务 By GET";
    }


    /**
     * 作为服务提供方，供feign-service通过feign远程调用
     * @param user
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void postUser(@RequestBody UserDO user) {
        System.out.println("user.name="+user.getName()+", user.id="+user.getId());
    }

}
