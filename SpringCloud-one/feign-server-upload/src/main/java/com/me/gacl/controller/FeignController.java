package com.me.gacl.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author CH-yfy
 * @date 2018/4/27
 */
@RestController
@RequestMapping("/hyh")
public class FeignController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void func(@RequestParam(value = "name") String name,
                     @RequestParam(value = "reqJson") String json,
                     @RequestParam(value = "token") String token) {
        System.out.println("feign调用restful服务 By POST: "+name+", "+json+", "+token);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String func2() {
        return "feign调用restful服务 By GET";
    }
}
