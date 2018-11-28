//1.异步调用的简单例子
public class Test {
	private ExecutorService es = Executors.newCachedThreadPool();
	private void func() {
		es.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("异步调用的方法");
			}
		});
	}

	public static void main(String[] args) {
        Test test = new Test();
		test.func();
		System.out.println("主线程main的方法");
    }
}
//运行结果是: 主线程main的方法 \n 异步调用的方法


//2.回调的简单例子
public class A {
    public void funcA() { 
        B b = new B();
        b.funcB(new A());
        System.out.println("A类中的funcA方法");
    };
    public void callBack() {
        System.out.println("A类中的callBack回调方法");
    }
}

public class B {
    public void methodB(A a) {
        System.out.println("B类中的funcB方法");
        a.callBack();    //回调
    }
}

public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.methodA();
        }
}
//运行结果是: B类中的funcB方法 \n A类中的callBack回调方法 \n A类中的funcA方法

//3. 线程池的简单例子
public class Test {
	public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));
		for(int i=1;i<16;i++) {
			Task task = new Task(i);
			executor.execute(task);
			System.out.println("线程池中线程数目："+ executor.getPoolSize() +
					"，队列中等待执行的任务数目："+ executor.getQueue().size() +
					"，已执行完毕的任务数目："+ executor.getCompletedTaskCount());
		}
		executor.shutdown();
    }
}
class Task implements Runnable {

	private int taskNum;
	public Task(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {
		System.out.println("正在执行task "+taskNum);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("执行完毕task "+taskNum);
	}
}
/**
刚开始都是在创建新的线程(1-6)，达到核心线程数量6个后，新的任务进来后不再创建新的线程，而是将其加入缓存队列(7-11)。缓存队列达到5个上线后，新的任务到来又会创建新的线程，直到达到线程池的最大线程数量(12-15)。后面若再有新的任务(将for循环改成大于15的数)，就会抛出任务拒绝异常了。
因为当线程池中的任务缓存队列已满并且线程池中的数目达到maximumPoolSize后，如果还有任务到来就会采取任务拒绝策略。
*/