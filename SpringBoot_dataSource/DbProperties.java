package com.changhong.usercenter;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * @author yunhua.he
 * @date 2017/11/6
 * 5.定义实际数据源配置类
 */

@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DbProperties {
    private DruidDataSource test1;
    private DruidDataSource test2;

    public DruidDataSource getTest1() {
        return test1;
    }

    public void setTest1(DruidDataSource test1) {
        this.test1 = test1;
    }

    public DruidDataSource getTest2() {
        return test2;
    }

    public void setTest2(DruidDataSource test2) {
        this.test2 = test2;
    }
}
