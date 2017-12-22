package com.me.gacl.dao;

import com.me.gacl.domain.Computer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author yunhua.he
 * @date 2017/12/22
 */
@Component
public interface ComputerDao {

    /**
     * 获取计算机信息
     * @param type
     * @return
     */
    Computer selectComputer(@Param("type")String type);
}
