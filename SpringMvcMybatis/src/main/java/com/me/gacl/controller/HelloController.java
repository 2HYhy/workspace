package com.me.gacl.controller;

import com.me.gacl.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("/test1.do")
    public String test1(@RequestParam("name")String username, @RequestParam("pwd")String password, Model model){
        model.addAttribute("userName1",username);
        model.addAttribute("passWord1",password);
        return "hello";
    }
    @RequestMapping("/test2.do/{name}/{pwd}")
    public String test2(@PathVariable("name")String name, @PathVariable("pwd")String password, Model model){
        model.addAttribute("userName2",name);
        model.addAttribute("passWord2",password);
        return "hello";
    }
    @RequestMapping("/test3.do") //和test实质上是一个，URL相同。
    public String test3(HttpServletRequest request, Model model){
        model.addAttribute("userName3",request.getParameter("name"));
        model.addAttribute("passWord3",request.getParameter("pwd"));
        return "test";
    }
    @RequestMapping("/test4.do")
    public ModelAndView test4(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("infos","This is a website named test!!!");
        mv.setViewName("hello");
        return mv;
    }
    @RequestMapping("test5.do")
    public String test5(@ModelAttribute("users")User user){
        return "hello";
    }
}
