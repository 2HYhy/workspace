package com.me.gacl.dao;

import com.me.gacl.domain.Computer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by yunhua.he on 2017/8/23.
 */
@Component
public interface ComputerDao {

    Computer selectComputer(@Param("type")String type);
}
