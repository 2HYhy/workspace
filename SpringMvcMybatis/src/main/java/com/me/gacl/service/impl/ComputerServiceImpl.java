package com.me.gacl.service.impl;

import com.me.gacl.dao.ComputerDao;
import com.me.gacl.domain.Computer;
import com.me.gacl.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CH-yfy
 * @date 2017/12/22
 */

@Service
public class ComputerServiceImpl implements ComputerService {

    private final static Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
    @Autowired
    private ComputerDao computerDao;

    @Override
    public Computer selectComputer(String type){
        Computer computer = computerDao.selectComputer(type);
        logger.info("查询电脑信息结果:[{}]",computer);
        return computer;
    }
}
