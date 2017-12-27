package com.example.demo.controller.swagger;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2017/12/11
 * spring boot 利用swagger2自动生成在线文档的查阅和测试
 */

@Api("api operation of student")
@RestController
@RequestMapping("stu")
public class StudentController {

    Map<Integer, Student> map = Collections.synchronizedMap(new HashMap<Integer, Student>());

    @ApiOperation(value = "增加一个学生", notes = "add a student")
    @ApiImplicitParam(name = "student", value = "学生实体对象", required = true, dataType = "Student")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Student student) {
        map.put(student.getStuId(), student);
        return " insert success";
    }

    @ApiOperation("修改一个学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生学号", required = true, dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name = "jsonObject", value = "学生实体信息", required = true, dataType = "JSONObject")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, @RequestBody JSONObject json) {
        Student student = new Student();
        student.setStuId(id);
        student.setUsername(json.getString("name"));
        student.setSex(json.getString("sex"));
        map.put(id, student);
        return "update success";
    }

    @ApiOperation("获取一个学生")
    @ApiImplicitParam(name = "id", value = "学生学号", required = true, dataType = "Integer",paramType = "query")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(@RequestParam int id) {
        Student result = map.get(id);
        return result.toString();
    }

    @ApiOperation("删除一个学生")
    @ApiImplicitParam(name = "id", value = "学生学号", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable int id) {
        map.remove(id);
        Student result = map.get(id);
        if( result == null) {
            return "remove success";
        } else {
            return "remove exception";
        }
    }

    @ApiIgnore
    @RequestMapping("/hello")
    public void hello() {
        //忽略
    }
}
