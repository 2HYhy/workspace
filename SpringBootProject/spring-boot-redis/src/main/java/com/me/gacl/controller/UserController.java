package com.me.gacl.controller;

import com.me.gacl.entity.User;
import com.me.gacl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/6/26
 * 验证redis缓存
 */
@RestController
@CacheConfig(cacheNames = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    @Cacheable
    public List<User> find() {
        System.out.println("------entering method------");
        return userService.find();
    }

    @GetMapping("/find/{id}")
    @Cacheable(key = "#id", condition = "#id != null")
    public User find(@PathVariable Long id) {
        System.out.println("------entering method------");
        return userService.find(id);
    }

    @GetMapping("/find")
    @Cacheable(key = "#name", condition = "#name != null")
    public User find(@RequestParam String name) {
        System.out.println("------entering method------");
        return userService.find(name);
    }

    /**
     *  allEntries：是否需要清除缓存中所有元素
     *  beforeInvocation：清除操作是否在对应方法成功执行后触发，即方法因为抛出异常未能成功返回不会触发该操作
     * @return
     */
    @GetMapping("/save")
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public User save() {
        System.out.println("------entering method------");
        return userService.save();
    }

    @GetMapping("/update/{id}")
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public User update(@PathVariable Long id) {
        System.out.println("------entering method------");
        return userService.update(id);
    }

    @GetMapping("/delete/{id}")
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public String delete(@PathVariable Long id) {
        System.out.println("------entering method------");
        User user = userService.delete(id);
        if (user == null) {
            return "用户不存在";
        } else {
            return user.getName() + "删除成功";
        }
    }

}
