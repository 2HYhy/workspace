package com.me.gacl.pojo;

/**
 * @author CH-yfy
 * @date 2017/12/20
 * 事件监听器
 */
public interface UserListener {

    void doBuy(Events event);

    void doSleep(Events event);
}
