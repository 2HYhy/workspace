package com.me.gacl.controller;

import com.me.gacl.entity.User;
import com.me.gacl.service.UserService;
import org.apache.ibatis.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author CH-yfy
 * @date 2019/2/19
 * 测试Ehcache的使用
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/testCache")
    public String ehcacheTest() {
        System.out.println("---start to test Ehcache---");
        User userO = new User();
        String id = UUID.randomUUID().toString();
        //去掉UUID的-符号，32位
        String uuid = id.substring(0,8)+id.substring(9,13)+id.substring(14,18)+id.substring(19,23)+id.substring(24);
        userO.setUuid(uuid);
        userO.setName("湘汐");
        userO.setAge(22);
        userO.setBirthday("2019-02-20");
        try {
            userService.save(userO);
        } catch (Exception e) {
            e.printStackTrace();
            return "用户对象插入数据库失败";
        }
        //第一次查询(通过数据库)
        System.out.println("first 查询:" + userService.get(userO.getUuid()));
        //第二次查询(通过缓存查询)
        System.out.println("second 查询:" + userService.get(userO.getUuid()));

        System.out.println("---update user info---");
        User userT = new User();
        userT.setUuid(userO.getUuid());
        //只更新name和age
        userT.setName("湘楠");
        userT.setAge(24);
        try {
            userService.update(userT);
        } catch (CacheException e){
            e.printStackTrace();
        }
        //更新后再次查询

        System.out.println("third 查询:" + userService.get(userT.getUuid()));
        return "operate success";
    }

    @RequestMapping("/findCache")
    public String findTest(@RequestParam("uuid")String uuid) {
        System.out.println("---find user---");
        System.out.println(userService.get(uuid));
        return "find success";
    }

    @RequestMapping("/deleteCache")
    public String deleteTest(@RequestParam("name")String name, @RequestParam("uuid")String uuid) {
        System.out.println("---delete user---");
        userService.delete(uuid, name);
        //再去查询已经删除的用户
        System.out.println(userService.get(uuid));
        return "delete success";
    }
}
