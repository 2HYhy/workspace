### 一. Java进程与线程
*转载自http://www.cnblogs.com/skywang12345/p/3479202.html*

#### 进程
> 一个程序进入内存运行，便成为一个进程。拥有自己独立的系统资源和地址空间，动态产生和消亡。

#### 线程
> 是进程中的一个实体，不拥有系统资源，但可与同属一个进程的其他线程共享进程的所有资源。
> java中主线程main是由Java虚拟机创建的，程序一启动就会自动运行。

#### 用户线程和守护线程
1. Thread.isDaemon(fale)是用户线程，Thread.isDaemon(true)是守护线程    
2. 默认是用户线程
3. Java虚拟机一旦启动，就会一直运行，直到:
> exit()方法被调用执行   
> 只有搜狐户线程在运行  

#### 线程的5种状态
1. 新建状态:
> 线程对象被新建后，就进入了新建状态，此时由java虚拟机分配了内存         

2. 就绪状态:
> 线程对象被调用了start()方法后，就处于就绪状态，也叫可执行状态，随时可以被CPU调度执行

3. 运行状态:
> 线程获取CPU权限进行执行，线程只能从就绪状态进入运行状态

4. 阻塞状态:
> 线程因为某种原因放弃CPU使用权，暂时停止运行。阻塞状态分为3种:
>- 等待阻塞：调用wait方法，让线程等待某工作的完成    
>- 同步阻塞：线程在调用synchronized获取同步锁时失败，因为锁被其他线程占用      
>- 其他阻塞：调用了线程的sleep(),join()或发出了I/O请求

5. 死亡状态:
> 线程执行完了或异常退出run方法，结束了生命周期

#### 实现多线程的两种方式
1. 继承Thread类(各子线程彼此独立) 
2. 实现Runnable接口(各子线程资源共享)

#### start()和run()区别
1. start()用于启动一个新线程，会调用响应的run方法，不能重复被调用       
2. run()和普通成员方法一样，可以重复被调用，当单独调用时，会在当前线程中执行，不会启动新线程   

#### synchronized关键字
1. 原理
> java中，每一个对象有且仅有一个同步锁，即同步锁是依赖于对象而存在。    
> 不同线程对同步锁的访问是互斥的。即同一时间点，对象的同步锁只能由一个线程获取到。     
> synchronized(this)指当前所在类的对象的同步锁，synchronized(obj)指“obj这个对象”的同步锁。

2. 基本规则
> 当一个线程访问“某对象”的“synchronized方法”或"synchronized代码块"时，其他线程对"该对象"的该"synchronized方法"或"synchronized代码块"的访问将被阻塞      
> 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问"该对象"的非同步代码块      
> 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对"该对象"的其他的"synchronized方法"或"synchronized代码块"的访问将被阻塞

3. “synchronized方法”是用synchronized修饰方法，而 “synchronized代码块”则是用synchronized修饰代码块。  
4. 实例锁和全局锁
> 实例锁指的锁在一个实例对象上，对应的就是synchronized关键字       
> 全局锁指的锁在一个类上，对应的就是static synchronized关键字
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

#### 等待与唤醒
*当前线程”是指正在cpu上运行的线程* 

1. wait()
> 让当前线程处于等待(阻塞)状态，直到其他线程调用此对象的notify()或notifyAll()方法，方可被唤醒进入就绪状态

2. wait(long timeout)
> 让当前线程处于等待(阻塞)状态，直到其他线程调用此对象的notify()或 notifyAll()，或者超过指定的时间量”，方可被唤醒(进入“就绪状态”)

3. notify()
> 唤醒在此对象监视器上等待的单个线程

4. notifyAll()
> 唤醒在此对象监视器上等待的所有线程

5. 
> wait()等待线程和notify()之间正是通过对象的同步锁关联起来的。而同步锁是对象持有的，这就是notify(), wait()等函数定义在Object类，而不是Thread类中的原因。

#### yield让步
> 使当前线程由运行状态变成就绪状态，从而让其他具有相同优先级的线程获取执行权     
> 但不能保证当前线程调用yield后，其他同优先级线程就一定能获得执行权，也可能是当前线程又进入运行状态      
> 不会释放同步锁
> 定义在Thread中      

#### sleep休眠
> 让当前线程由运行状态进入休眠阻塞状态，不会释放同步锁
> 定义在Thread中  

#### Join()
> 让主线程等待子线程结束之后才能继续运行
> 定义在Thread中

#### interrupt中断终止线程
> interrupt()除了返回中断标记外，还会清除中断标记   
> isInterrupted()只是返回中断标记  


