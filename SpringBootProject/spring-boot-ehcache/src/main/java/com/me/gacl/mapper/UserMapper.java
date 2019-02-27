package com.me.gacl.mapper;

import com.me.gacl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author CH-yfy
 * @date 2019/2/19
 */
@Mapper
public interface UserMapper {

    void addUser(@Param("user") User user);

    User getUser(@Param("uuid") String uuid);

    void updateUser(@Param("user") User user);

    void deleteUser(@Param("name") String name);
}
