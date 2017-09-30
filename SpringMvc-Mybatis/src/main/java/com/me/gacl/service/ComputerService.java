package com.me.gacl.service;

import com.me.gacl.domain.Computer;

/**
 * Created by yunhua.he on 2017/8/23.
 */
public interface ComputerService {

    Computer selectComputer(String type);

}
