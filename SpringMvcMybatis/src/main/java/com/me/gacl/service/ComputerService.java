package com.me.gacl.service;

import com.me.gacl.domain.Computer;

/**
 * @author CH-yfy
 * @date 2017/12/22
 */
public interface ComputerService {

    /**
     * 获取计算机信息
     * @param type
     * @return
     */
    Computer selectComputer(String type);
}
