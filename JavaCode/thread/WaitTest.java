package com.me.gacl.thread;

/**
 * wait()和notify()
 */
class ThreadA extends Thread{


    public ThreadA(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName()+ " next notify");
            notify();
        }
    }
}
public class WaitTest {

    public static void main(String [] args) {
        ThreadA ta = new ThreadA("ta");
        synchronized (ta) {
            try {
                System.out.println(Thread.currentThread().getName() + " next start");
                ta.start();
                System.out.println(Thread.currentThread().getName() + " next wait");
                ta.wait();
                System.out.println(Thread.currentThread().getName() + " continue...");
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
/**
 * 1. 主线程main处于运行状态
 * 2. main创建线程ta,使其处于新建状态
 * 3. main获取ta线程的同步锁
 * 4. main启动线程ta,使其处于就绪状态
 * 5. main执行ta.wait(),进入等待(堵塞)状态,并释放ta对象的锁(虽是通过ta调用的wait()方法,但调用的地方是在main中,而main就是当前线程,也是运行状态.所以此处是让main等待,而非ta)
 * 6. ta处于运行状态,通过synchronized(this)获取当前对象的同步锁
 * 7. ta调用notify()唤醒当前对象上的等待线程,即main,使其处于就绪状态
 * 8. ta释放当前对象的锁,进入死亡状态
 * 9. main重新获取ta对象的锁，继续运行
 */

