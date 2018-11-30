//转自http://www.cnblogs.com/dolphin0520/p/3923167.html
public class Test {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        final Test test = new Test();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
    }

    private void insert(Thread thread) {
        //获取锁，如果锁已被其他线程获取，则进行等待
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            //释放锁
            lock.unlock();
        }
    }
}