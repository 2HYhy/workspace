public class Test {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void setValue() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLongValue() {
        return longLocal.get();
    }

    public String getStringValue() {
        return stringLocal.get();
    }

    public static void main(String [] args) throws InterruptedException {
        Test test = new Test();
        //get之前若不先set,则get的结果为null
        test.setValue();
        System.out.println("main id :" + test.getLongValue());
        System.out.println("main name :" + test.getStringValue());
        Thread th = new Thread() {
            public void run() {
                test.setValue();
                System.out.println("th id :" + test.getLongValue());
                System.out.println("th name :" + test.getStringValue());
            }
        };
        th.start();
        //让主线程等待子线程运行完毕
        th.join();
        //验证主线程的变量并不受th线程影响
        System.out.println("again main id :" + test.getLongValue());
        System.out.println("again main name :" + test.getStringValue());
    }
}

//可以代替setValue方法，即get前不set,直接initialValue
ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };
