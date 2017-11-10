package com.changhong.usercenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yunhua.he
 * @date 2017/11/6
 */

@Configuration
@EnableScheduling
public class DataSourceConfig {

    @Autowired
    private DbProperties properties;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(100);
        targetDataSources.put("test1", properties.getTest1());
        targetDataSources.put("test2", properties.getTest2());
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(properties.getTest1());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
