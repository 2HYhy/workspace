 ### 一. Java进程与线程
*转载自http://www.cnblogs.com/skywang12345/p/3479202.html*

#### 进程
> 一个程序进入内存运行，便成为一个进程。拥有自己独立的系统资源和地址空间，动态产生和消亡。

#### 线程
> 是进程中的一个实体，不拥有系统资源，但可与同属一个进程的其他线程共享进程的所有资源。
> java中主线程main是由Java虚拟机创建的，程序一启动就会自动运行。

进程让操作系统的并发性成为可能，线程让进程的内部并发成为可能。进程是操作系统进行资源分配的基本单位，线程是操作系统进行调度的基本单位。

#### 用户线程和守护线程
1. Thread.isDaemon(false)是用户线程，Thread.isDaemon(true)是守护线程(依赖于创建它的线程)    
2. 默认是用户线程
3. Java虚拟机一旦启动，就会一直运行，直到exit()方法被执行或者只有用户线程在运行。  

#### 线程的5种状态
1. 新建状态(New):
> 线程对象被新建后，就进入了新建状态，此时由java虚拟机分配了内存         

2. 就绪状态(Runnable):
> 线程对象被调用了start()方法后，就处于就绪状态，也叫可执行状态，随时可以被CPU调度执行

3. 运行状态(Running):
> 线程获取CPU权限进行运行，线程只能从就绪状态进入运行状态

4. 阻塞状态(Blocked/Waiting):
> 线程因为某种原因放弃CPU使用权，暂时停止运行。阻塞状态分为3种:
>- 等待阻塞：调用wait方法，让线程等待某工作的完成    
>- 同步阻塞：线程在调用synchronized获取同步锁时失败，因为锁被其他线程占用      
>- 其他阻塞：调用了线程的sleep()，join()或发出了I/O请求

5. 死亡状态(Dead):
> 线程执行完了或异常退出run方法，结束了生命周期

#### 实现多线程的两种方式
1. 继承Thread类(各子线程彼此独立) 
2. 实现Runnable接口(各子线程资源共享)
 *两者的区别*
> 1. Thread比较少用,因为:  
>- 1）一个子类只能继承一个父类;   
>- 2）资源无法共享。eg：定义了两个线程，彼此之间是独立的，各自卖出6张票，共卖出12张。 

> 2. Runnable比较多用，因为:  
>- 1) 一个类可以实现多个接口;   
>- 2) 资源可以共享。eg：定义了两个线程，资源是共享的，共卖出6张票。 

#### start()和run()区别
1. start()用于启动一个新线程，会调用相应的run方法，不能重复被调用       
2. run()用于定义需要执行的任务，和普通成员方法一样可以被重复调用，当直接调用时，会在当前线程中执行，不会启动新线程   

#### synchronized关键字
1. 原理
> java中，每一个对象有且仅有一个同步锁，即同步锁是依赖于对象而存在。    
> 不同线程对同步锁的访问是互斥的。即同一时间点，对象的同步锁只能由一个线程获取到。     
> synchronized(this)指当前所在类的对象的同步锁，synchronized(obj)指“obj这个对象”的同步锁。

2. 基本规则
> 当一个线程访问“某对象”的“synchronized方法”或"synchronized代码块"时，其他线程对"该对象"的该"synchronized方法"或"synchronized代码块"的访问将被阻塞      
> 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问"该对象"的非同步代码块      
> 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对"该对象"的其他"synchronized方法"或"synchronized代码块"的访问将被阻塞

3. “synchronized方法”是用synchronized修饰方法，而“synchronized代码块”则是用synchronized修饰代码块。  
```java
public synchronized void func() {
    //
}
public void func() {
    synchronized (this) {
        //
    }
 //当访问两者出现异常时，JVM会自动释放当前线程占用的锁，不会导致死锁的现象
}
```
4. 实例锁和全局锁
> 实例锁指锁在一个实例对象上，对应的就是synchronized关键字       
> 全局锁指锁在一个类上，对应的就是static synchronized关键字

