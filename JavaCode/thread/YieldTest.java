package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/25
 */
public class YieldTest {

    public static void main(String [] args) {
        Yield y1 = new Yield("y1");
        Yield y2 = new Yield("y2");
        y1.start();
        y2.start();
        System.out.println("--------------------------------");
        Yields ys1 = new Yields("ys1");
        Yields ys2 = new Yields("ys2");
        ys1.start();
        ys2.start();
    }
}

class Yield extends Thread {

    public Yield(String name) {
        super(name);
    }

    @Override
    public synchronized void run() {
        for (int i=0;i<8;i++) {
            System.out.println("---"+this.getName()+"---"+this.getPriority()+",  i="+i);
            if (i % 4 == 0){
                Thread.yield();
            }
        }
    }
}

class Yields extends Thread{
    public static Object obj = new Object();

    public Yields(String name) {
        super(name);
    }
    @Override
    public void run() {
        synchronized (obj) {
            for (int i=0;i<8;i++) {
                System.out.println(this.getName()+"-"+this.getPriority()+"-"+i);
                if (i % 4 == 0){
                    Thread.yield();
                }
            }
        }
    }
}

/**
 * 当线程y1遇到整除时，可能会让步给同一优先级的y2,也可能不会让步
 * 所以运行结果是随机的
 *
 * ys1和ys2共用obj对象的同步锁
 * 当ys1遇到整除时，虽会进行让步，但并未释放同步锁
 * 所以ys2无法获取CPU执行权，进入运行状态
 */
