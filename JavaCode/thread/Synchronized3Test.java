package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/24
 */
public class Synchronized3Test {

    public static void main(String [] args) {
        Synchronized s = new Synchronized();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s.synFunc();
            }
        }, "t1");
        Thread t2 = new Thread(() -> s.nonSynFunc(), "t2");

        t1.start();
        t2.start();

        Thread t3 = new Thread(() -> s.synFuncs(), "t3");
        t3.start();
    }
}

class Synchronized {

    public void synFunc() {
        synchronized (this) {
            try {
                for (int i=0;i<3;i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+" loop "+i+ " by sync");
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nonSynFunc() {
        try {
            for (int i=0;i<3;i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+" loop "+i+" by nonSync");
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    //另一个同步代码块
    public void synFuncs() {
        synchronized (this) {
            try {
                for (int i=0;i<3;i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+" loop "+i+ " by syncs");
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * 主线程main创建了两个子线程
 * t1调用s对象的同步代码块方法，t2调用s对象的非同步代码块方法
 * t1运行时不会造成t2的阻塞，因为他不需要s对象的同步锁
 *
 * t1和t3运行时都调用synchronized (this)同步代码块，this就是s对象，t1和t3共用它
 * 所以t1运行时，t3会被阻塞，等待t1释放同步锁，t3才能运行
 */
