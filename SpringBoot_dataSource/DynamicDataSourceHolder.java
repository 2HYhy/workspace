package com.changhong.usercenter;

/**
 * @author yunhua.he
 * @date 2017/11/6
 * 1.创建线程共享工具,动态数据源持有者，负责利用ThreadLocal存取数据源名称
 */

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void putDataSource(String name) {
        THREAD_LOCAL.set(name);
    }

    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    public static void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}
