package com.me.gacl.controller;

import com.me.gacl.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@Controller
@RequestMapping("register")
public class RegistController {
    @RequestMapping(value="/register1",method = RequestMethod.GET)
    public String register(HttpServletRequest request, User user){
        request.setAttribute("username",user.getUsername());
        request.setAttribute("password",user.getPassword());
        return "register_no";
    }
    @RequestMapping(value="/register2",method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user")User user){ // @ModelAttribute("user")是非必须的
        ModelAndView mv=new ModelAndView();
        if(user.getPassword().length()<12 && user.getPassword().length()>6 ){
            user.setId(UUID.randomUUID().toString());
            user.setRegtime(new Date());
            mv.addObject("msg", "注册成功! 您的用户ID为" + user.getId()+
                    "，您的注册日期为:" + user.getRegtime());
            mv.setViewName("register_ok");
            return mv;
        }else{
            mv.addObject("error","注册失败，密码格式错误，请重试！");
            mv.setViewName("register_no");
            return mv;
        }
    }
}
