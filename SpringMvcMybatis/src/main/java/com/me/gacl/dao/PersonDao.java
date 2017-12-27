package com.me.gacl.dao;

import com.me.gacl.domain.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 *
 * @author CH-yfy
 * @date 2017/8/23
 */
@Component
public interface PersonDao {

    Person getPerson(@Param("name")String name);

    boolean addPerson(@Param("param")Person person);
}
