## java实现多线程
### 继承Thread类：
```java 
public class ThreadController extends Thread {

    private String name;
    private int ticket = 6;

    public ThreadController(String name){
        super();
        this.name=name;
    }

    public void run(){
        for(int i=1;i<=ticket;i++) {
            System.out.println(this.name+ "开始,i=" +i);
            System.out.println("车票第" + i + "张");
        }
    }

    public static void main(String [] args) {
        ThreadController th1 = new ThreadController("线程a");
        ThreadController th2 = new ThreadController("线程b");
//        th1.run();  //先第一个对象运行，再第二个对象运行，程序之间没有相互运行
//        th2.run();
        th1.start();  //通过JVM找到run方法，程序交互式运行
        th2.start();
    }
}
```    
运行结果如图：  
![alt-text](/images/thread.png)

### 实现Runnable接口：
```java
public class RunnableController implements Runnable {

    private String name;
    private int ticket = 6;

    public RunnableController(String name){
        this.name = name;
    }

    public void run(){
        for(int i=1;i<10;i++){
            if(ticket>0) {
                System.out.println(this.name + "开始,i=" + i);
                System.out.println("车票第" + ticket-- + "张");
            }
        }
    }

    public static void main(String [] args) {
        RunnableController ru = new RunnableController("线程");
        new Thread(ru).start();  //Runnable定义的子类中没有start方法，但Thread的一个构造方法可以接受Runnable的子类示例
        new Thread(ru).start();
    }
}
```     
运行结果如图：  
![alt-text](/images/runnable.png)  
## 两者的区别：
1. Thread比较少用,因为:  
1）一个子类只能继承一个父;   
2）资源无法共享。以上为例：定义了两个线程，彼此之间是独立的，各自卖出6张票，共卖出12张。 

2. Runnable比较多用，因为:  
1) 一个类可以继承多个接口;   
2) k可以实现资源共享。以上为例：定义了两个线程，资源是共享的，共卖出6张票。  
