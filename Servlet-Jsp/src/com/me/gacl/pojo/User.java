package com.me.gacl.pojo;

/**
 * @author yunhua.he
 * @date 2017/12/20
 * 事件源
 */
public class User {

    private UserListener listener;

    public void buy() {
        if (listener != null) {
            listener.doBuy(new Events(this));
        }
    }

    public void sleep() {
        if (listener !=null) {
            listener.doSleep(new Events(this));
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "listener=" + listener +
                '}';
    }

    /**
     * 注册对User的行为进行监听的监听器
     * @param listener
     */
    public void registerListener(UserListener listener) {
        this.listener = listener;
    }
}