> eg: Something有两个实例x 和 y, 以及四个方法
```java
01) x.isSyncA() 与 x.isSyncB() 

02) x.isSyncA() 与 y.isSyncA()

03) x.cSyncA() 与 y.cSyncB()

04) x.isSyncA() 与 Something.cSyncA()

/**
01: 不能被同时访问，因为2个方法都是访问同一个对象x的同步锁     
02: 可以同时被访问，因为访问的不是同一个对象的同步锁     
03: 不能同时被访问，因为2个方法都是static类型，相当于共用的都是Something类的同步锁     
04: 可以同时被访问，因为x.isSyncA()使用的是对象x的同步锁，而后者使用的是Something类的锁
*/
```
#### Lock接口类
> 必须要用户手动释放锁，如果没有主动释放锁，就有可能出现死锁(两个以上的线程永远阻塞的情况)现象。       
> ReentrantLock是唯一实现了Lock接口的类。 

#### 等待与唤醒
*当前线程是指正在cpu上运行的线程* 

1. wait()
> 让当前线程处于等待(阻塞)状态，并释放同步锁，直到其他线程调用此对象的notify()或notifyAll()方法，方可被唤醒进入就绪状态

2. wait(long timeout)
> 让当前线程处于等待(阻塞)状态，并释放同步锁，直到其他线程调用此对象的notify()或notifyAll()方法，或者超过指定的时间量，方可被唤醒进入就绪状态

3. notify()
> 唤醒在此对象监视器上等待的单个线程

4. notifyAll()
> 唤醒在此对象监视器上等待的所有线程

5. 
> wait()和notify()之间正是通过对象的同步锁关联起来的。而同步锁是对象持有的，这就是notify(), wait()等函数定义在Object类，而不是Thread类中的原因。

#### yield让步
> 使当前线程由运行状态变成就绪状态，从而让其他具有相同优先级的线程获取执行权     
> 但不能保证当前线程调用yield后，其他同优先级线程就一定能获得执行权，也可能是当前线程又进入运行状态      
> 不会释放同步锁
> 定义在Thread中      

#### sleep休眠
> 让当前线程由运行状态进入休眠阻塞状态，不会释放同步锁
> 定义在Thread中  

#### join()
> 让主线程等待子线程结束之后才能继续运行
> 定义在Thread中

#### interrupt中断终止线程
> interrupt()既能返回中断标记也能清除中断标记。可以中断处于阻塞状态的线程，不能中断正在运行中的线程。调用此方法相当于将中断标记置为true。   
> isInterrupted()返回中断标记，通过判断终端标记是否为true来中断线程的运行。 

#### 关于多线程的一些理解
1. 多个线程同时访问同一个资源时，就会产生线程安全问题。(解决方法就是使用sychronized关键字或者Lock接口类实现同步互斥访问)。
2. 多个线程同时访问同一个方法时，不会出现线程安全问题，因为方法是在栈上运行的，而栈是线程私有的。
3. 并发程序要正确执行，就得保证原子性、可见性以及有序性。三者缺一就有可能会导致程序运行不正确。
   > 原子性是指:一个操作要么全部执行并且执行的过程不被任何因素打断，要么全都不执行。
   > 可见性是指:多个线程访问同一个变量时，若其中一个线程修改了该变量的值，其他线程能够立即看得到修改后的值。
   > 有序性是指:程序执行的顺序按照代码的先后顺序执行。
4. 在java中，只有简单的读取、赋值操作(将数字赋值给某个变量)才是原子性操作。变量之间的相互赋值不是原子性操作。提供了关键字volatile来保证可见性和有序性。synchronized和Lock也能够保证可见性和有序性。         

#### 关于锁的分类
*转自:http://www.cnblogs.com/dolphin0520/p/3923167.html*
1. 可重入锁:  
> synchronized和ReentrantLock都是可重入锁。eg:当一个线程执行到synchronized方法method1时，在method1中会调用另外一个synchronized方法method2，此时线程不必重新去申请锁，而是可以直接执行方法method2。

2. 可中断锁:   
> synchronized不是可中断锁，而Lock是可中断锁。eg:如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，突然线程B不想等了，想先处理其他事情，可以让它中断自己或者在别的线程中中断它。

