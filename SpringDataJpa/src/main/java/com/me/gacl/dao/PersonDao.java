package com.me.gacl.dao;

import com.me.gacl.pojo.PersonDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author CH-yfy
 * @date 2017/12/26
 */
@Component
public interface PersonDao extends CrudRepository<PersonDO, Serializable> {

    // 接口继承关系： Repository<--CrudRepository<--PagingAndSortingRepository<--JpaRepository.
    // jpa的查询方式有三种：1.方法命名查询；2.@NameQuery查询；3.@Query查询.

    /**
     * 根据年龄，时间查询
     * @param age
     * @param createTime
     * @return
     */
    List<PersonDO> findByAgeAndCreateTime(Integer age, String createTime);

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    PersonDO findByName(String name);

    /**
     * 根据id修改全部信息
     * @param id
     * @param name
     * @param age
     * @param time
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update person set age = :age,create_time = :time,name = :name where id = :id", nativeQuery = true)
    int modifyPerson(@Param("id") Long id, @Param("name")String name, @Param("age")Integer age, @Param("time")String time);

    /**
     * 根据姓名更新年龄
     * @param name
     * @param age
     * @return
     */
    @Modifying
    @Query(value = "update person set age = ?2 where name = ?1", nativeQuery = true)
    //缺少该注解即缺少事务配置管理
    @Transactional(rollbackFor = Exception.class)
    int updatePerson(String name, Integer age);
}