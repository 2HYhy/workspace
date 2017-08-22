package com.me.gacl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yunhua.he on 2017/8/22.
 */
@ContextConfiguration(locations = {"classpath:application.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest extends AbstractJUnit4SpringContextTests {

}
