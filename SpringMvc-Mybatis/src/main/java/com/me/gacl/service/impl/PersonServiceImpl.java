package com.me.gacl.service.impl;

import com.me.gacl.dao.PersonDao;
import com.me.gacl.domain.Person;
import com.me.gacl.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yunhua.he on 2017/8/23.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired
    private PersonDao personDao;

    public Person getPerson(String name){
        logger.info("=====根据name[{}]获取人物信息====",name);
        return personDao.getPerson(name);
    }

    public boolean addPerson(Person person){
        boolean flag = personDao.addPerson(person);
        logger.info("=====添加人物信息结果====", flag);
        return flag;
    }
}
