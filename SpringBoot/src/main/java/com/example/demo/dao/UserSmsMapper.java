package com.example.demo.dao;

import com.example.demo.bean.UserSms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */
@Mapper
public interface UserSmsMapper {

    /**
     * 添加短信对象
     * @param sms
     * @return
     */
    @Insert("insert into sms(content,pkgname,sendtime) values(#{sms.content},#{sms.pkgName},#{sms.sendTime})")
    int add(@Param("sms") UserSms sms);

    /**
     * 获取短信对象
     * @param pkgName
     * @return
     */
    @Select("select content as content,pkgname as pkgName,sendtime as sendTime from sms where pkgname = #{package}")
    List<UserSms> get(@Param("package") String pkgName);

    /**
     * 修改短信对象
     * @param content
     * @param time
     * @return
     */
    int update(@Param("content") String content, @Param("time") String time);
}
