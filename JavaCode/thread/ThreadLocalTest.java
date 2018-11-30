public class ThreadLocalTest implements Runnable {

    private static final ThreadLocal<Integer> variable = ThreadLocal.withInitial(() -> 8);

    @Override
    public void run() {
        System.out.println("name = "+Thread.currentThread().getName()+", id = "+Thread.currentThread().getId()+", 本地变量 = " + variable.get());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //variable is changed here by the thread, but it won't reflect to other threads
        variable.set(16);
        System.out.println("本地变量 = "+variable.get()+", name = "+Thread.currentThread().getName()+", id = "+Thread.currentThread().getId());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest local = new ThreadLocalTest();
        for(int i=0; i<3; i++) {
            Thread t = new Thread(local, ""+i);
            t.start();
        }
    }
}
