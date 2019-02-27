package com.me.gacl.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.gacl.entity.OpenId;
import com.me.gacl.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/6/27
 * 验证各种形式传入的请求参数，不涉及缓存
 * 所有API均以postman请求为例
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * 请求：localhost:7003/redis/api/one?ids=1,2,3
     * 返回：[1,2,3]
     * @param ids
     * @return
     * RequestParam也可以是String类型，用split(",")处理
     */
    @GetMapping("/one")
    public Integer [] getOne(@RequestParam Integer [] ids) {
        int length = ids.length;
        System.out.println("length = " + length);
        return ids;
    }

    /**
     * 请求: { "openIds": ["001", "002", "003"] }
     * 返回： { "openIds": ["001", "002", "003"] }
     * @param params
     * @return
     */
    @PostMapping(value = "/two", consumes =  {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public OpenId getTwo(@RequestBody OpenId params) {
        String [] openIds = params.getOpenIds();
        int length = openIds.length;
        System.out.println("length = " + length);
        return params;
    }

    /**
     * 请求： ["id","name","phone"]
     * 返回： ["id","name","phone"]
     * @param list
     * @return
     */
    @PostMapping(value = "/three")
    public List<String> getThree(@RequestBody List<String> list) {
        int length = list.size();
        System.out.println("length = " + length);
        return list;
    }

    /**
     * 请求：
     [
     {
     "id": 11,
     "name" : "apple",
     "birthday": "2018-06-27"
     },
     {
     "id": 12,
     "name" : "banana",
     "birthday": "2018-06-26"
     }
     ]
     * 返回：
     [
     {
     "id": 11,
     "name": "apple",
     "birthDay": "2018-06-26T16:00:00.000+0000"
     },
     {
     "id": 12,
     "name": "banana",
     "birthDay": "2018-06-25T16:00:00.000+0000"
     }
     ]
     * @param array
     * @return
     */
    @PostMapping(value = "/four")
    public List<User> getFour(@RequestBody JSONArray array) {
        List<User> users = JSONArray.parseArray(array.toJSONString(),User.class);
        int length = users.size();
        System.out.println("length = " + length);
        return users;
    }

    /**
     * 请求：
     {
     "source" : "boot",
     "data": [
     {
     "id": 21,
     "name" : "pear"
     },
     {
     "id": 22,
     "name" : "kiwi"
     }
     ]
     }

     * 返回：
     [
     "boot",
     [
     {
     "id": 21,
     "name": "pear",
     "birthDay": null
     },
     {
     "id": 22,
     "name": "kiwi",
     "birthDay": null
     }
     ]
     ]
     * @param jsonString
     * @return
     */
    @PostMapping("/five")
    public JSONArray getFive(@RequestBody String jsonString) {
        JSONObject json = JSONObject.parseObject(jsonString);
        String source = json.get("source").toString();
        String data = json.getString("data");
        List<User> users = JSONArray.parseArray(data, User.class);
        int length = users.size();
        System.out.println("length = " + length);
        System.out.println("users = " + users);
        JSONArray array = new JSONArray();
        array.add(0, source);
        array.add(users);
        return array;
    }
}