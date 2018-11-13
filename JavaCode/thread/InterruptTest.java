package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/19
 */
public class InterruptTest {

    public static void main(String [] args) {
        ThreadE the = new ThreadE("the");
        System.out.println(the.getName()+" is "+the.getState()+", created");
        the.start();
        System.out.println(the.getName()+" is "+the.getState()+", started");
        try {
            //主线程休眠
            Thread.sleep(300);
            the.interrupt();
            System.out.println(the.getName()+" is "+the.getState()+", interrupted");
            //再次休眠后查看the的状态
            Thread.sleep(300);
            System.out.println(the.getName()+" is "+the.getState()+", sleeping");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadE extends Thread {

    public ThreadE(String name) {
        super(name);
    }

    @Override
    public void run() {
        //若try-catch是在while循环体内，则需要在catch通过break或return手动退出循环，否则会进入死循环
        try {
            int i = 0;
            while(!isInterrupted()) {
                //当中断标记为false时
                Thread.sleep(100);
                System.out.println("<<<"+System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName()+" is "+this.getState()+", loop i="+i);
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" is "+this.getState()+", catch interruptedException");
        }
    }
}
/**
 * 1. 主线程main创建并启动线程the，随后就休眠300ms
 * 2. 在main休眠期间，the进入运行状态，不断检查自己的终端标记，若为false，就休眠100ms
 * 3. 前300ms时main的休眠还未结束，所以连续打印i=0和1，时间间隔了100ms
 * 4. 300ms后切换回main运行，它执行interrupt()中断了the，接着又去休眠300ms
 * 5. the接收到中断指令，将自己的中断指令设为false，同时抛出InterruptedException异常
 * 6. 切换回主线程main
 */