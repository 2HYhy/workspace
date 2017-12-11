package com.example.demo.controller;

import com.example.demo.bean.UserInvoice;
import com.example.demo.dao.UserInvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yunhua.he
 * @date 2017/12/5
 * spring boot 整合jpa
 */

@RestController
@RequestMapping("/invoice")
public class UserInvoiceController {

    @Autowired
    UserInvoiceDAO userInvoiceDAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UserInvoice invoice) {
        UserInvoice result = userInvoiceDAO.save(invoice);
        if (result != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id){
        UserInvoice user = userInvoiceDAO.findOne(id);
        if (user != null) {
            return user.toString();
        } else {
            return "This user is not exist !";
        }
    }

}
