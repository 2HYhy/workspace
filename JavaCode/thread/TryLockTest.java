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
        //尝试获取锁，成功返回true，失败返回false，会立即返回。tryLock(long time, TimeUnit unit)方法则会等待一定的时间。
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"得到了锁");
                Thread.sleep(new Long("1000"));
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}