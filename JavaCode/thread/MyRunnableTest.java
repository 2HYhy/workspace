package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/23
 */

class MyRunnable implements Runnable {
    private int ticket = 6;

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+" sell ticket "+this.ticket--);
            }
        }
    }
}

public class MyRunnableTest {
    public static void main(String [] args) {
        MyRunnable run = new MyRunnable();
        Thread run1 = new Thread(run);
        Thread run2 = new Thread(run);
        Thread run3 = new Thread(run);
        run1.start();
        run2.start();
        run3.start();
    }
}

/**
 * MyRunnable实现了接口runnable
 * main创建了3个thread子线程，它们都是基于run这个对象的，共享了MyRunnable的资源，总共卖出6张票
 */
