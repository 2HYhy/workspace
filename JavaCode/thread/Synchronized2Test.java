package com.me.gacl.thread;

/**
 * @author momo
 * @date 2018/7/23
 */
public class Synchronized2Test {
    public static void main(String [] args) {
        MyThread1 t1 = new MyThread1("t1");
        MyThread1 t2 = new MyThread1("t2");
        t1.start();
        t2.start();
    }
}

class MyThread1 extends Thread {

    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (this) {
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(110);
                    System.out.println(Thread.currentThread().getName()+" loop "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * synchronized(this)的this是指MyThread1对象
 * t1和t2是不同的MyThread1对象，获取的是不同对象的同步锁，所以一个线程无需等待另一个线程结束
 */