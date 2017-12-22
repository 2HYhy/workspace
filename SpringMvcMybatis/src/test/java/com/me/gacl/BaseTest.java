package com.me.gacl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @author yunhua.he
 * @date 2017/12/22
 */

//指定bean注入的配置文件
@ContextConfiguration(locations={"classpath:config/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
}
