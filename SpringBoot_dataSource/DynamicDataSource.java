package com.changhong.usercenter;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yunhua.he
 * @date 2017/11/6
 * 2.动态数据源实现类
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //从共享线程中获取数据源名称
        return DynamicDataSourceHolder.getDataSource();
    }
}
