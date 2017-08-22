package com.me.gacl;

import com.me.gacl.service.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yunhua.he on 2017/8/22.
 */
//redis:单线程，耗内存
public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void getTimestampTest() throws InterruptedException {
        System.out.println("第一次调用:时间戳= "+redisService.getTimestamp());
        Thread.sleep(2000);  //线程休眠2秒
        System.out.println("2秒后调用:时间戳= "+redisService.getTimestamp());
        Thread.sleep(12000);  //线程再休眠12秒
        System.out.println("再过12秒后调用:时间戳= "+redisService.getTimestamp());
    }
}
