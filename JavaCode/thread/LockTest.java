package com.me.gacl.thread;

/**
 * @author yunhua.he
 * @date 2018/7/24
 */
class Something {
    public synchronized void isSyncA() {
        try {
            for (int i=0; i<3; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() +" isSyncA");
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public synchronized void isSyncB() {
        try {
            for (int i=0; i<3; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() +" isSyncB");
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void cSyncA(){
        try {
            for (int i=0; i<3; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() +" cSyncA");
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void cSyncB(){
        try {
            for (int i=0; i<3; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() +" cSyncB");
            }
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
public class LockTest {
    Something x = new Something();
    Something y = new Something();

    private void test1() {
        Thread t11 = new Thread(() -> x.isSyncA(), "t11");
        Thread t12 = new Thread(() -> x.isSyncB(), "t12");
        t11.start();
        t12.start();
    }

    private void test2() {
        Thread t21 = new Thread(() -> x.isSyncA(), "t21");
        Thread t22 = new Thread(() -> y.isSyncA(), "t22");
        t21.start();
        t22.start();
    }

    private void test3() {
        Thread t31 = new Thread(() -> x.cSyncA(), "t31");
        Thread t32 = new Thread(() -> y.cSyncB(), "t32");
        t31.start();
        t32.start();
    }

    private void test4() {
        Thread t41 = new Thread(() -> x.isSyncA(), "t41");
        Thread t42 = new Thread(() -> Something.cSyncA(), "t42");
        t41.start();
        t42.start();
    }


    public static void main(String [] args) {
        LockTest lt =new LockTest();

//        lt.test1();
//        System.out.println("-------");

        lt.test2();
        System.out.println("-------");

//        lt.test3();
//        System.out.println("-------");
//
//        lt.test4();
//        System.out.println("-------");
    }
}
