package com.me.gacl.thread;

/**
 * @author momo
 * @date 2018/7/23
 */
public class RunStartTest {
    public static void main(String [] args) {
        RunStart rs = new RunStart("rs");
        System.out.println(Thread.currentThread().getName()+" call run()");
        rs.run();
        System.out.println(Thread.currentThread().getName()+" call start()");
        rs.start();

    }
}

class RunStart extends Thread {

    public RunStart(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
    }
}

/**
 * rs.run()等同于调用普通的方法,运行在主线程main上
 * rs.start()启动子线程,调用run(),它运行在子线程rs上
 */
