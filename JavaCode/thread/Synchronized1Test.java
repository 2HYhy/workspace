package com.me.gacl.thread;

/**
 * @author momo
 * @date 2018/7/23
 */
public class Synchronized1Test {

    public static void main(String [] args) {
        MyRunnable1 run = new MyRunnable1();
        Thread t1 = new Thread(run, "t1");
        Thread t2 = new Thread(run, "t2");
        t1.start();
        t2.start();
    }
}

class MyRunnable1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+" loop "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * run()中存在synchronized代码块
 * 这里的this指的是MyRunnable1对象run，t1,t2都是基于MyRunnable1对象run创建的子线程，所以它们共享该对象run的同步锁
 * 所以一个线程运行时，另一个线程必须等到run的同步锁释放后才能运行
 */