### 二、Java静态变量、方法、类

####静态变量和方法
1. 静态方法可以直接调用同类的静态变量，不能直接调用同类的非静态变量    
2. 静态方法调用同类的非静态变量，要先创建对象，再通过对象调用同类的非静态变量   
3. 普通成员方法可以直接调用同类的静态和非静态成员    
4. 静态方法不能直接调用非静态方法，需要通过对象调用   

#### 静态类
1. 一个类要被声明为static，只能是静态内部类   
2. 静态内部类只能访问外部类的静态成员变量和方法，不能访问非静态成员变量和方法   
3. 普通内部类可以方法外部类的任意成员变量和方法   
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

### 三、Java抽象类和方法，多态与重载和重写
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

### 四、Java访问权限修饰符
| 范围   | public | protected | default | private |
|--------|--------|-----------|--------|---------|
| 同一个类 |  yes    |   yes  |  yes  |    yes    |
| 同一个包 |   yes   |   yes   |   yes  |    no   |
| 子父类   |   yes   |   yes   |   no   |  no     |
| 不同包   |  yes    |   no    |  no    |   no    |

### 五、Java的equals()和hashCode()方法
#### equals()
定义在JDK的Object.java中，作用是判断两个对象是否相等。
使用默认的equals()方法，等价于“==”方法，实际上比较的是两个对象的地址是否相等
通常都会重写equals()方法，用来比较两个对象的内容是否相同。

#### hashCode()
定义在JDK的Object.java中，作用是获取对象的散列码。只有在散列表HashMap，HashSet，HashTable中才有用。
HashSet实现了Set接口，它不允许集合中出现重复元素,存储的是对象，使用add()添加元素。
HashMap实现了Map接口，它不允许出现重复的键(key),存储的是键值对，使用put()添加元素。
HashMap时无序的，TreeMap是按自然顺序或自定义顺序的，LinkedHashMap是输出顺序和输入顺序相同的。

#### equals()和hashCode()
1. 类中不会用到HashSet, HashTable, HashMap等这些本质是散列表的数据结构：
equals()用来比较该类的两个对象是否相等。而hashCode()没有任何作用，可以忽略。
2. 类中有用到了HashSet, HashTable, HashMap等这些本质是散列表的数据结构：
如果两个对象相等(即equals比较两个对象时，返回true)，那么它们的hashCode一定相等；如果两个对象的hashCode相等，它们并不一定相等。
3.总而言之:当需要对比时，首先用hashCode()比，如果hashCode()不一样，那这两个对象肯定不相等(也就是不必再用equal()比了),如果hashCode()相同，再用equal()比，如果equal()也相同，那这两个对象就真的相同了。


### Java中HashMap遍历的两种方式:
```java
第一种:
　　Map map = new HashMap();
　　Iterator iter = map.entrySet().iterator();
　　while (iter.hasNext()) {
　　Map.Entry entry = (Map.Entry) iter.next();
　　Object key = entry.getKey();
　　Object val = entry.getValue();
　　}
　　效率高,以后一定要使用此种方式！
第二种:
　　Map map = new HashMap();
　　Iterator iter = map.keySet().iterator();
　　while (iter.hasNext()) {
　　Object key = iter.next();
　　Object val = map.get(key);
　　}
　　效率低,以后尽量少使用！
```


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
3. 运行:  `java 类名 输出结果`      

##### 数据类型转换： 
`int intdate = Integer.parseInt(stringdata);`  

`String stringdata = String.valueOf(intdata);`

`String stringdata = JSONObject.toJSONString(jsonObject);`  

`JSONObject json = JSONObject.parseObject(stringdata);`   

`ClassDO classDO = JSON.parseObject(stringdata,ClassDO.class);`  

`ClassDO classDO = JSONObject.parseObject(String.valueOf(jsonObject),ClassDO.class);`       

`String stringdata = JSON.toJSONString(classDO);`   

`List<ClassDo> list = JSONArray.parseArray(jsonArray.toJSONString());`  

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

### 六、字符串相关函数： 
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

##### Java输入输出流：  
```java
Scanner in = new Scanner(System.in);   
T content = in.nextLine() / in.next() / in.nextInt() / in.nextDouble();     
//读取可能包含空格的一行内容/读取一个单词/读取一个整数/读取一个双精度浮点数 

// 输入流抽象类
InputStream(字节输入流)，Reader(字符输入流)
// 输出流抽象类
OutputStream(字节输出流)，Writer(字符输出流)  
``` 