3. 公平锁:    
> 指的是有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该锁。synchronized就是非公平锁，ReentrantLock默认情况下是非公平锁，但是可以设置为公平锁。

#### Condition接口类
1. 依赖于Lock接口，基本方法是await()和signal()         
2. 上面2个方法必须在lock.lock()和lock.unlock()之间才可以使用       
3. await()对应Object的wait(), signal()对应Object的notify(), signalAll()对应Object的notifyAll()

#### Java的三种调用方式
> 同步调用: 是指按照先后顺序一个一个的执行。必须等到前一个方法运行结束，返回结果后，后一个方法才能运行。

> 异步调用: 是指不用等到前一个方法完全执行完毕并返回结果，就可以运行, 一般用作异步多线程。

> 回调: 是指调用完其他类的方法后再回过头来调用自己的方法(不能在自己类中直接调用，而要在其他类中调用)。


### 二、java并发编程之线程池
*转载自http://www.cnblogs.com/dolphin0520/p/3932921.html*
1. 线程池核心类TheadPoolExecutor的介绍      
四种构造方法中各个参数的含义:      
> corePoolSize
>- 核心池的大小。默认情况下，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，即在任务到来之前就创建corePoolSize个或一个线程。否则的话，在创建了线程池之后，线程池中的线程数为0，当有任务到来之后，就会创建一个线程去执行任务。当线程池中的线程数达到corePoolSize之后，就会把到达的任务放入缓存队列中。

> maximumPoolSize
>- 线程池最大线程数，表示在线程池中最多能创建多少个线程。线程池中的当前线程数(poolSize)不会超过该值。

> keepAliveTime
>- 线程没有任务执行时最多保持多长时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用。

> unit
>- 参数keepAliveTime的时间单位。

> workQueue
>- 阻塞队列，用来存储等待执行的任务。一般使用LinkedBlockingQueue和Synchronous。

> threadFactory
>- 线程工厂，主要用来创建线程。

> handler
>- 当拒绝处理任务时的策略，有ThreadPoolExecutor.AbortPolicy等4种取值。

类ThreadPoolExecutor 继承自 抽象类AbstractExecutorService 继承自 接口ExecutorService  继承自  接口Executor。

ThreadPoolExecutor中的重要方法:
> execute(): 向线程池提交一个任务，交由线程池去执行。

> submit(): 也是用来向线程池提交任务的，与execute的不同在于利用了Futute获取任务执行结果并返回。

> shutdown(): 用来关闭线程池但不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务。

> shutdownNow(): 立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务。

2. 线程池的状态
runState表示当前线程池的状态，有以下取值:
> RUNNING:创建线程池后的初始状态         
> STOPED:调用了shutdown()方法，此时线程池不能够接受新的任务，它会等待所有任务执行完毕      
> SHUTDOWN:调用了shutdownNow()方法，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务
> TERMINATED:线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后

3. 任务的执行
任务提交给线程池后的处理策略分为四种:
> 1. 若当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务

> 2. 若当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务

> 3. 若当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理

> 4. 若线程池中的线程数量大于corePoolSize，若某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize

在实际的java代码应用中，不提倡使用`ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5));`, 而是使用Executors类中提供的几个静态方法来创建线程池。`ExecutorService es = Executors.newCachedThreadPool()/newSingleThreadExecutor/newFixedThreadPool(6)`。

4. 提交一个线程到线程池中，线程池的处理流程
>1. 判断线程池里的核心线程是否都在执行任务，如果不是（核心线程空闲或者还有核心线程没有被创建）则创建一个新的工作线程来执行任务。如果核心线程都在执行任务，则进入下个流程。

>2. 线程池判断缓存队列是否已满，如果缓存队列没有满，则将新提交的任务存储在这个缓存队列里。如果缓存队列满了，则进入下个流程。

>3. 判断线程池里的线程是否都处于工作状态，如果没有，则创建一个新的工作线程来执行任务。如果已经满了，则交给饱和(拒绝)策略来处理这个任务。

### 三、Java静态变量、方法、类

