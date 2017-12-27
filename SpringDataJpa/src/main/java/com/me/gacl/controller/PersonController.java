package com.me.gacl.controller;

import com.alibaba.fastjson.JSONObject;
import com.me.gacl.dao.PersonDao;
import com.me.gacl.pojo.PersonDO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author CH-yfy
 */
@RestController
@RequestMapping(value = "/person")
//可以不添加，会默认添加
@Api("PersonController")
public class PersonController {

    @Autowired
    PersonDao personDao;

    @ApiOperation(value = "新增用户1")
    @RequestMapping(value = "/add1/{age}",  method = RequestMethod.POST)
    // paramType表示参数的位置，常用的有header即@RequestHeader、query即@RequestParam、path即@PathVariable
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "name", dataType = "String", required = true, value = "用户的姓名"),
            @ApiImplicitParam(paramType = "path", name = "age", dataType = "Integer", required = true, value = "用户的年龄"),
            @ApiImplicitParam(paramType = "query", name = "createTime", dataType = "String", required = true, value = "用户创建时间")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数不正确"),
            @ApiResponse(code = 404, message = "请求路径或页面跳转路径不对")
    })
    public String addPerson(@RequestHeader String name,
                            @PathVariable Integer age,
                            @RequestParam String createTime){
        PersonDO person = new PersonDO();
        person.setName(name);
        person.setAge(age);
        person.setCreateTime(createTime);
        PersonDO per = personDao.save(person);
        if (per != null) {
            return per.toString();
        } else {
            return "add fail";
        }
    }

    @ApiOperation("新增用户2")
    @RequestMapping(value = "/add2", method = RequestMethod.POST)
    @ApiImplicitParam(name = "person", value = "用户详细实体", required = true, dataType = "PersonDO")
    @ApiResponses({
            @ApiResponse(code = 401, message = "未认证"),
            @ApiResponse(code = 403, message = "请求被禁止")
    })
    public String savePerson(@RequestBody PersonDO person) {
        //满足构造函数
        personDao.save(person);
        return "add person successfully";
    }

    @ApiOperation(value = "获取用户1", notes = "根据年龄获取用户列表")
    @RequestMapping(value = "/get1/{age}", method = RequestMethod.GET)
    public String getPerson(@PathVariable("age") Integer age,
                            @RequestParam(value = "createTime", required = false) String createTime) {
        List<PersonDO> person = personDao.findByAgeAndCreateTime(age, createTime);
        if (person.size() > 0) {
            return person.get(0).toString();
        } else {
            return "person is not exist";
        }
    }

    @ApiOperation("获取用户2")
    @RequestMapping(value = "/get2", method = RequestMethod.GET)
    public String getPerson(@RequestParam(value = "name") String name) {
        PersonDO person = personDao.findByName(name);
        if (person != null) {
            return person.toString();
        } else {
            return " the person is not exist";
        }
    }

    /**
     * 修改用户，也可调用自带的save方法
     * @param person
     * @return
     */
    @ApiOperation("修改用户1")
    @RequestMapping(value = "/update1", method = RequestMethod.POST)
    @ApiImplicitParam(name = "person", value = "用户详细实体", required = true, dataType = "PersonDO")
    public String updatePerson(@RequestBody PersonDO person) {
        Long id = person.getId();
        Integer age = person.getAge();
        String name = person.getName();
        String time = person.getCreateTime();
        int flag = personDao.modifyPerson(id, name, age, time);
        if (flag >= 1) {
            return "modify success";
        } else {
            return "modify fail";
        }
    }

    @ApiOperation("修改用户2")
    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public String updatePerson(@RequestBody JSONObject json) {
        String name = json.getString("name");
        Integer age = json.getInteger("age");
        int flag = personDao.updatePerson(name, age);
        if (flag >= 1) {
            return "update success";
        } else {
            return "update fail";
        }
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") Long id){
        //验证用户是否存在
        boolean flag = personDao.exists(id);
        if (!flag) {
            return "person does not exist";
        } else {
            //去删除
            personDao.delete(id);
            return "delete success";
        }
    }

    //使用该注解忽略这个API
    @ApiIgnore
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  sayHello() {
        return "Hello!";
    }
}
