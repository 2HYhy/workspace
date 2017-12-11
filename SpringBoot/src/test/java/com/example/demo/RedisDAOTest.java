package com.example.demo;

import com.example.demo.dao.RedisDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yunhua.he
 * @date 2017/12/6
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDAOTest {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisDAO redisDAO;

    @Test
    public void testRedis() {
        redisDAO.setKey("name", "奥斯特洛夫斯基");
        redisDAO.setKey("password", "100100100");
        System.out.println("redis信息已保存");
        String value1 = redisDAO.getKey("name");
        String value2 = redisDAO.getKey("password");
        System.out.println("value="+value1+" && value="+value2);
    }
}
