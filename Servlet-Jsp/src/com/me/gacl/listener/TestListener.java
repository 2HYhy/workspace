package com.me.gacl.listener;

import com.me.gacl.pojo.Events;
import com.me.gacl.pojo.User;
import com.me.gacl.pojo.UserListener;

/**
 * @author yunhua.he
 * @date 2017/12/20
 */
public class TestListener {

    public static void main(String[] args) {
        User user = new User();
        user.registerListener(new UserListener() {
            @Override
            public void doBuy(Events event) {
                User u = event.getSource();
                System.out.println(u+"is buying");
            }

            @Override
            public void doSleep(Events event) {
                User u = event.getSource();
                System.out.println(u+"is sleeping");
            }
        });
        //执行buy的动作
        user.buy();
        //执行sleep的动作
        user.sleep();
    }
}
