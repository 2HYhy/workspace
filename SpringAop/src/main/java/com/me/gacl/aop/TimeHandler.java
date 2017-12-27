package com.me.gacl.aop;

/**
 * @author CH-yfy
 * @date 2017/12/25
 * 横切关注点
 */
public class TimeHandler {

    public void printTime() {
        System.out.println("currentTime is: " + System.currentTimeMillis()+" about time by join-point");
    }
}
