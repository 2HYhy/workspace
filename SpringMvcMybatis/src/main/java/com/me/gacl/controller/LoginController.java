package com.me.gacl.controller;

import com.me.gacl.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yunhua.he
 * @date 2017/12/22
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    //第一种登录，登录成功与失败的页面是分开的
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login1(HttpServletRequest request, User user){ // User user=new User(),user这样写也可以
        request.setAttribute("username",user.getUsername());
        request.setAttribute("password",user.getPassword());
        return "login_no";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView login2(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        ModelAndView mv=new ModelAndView();
        String name = "apple";
        String pwd = "123456";
        if(username.equals(name) && password.equals(pwd)){
            mv.addObject("username",username);
            mv.addObject("password",password);
            mv.setViewName("login_ok");
            return mv;
        }else{
            mv.addObject("error","您输入的信息有误，请重新输入！");
            mv.setViewName("login_no");
            return mv;
        }
    }

    //第二种登录
    @RequestMapping("/login")
    public String login(@RequestParam("name")String username,
                        @RequestParam("pwd")String password,Model model){
        if(username.equals("hyh") && password.equals("123qq")){
            model.addAttribute("username",username);
            model.addAttribute("password",password);
            return "login_ok";
        }else{
            model.addAttribute("error","您输入的信息有误，请重新输入！");
            return "login_no";
        }
    }
}
