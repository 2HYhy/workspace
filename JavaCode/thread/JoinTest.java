package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/19
 */
public class JoinTest extends Thread {

    public static void main(String [] args) {
        ThreadD thd = new ThreadD();
        thd.start();
        System.out.println("...main create thd");
        try {
            thd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after thd finished, main continue...");
    }
}

class ThreadD extends Thread {

    @Override
    public void run() {
        for(int i=1; i<4; i++) {
            System.out.println("This is thd in "+i);
        }
    }
}

/**
 * 主线程main创建并启动子线程thd
 * 调用thd.join()后main进入阻塞状态，等待thd运行结束
 * main再次被唤醒，继续运行
 */
