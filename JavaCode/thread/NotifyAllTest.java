package com.me.gacl.thread;

/**
 * @author momo
 * @date 2018/7/18
 */
public class NotifyAllTest {

    private static Object obj = new Object();
    public static void main(String [] args) {
        ThreadC tc1 = new ThreadC("tc1");
        ThreadC tc2 = new ThreadC("tc2");
        ThreadC tc3 = new ThreadC("tc3");
        tc1.start();
        tc2.start();
        tc3.start();
        try {
            System.out.println(Thread.currentThread().getName() + " sleep 3000ms");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " notifyAll");
            obj.notifyAll();
        }
    }

    static class ThreadC extends Thread {

        public ThreadC(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized(obj) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
 * 1. 主线程main创建并启动3个线程tc1,tc2,tc3,使其处于就绪状态
 * 2. main调用sleep休眠3秒，这期间tc1,tc2,tc3获取obj对象的同步锁(main并未占用)依次处于运行状态
 * 3. tc1,tc2,tc3调用wait()使自己处于等待(阻塞状态)，等待其他线程调用notify()唤醒
 * 4. 3s过后main接着运行，先获取obj对象锁，再调用notifyAll()唤醒obj上所有等待的线程
 * 5. main执行完synchronized(obj)后释放obj锁，tc1,tc2,tc3获得obj锁继续运行
 */
