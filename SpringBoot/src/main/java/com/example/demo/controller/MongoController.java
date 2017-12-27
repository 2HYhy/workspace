package com.example.demo.controller;

import com.example.demo.bean.MongoBean;
import com.example.demo.dao.MongoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2017/12/6
 * spring boot整合mongodb
 */

@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private MongoDAO mongoDAO;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save() {
        MongoBean bean1 = new MongoBean();
        bean1.setId(1001);
        bean1.setUserName("apple");
        bean1.setPassword("abc123");
        MongoBean bean2 = new MongoBean();
        bean2.setId(1002);
        bean2.setUserName("pear");
        bean2.setPassword("qaz123");
        MongoBean result = mongoDAO.save(bean1);
        mongoDAO.save(bean2);
        return result.toString();
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll() {
        System.out.println(">>>>>findAll records>>>>>>");
        for(MongoBean mongo : mongoDAO.findAll()) {
            System.out.println("获取到的记录:" + mongo);
        }
        return "successfully  get";
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public String findOne() {
        System.out.println("find records by username");
        MongoBean mongo = mongoDAO.findByUserName("pear");
        return mongo.toString();
    }

    //利用MongoTemplate实现
    @Autowired
    private MongoTemplate mongoTemplate;
    @RequestMapping("/find")
    public String find(@RequestParam String password) {
        Criteria base = Criteria.where("password").is(password);
        Query query = new Query(base);
        MongoBean result = mongoTemplate.findOne(query, MongoBean.class);
        return result.toString();
    }

}