#### 静态变量和方法
1. 静态方法可以直接调用同类的静态变量，不能直接调用同类的非静态变量    
2. 静态方法调用同类的非静态变量，要先创建对象，再通过对象调用同类的非静态变量   
3. 普通成员方法可以直接调用同类的静态和非静态成员    
4. 静态方法不能直接调用非静态方法，需要通过对象调用   

#### 静态类
1. 一个类要被声明为static，只能是静态内部类   
2. 静态内部类只能访问外部类的静态成员变量和方法，不能访问非静态成员变量和方法   
3. 普通内部类可以访问外部类的任意成员变量和方法   
4. 静态内部类可以声明普通成员变量和方法，普通内部类不能声明静态成员变量和方法   
5. 静态内部类的初始化不需要外部类的实例
```java
Inner in = new Outer.Inner();
```
6. 普通内部类的初始化依赖于外部类的实例
```java
Outter out = new Outter();
Inner in = out.new Inner();
```

### 四、Java抽象类和方法，多态与重载和重写
#### 抽象
1. 抽象类中可以有抽象方法和非抽象方法
2. 有抽象方法的一定是抽象类，但抽象类包含抽象方法+一般方法

#### 重载
1. 发生在同一个类中
2. 方法名称相同，参数的个数或类型不同
3. 不能通过访问权限，返回值类型和抛出异常来实现
4. 允许返回值类型不同，访问修饰符不同(即这两者不同时并不算重载)

#### 重写
1. 发生在继承类中
2. 方法名称，返回值类型，参数类型完全相同
3. 子类方法访问权限不能小于父类中该方法的访问权限
4. 子类只能抛出比父类更小的异常，或者不抛出异常

#### 多态
1. 定义:
> 同一消息可以根据发送对象的不同而采用多种不同的行为方式

2. 存在的三个必要条件:
> 有继承
> 有重写
> 父类引用指向子类对象

3. 实现方式:
> 同一类中进行方法重载，继承类中进行方法重写

### 五、Java访问权限修饰符
| 范围   | public | protected | default | private |
|--------|--------|-----------|--------|---------|
| 同一个类 |  yes    |   yes  |  yes  |    yes    |
| 同一个包 |   yes   |   yes   |   yes  |    no   |
| 子父类   |   yes   |   yes   |   no   |  no     |
| 不同包   |  yes    |   no    |  no    |   no    |

### 六、Java的equals()和hashCode()方法
#### equals()
定义在JDK的Object.java中，作用是判断两个对象是否相等。
使用默认的equals()方法，等价于“==”方法，实际上比较的是两个对象的地址是否相等
通常都会重写equals()方法，用来比较两个对象的内容是否相同。

#### hashCode()
定义在JDK的Object.java中，作用是获取对象的散列码。只有在散列表HashMap，HashSet，HashTable中才有用。
HashSet实现了Set接口，它不允许集合中出现重复元素,存储的是对象，使用add()添加元素。
HashMap实现了Map接口，它不允许出现重复的键(key),存储的是键值对，使用put()添加元素。
HashMap是无序的，TreeMap是按自然顺序或自定义顺序的，LinkedHashMap是输出顺序和输入顺序相同的。

#### equals()和hashCode()
1. 类中没用到HashSet, HashTable, HashMap等这些本质是散列表的数据结构: 
equals()用来比较该类的两个对象是否相等。而hashCode()没有任何作用，可以忽略。
2. 类中有用到HashSet, HashTable, HashMap等这些本质是散列表的数据结构: 
如果两个对象相等(即equals比较两个对象时，返回true)，那么它们的hashCode一定相等；如果两个对象的hashCode相等，它们并不一定相等。   
3. 总而言之: 当需要对比时，首先用hashCode()比，如果hashCode()不一样，那这两个对象肯定不相等(也就是不必再用equal()比了),如果hashCode()相同，再用equal()比，如果equal()也相同，那这两个对象就真的相同了。

