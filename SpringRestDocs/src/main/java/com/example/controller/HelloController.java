package com.example.controller;
import com.example.entity.UserInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH-yfy
 */
@RestController
@RequestMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class HelloController {

    @RequestMapping(value="/info", method = RequestMethod.GET)
    public UserInfo getInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("Linda");
        userInfo.setAddress("山东济南");
        userInfo.setCompany("Big Apple");
        return userInfo;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserInfo saveInfo(@RequestBody UserInfo info) {
        UserInfo newInfo = new UserInfo();
        newInfo.setUsername(info.getUsername());
        newInfo.setAddress(info.getAddress());
        newInfo.setCompany(info.getCompany());
        return newInfo;
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public UserInfo getByName(@PathVariable("name") String name,
                              @RequestParam("address") String address) {
        UserInfo info = new UserInfo();
        info.setUsername(name);
        info.setAddress(address);
        return info;
    }
}
