package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/23
 */

class MyThread extends Thread {
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

public class MyThreadTest {
    public static void main(String [] args) {
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();
        MyThread th3 = new MyThread();
        th1.start();
        th2.start();
        th3.start();
    }
}

/**
 * MyThread继承了Thread类
 * main创建了3个thread子线程，每个子线程是独立的，都卖出了各自的6张票
 */