##### 日期相关函数：  
 > `Date date = new Date();`    获取系统当前日期和时间  
 > `System.currentMillis();` 获取当前毫秒级时间   
 > `SimpleDateFormat sdf = new SimpleFormat("yyyy-MM-dd HH:mm:ss);` 时间格式化  
 > `Date date = sdf.parse(stringtime);` 字符串转日期格式   
 > `String time = sdf.format(date);` 日期转字符串格式       
 ``` java 
 Calendar cal = Calendar.getInstance(); //获取当前日历 
 cal.setTime(date); //把日期赋给日历
 long bucket = date2.getTime() - date1.getTime(); //日期相差毫秒数
 int days =  bucket / (1000*60*60*24);   // 换算成天数
 ```    
##### 命令运行Java程序：  
1. 进入Java类所在目录  
2. 编译:  `javac 类名.java`
3. 运行:  `java 类名`
4. 输出结果`      

##### 数据类型转换： 
`int intdate = Integer.parseInt(stringdata);`  

`String stringdata = String.valueOf(intdata);`

`String stringdata = JSONObject.toJSONString(jsonObject);`  

`JSONObject json = JSONObject.parseObject(stringdata);`   

`ClassDO classDO = JSON.parseObject(stringdata,ClassDO.class);`  

`ClassDO classDO = JSONObject.parseObject(String.valueOf(jsonObject),ClassDO.class);`       

`String stringdata = JSON.toJSONString(classDO);`   

`List<ClassDo> list = JSONArray.parseArray(jsonArray.toJSONString());`  

> 基本数据类型转换
> - 隐式类型转换(自动类型转换): 从存储范围小的类型到存储范围大的类型{ byte ->short(char)->int->long->float->double }。          
> - 显式类型转换(强制类型转换): 从存储范围大的类型到存储范围小的类型{ double→float→long→int→short(char)→byte }。

> 父类和子类数据类型转换
> - 子类可以直接向父类转换(因为子类一定是父类的一个实例)      
> - 若父类为子类的实例, 就可以直接强转; 否则不能强转。可以先执行`if(object instanceof Integer/Long/xxx)`判断父类变量是否为子类的一个实例，否则可能会抛出ClassCastException异常)。   

```java
//Object to String
Onject obj = "apple";  //父类是子类的实例
String string = (String) obj;

Object obj = new Object();
String string = String.valueOf(obj);
String string = obj.toString();

//Object to int/Integer
Object obj = 100;  //父类是子类的实例
int param = (int) obj;

Object obj = "200";
Object obj = 300L;
int param = Integer.parseInt(obj.toString());

//Obejct to long/Long
Object obj = 400L;  //父类是子类的实例
long param = (long) obj;

Object obj = "400";
long param = Long.valueOf(obj.toString());

