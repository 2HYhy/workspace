package com.me.gacl.controller;

import com.me.gacl.pojo.PersonDO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2017/12/27
 */
@RestController
@RequestMapping("/another")
public class AnotherController {

    /**
     * 模拟Dao层
     */
    private Map<Long, PersonDO> repository = Collections.synchronizedMap(new HashMap<Long, PersonDO>());

    @ApiOperation("更新人物信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String func(@RequestBody PersonDO person) {
        Long id = person.getId();
        repository.put(id, person);
        return "success";
    }
}
