package com.me.gacl.aop;

/**
 * @author CH-yfy
 * @date 2017/12/25
 * 横切关注点
 */
public class LogHandler {

    public void doBefore() {
        System.out.println("the function of [before] about log by join-point");
    }

    public void doAfter() {
        System.out.println("the function of [after] about log by join-point");
    }
}