// Object 转成其他类，当不是该子类的实例时，思路是先将Object转成String，然后再转成对应类型
```
##### 时间类型转换:
 String-->Date    
```java 
String time = "2017-07-26 10:06:09";
Date date = new Date();
SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
date = df.parse(time);
System.ou.println("date="+date);  
```
Date-->String    
```java
String time = "";
Date date = new Date();
DateFormat df = new DateFormat("yyyy-mm-dd HH:mm:ss");
time = df.format(date);
System.ou.println("time="+time);  
```
String-->Timestamp   
```java
Timestamp ts = new Timestamp(System.currentTimeMillis());
String time = "2011-05-09 11:49:45";
ts = Timestamp.valueOf(time);
System.out.println(“ts="+ts);   
```
Timestamp-->String  
```java
Timestamp ts = new Timestamp(System.currentTimeMillis());
String time = "";
//两种方式，一种借助format,一种借助toString
DateFormat df = new DateFormat("yyyy-mm-dd HH:mm:ss");
time = df.format(ts);
time = ts.toString();
System.ou.println("time="+time);  
```
Timestamp-->Date   
```java
//Date是Timestamp的父类,子类可直接向父类转换，父类不能直接向子类转换
Timestamp ts = new Timestamp(System.currentTimeMillis());   
Date date = new Date();  
date = ts;   
```
##### 数据类型所占字节：    
> 整型数所占字节：
>- int 4字节， short 2字节，long 8字节，byte 1字节。    

> 浮点数所占字节：  
>- float 4字节，double 8字节。            

### 七、字符串相关函数： 
> `char charAt(int index)` :  
> - 返回给定位置的代码单元   

> `int compareTo(String onestring)` :     
> - 按照字典顺序，若字符串位于onestring之前，返回负数，之后返回正数，相等，返回0。 
 
> `int indexOf / lastindexOf(String twostring)` : 
> - 返回与给定代码单元匹配的第一个/最后一个子串的位置。       

> `boolean startsWith(String prefix) / endWith(String suffix)` : 
> - 如果字符串以prefix开始/以suffix结尾，返回true,否则返回false。    

> `String trim()`  : 
> - 返回删除了原字符串头部和尾部空格的子字符串。    
   
> `StringBuilder append(String something)` : 
> - 在字符串后拼接给定字符串。  
     
> `void charAt(int x , char y)`: 
> - 将第x个代码单元设置为y。  

> `StringBuilder insert(int offset , String one/char two)` : 
> - 在offset位置插入一个字符串或代码单元。  

> `StringBuilder delete(int start , int end)` : 
> - 删除从start到end-1的字符串。     

### 八、Java输入输出流：  
(1) 字节流: 流中的数据是以8位字节为单位进行读写，以InputStream和OutputStream为基础类。

(2) 字符流: 流中的数据是以16位字符为单位进行读写，以Reader和Writer为基础类。

```java
// 输入流抽象类
InputStream(字节输入流)，Reader(字符输入流)
// 输出流抽象类
OutputStream(字节输出流)，Writer(字符输出流)  
```      

在java程序中，要想从数据源(文件，键盘，网络)中读取数据，需要在java程序和数据源之间建立一条数据输入的通道；要想在java程序中把数据写入数据源，需要在java程序和数据源之间建立一条数据输出的通道。即:输入流是从文件读取数据，是一个拉取数据的过程；输出流是将数据写入到文件，是一个推送数据的过程。 

![Alt-text](/images/javaio.png) 

### 九、Java集合
> java集合类存放于java.util包中，只能存放对象，存放的是对象的引用，对象本身还是放在堆内存中。         
> List，Set，Map将持有对象一律视为Object类型。               
> Collection:保存一个独立元素，是List和Set接口的父接口。List有序可以重复，Set无序不能重复。
>- Set:不允许重复元素，主要实现类是HashSet(无序)，但LinkedHashSet是有序的。             
>- List:可以重复元素，有序。       

> Map:保存一个键值对，不允许key重复，否则后者会覆盖前者，有三个实现类，是HashMap，HashLinkedMap，TreeMap。HashTbable也是Map的实现类。 
>- HashMap: 无序，不支持线程同步，即同一时间可以有多个线程写hashMap，键可以一个为null，值可以多个为null。        
>- LinkedHashMap: 有序，按插入顺序获取，是hashMap的子集。
>- TreeMap: 有序，默认按键值的升序，键和值都不能为null。        
>- HashTable: 无序，支持线程同步，键和值都不能为null。

```java
//第一种:效率高,多用此方式
　　Map map = new HashMap();
　　Iterator iter = map.entrySet().iterator();  //是遍历map的键值对
　　while (iter.hasNext()) {
　　Map.Entry entry = (Map.Entry) iter.next();  
　　Object key = entry.getKey();
　　Object val = entry.getValue();
　　}
//第二种:效率低,尽量少用
　　Map map = new HashMap();
　　Iterator iter = map.keySet().iterator();  //是遍历map的键
　　while (iter.hasNext()) {
　　Object key = iter.next();
　　Object val = map.get(key);
　　}
```

### 十、Java反射
1. java的反射机制是指在运行状态下，可以获得任意一个类的所有属性和方法，可以调用任意一个对象的所有属性和方法，甚至改变它的属性。        
2. 一个类在JVM中只会有一个Class实例。Class是用来描述类(属性、方法、构造器)的类。     
3. 获取Class对象的3种方式
>- 通过类名获取: 类名.class;      
>- 通过对象获取: 对象名.getClass();     
>- 通过全类名获取: Class.forName("全类名");     
4. 一般而言，一个类若声明了一个带参数的构造器，同时也要声明一个无参的构造器。前者用于初始化属性，后者用于反射。 



