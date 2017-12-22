package com.me.gacl.controller;

import com.me.gacl.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author yunhua.he
 * @date 2017/12/22
 */

@RestController
@RequestMapping("/user")
public class UserController {

    //有构函时，添加一个用户
    @ModelAttribute
    public User user(){
        return new User("010","linda","123qq");
    }
    @RequestMapping("addTwo")
    public String add2() {
        return "user";
    }

    //有构函时，添加多个用户
    @ModelAttribute
    public void getUser(Model model) {
        model.addAttribute("user1", new User("1","apple","123a"));
        model.addAttribute("user2", new User("2","pear","456b"));
    }
    @RequestMapping("/model")
    public String add3() {
        return "user";
    }

    //无构函时，添加一个用户，对应默认的无参构函。
//    @RequestMapping("/addOne")
//    public String add1(@ModelAttribute("usersget") User user) {
//        user.setId("010");
//        user.setUsername("linda");
//        user.setPassword("123qq");
//        return "user";
//    }
}

