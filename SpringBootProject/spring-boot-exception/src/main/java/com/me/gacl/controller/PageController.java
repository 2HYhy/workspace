package com.me.gacl.controller;

import com.me.gacl.exception.PageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author CH-yfy
 * @date 2018/6/25
 */
@RestController
@RequestMapping("/page")
public class PageController {

    @GetMapping("/")
    public ModelAndView index() {
        throw new PageException(500, "This is PageException");
    }

}
