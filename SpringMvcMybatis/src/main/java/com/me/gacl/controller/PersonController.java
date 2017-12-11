package com.me.gacl.controller;

import com.me.gacl.domain.Person;
import com.me.gacl.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yunhua.he on 2017/8/24.
 */
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public ModelAndView getIndex(){
        ModelAndView model = new ModelAndView("index");
        Person person = personService.getPerson("Linda");
        model.addObject("person",person);
        return model;
    }

    @RequestMapping("/getUser")
    public ModelAndView getUser(@RequestParam("name")String name){
        ModelAndView modelAndView=new ModelAndView();
        Person user=personService.getPerson(name);
        modelAndView.addObject("userinfo",user);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
