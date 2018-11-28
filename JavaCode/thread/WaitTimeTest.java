package com.me.gacl.thread;

/**
 * @author momo
 * @date 2018/7/18
 */
class ThreadB extends Thread {

    public ThreadB(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" run");
        while(true)
            ;  //无线循环，不断运行
    }

}
public class WaitTimeTest {

    public static void main(String [] args) {
        ThreadB tb = new ThreadB("tb");
        synchronized(tb) {
            try {
                System.out.println(Thread.currentThread().getName() + " is start");
                tb.start();
                System.out.println(Thread.currentThread().getName() + " is wait");
                //单位是毫秒
                tb.wait(3000);
                //3s之后打印
                System.out.println(Thread.currentThread().getName() + " continue...");
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
/**
 * 1. 主线程main执行tb.start()，启动线程tb
 * 2. 主线程执行tb.wait(3000)，本身进入阻塞状态，并释放对象锁，需要线程tb调用notify()/notifyAll()方法，或者超过3000ms之后才能重新被唤醒
 * 3. 线程tb运行后，进入死循环，不断地运行
 * 4. 超过3s后，主线程main再次进入就绪状态，接着进入运行状态
 */
