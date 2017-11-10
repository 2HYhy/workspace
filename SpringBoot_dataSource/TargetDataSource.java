package com.changhong.usercenter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yunhua.he
 * @date 2017/11/6
 * 3.创建数据源切换方法注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TargetDataSource {
    //接收数据源名称
    String value();
}

// 创建完所需类后，还要在启动类中添加注解:不让SpringBoot自己配置
// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// mapper中的方法上使用@TargetDataSource(“test1”)注解