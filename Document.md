**小知识点集锦**     
1. Windows系统基本操作:
```java
1. 一键锁屏：windows + L 
2. 显示桌面： windows + D
3. 命令提示符： windows + R 
4. 关掉页面: ctrl + W
5. 重新打开一个窗口： ctrl + N
6. 查找某个内容： ctrl + F      
```
2. Mac系统基本操作 
> **新建一个文件：**  
```java
1. $ `cd dir` 
2. `vim filename`  
3. 按 i 键，输入文件内容  
4. 按 `esc` 或 `：+ shift` 键，退出编辑  
5. $ `: wq`  ，保存文件,`!q` 为不保存   
```  

> **配置环境变量：** 
```java 
1. $ `vim ~/.bash_profile`  ，打开系统配置文件  
2. 输入`export PATH = $PATH:/文件路径`  
3. 保存之后，$`source ~/.bash_profile` ，即可生效    
```    

> **修改host文件：** 
```java
$ `vim /etc/hosts` ，//打开hosts文件  
//另一种方法
Finder --> Commond+Shift+G 快捷键 --> 输入/private/etc --> 即可看到hosts文件，但不能修改，可复制一份至其他地方，待修改后，再拖进去替换原来的。
```

> **启动可执行文件的命令**： 
>- $`sh file` 或 `./file` 

> **重新打开一个页面:** 
> - `commond + N`

> **在原来的页面里打开新的页面:**
>-  `commond + T`
  
> **查看本机ssh连接到的host:** 
> - `vi ~/.ssh/known_hosts`
  
> **Homebrew---mac的软件包管理工具:**
```java
//安装brew
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

brew install 软件名  
brew uninstall/remove 软件名
brew search 软件名
brew update
brew list   //brew列出已安装软件
brew info 软件名
```       

## 一、Markdown语法
> 关于字体：
>- *斜体字* 
>- **粗体字** 
>- <font face="黑体" color= "red" size=5>内容</font>

> 关于链接：
>- [百度一下](http:www.baidu.com)  
>- [我的照片](E:\Office\wodeqq.png)  

> 关于标题：
>- # 一级
一级标题  
======= 
二级标题  
-------
>- ## 二级

> 关于列表：
>- 1. 有序列表
>- 2. 有序列表
>- - 无序列表
>- - 无序列表

> 关于换行，分割：
>- 需要换行的地方，至少两个空格，再按回车； 单独一行里输入连续三个以上的“ - ”或者“ * ”或者“ _ ” ，就会产生水平分割线  
***  
> 关于代码和公式：
>- 行内代码： `int a =10;` 
>- 行内公式： $ y=sinx + a^2 $
>- 整行代码： $$ y=tanx $$
>- 代码区块： 
```java 
 public class Student{
    int name;
    int phone;
    System.out.println("hello");
}
```
> 关于表格：  

| Col1      |     Col2 |   Col3   |
| :-------- | --------:| :------: |
| field1    |   field2 |  field3  |   
|\$126      |   \￥106  |   apple |
 
## 二、Java语法 
### 日期相关函数：  
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
### 命令运行Java程序：  
1. 进入Java类所在目录  
2. 编译:  `javac 类名.java`
3. 运行:  `java 类名 输出结果`      
### 数据类型转换： 
`int intdate = Integer.parseInt(stringdata);`  

`String stringdata = String.valueOf(intdata);`

`String stringdata = JSONObject.toJSONString(jsonObject);`  

`JSONObject json = JSONObject.parseObject(stringdata);`   

`ClassDO classDO = JSON.parseObject(stringdata,ClassDO.class);`  

`ClassDO classDO = JSONObject.parseObject(String.valueOf(jsonObject),ClassDO.class);`       

`String stringdata = JSON.toJSONString(classDO);`   

`List<ClassDo> list = JSONArray.parseArray(jsonArray.toJSONString());`  
 
### 时间类型转换:
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
### 数据类型所占字节：    
> 整型数所占字节：
>- int 4字节， short 2字节，long 8字节，byte 1字节。    

> 浮点数所占字节：  
>- float 4字节，double 8字节。            

### 字符串相关函数： 
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

### Java输入输出流：  
```java
Scanner in = new Scanner(System.in);   
T content = in.nextLine() / in.next() / in.nextInt() / in.nextDouble();     
//读取可能包含空格的一行内容/读取一个单词/读取一个整数/读取一个双精度浮点数  
```    
电脑上的数据有三种存储方法：外存，内存，缓存。存储量：外存>内存>缓存；数据读取速度：缓存>内存>外存。     
将数据从外存读取到内存的称为输入流，将数据从内存写入外存的称为输出流。   
**输入流抽象类:**   
> InputStream(字节输入流)，Reader(字符输入流)   

**输出流抽象类：**    
> OutputStream(字节输出流)，Writer(字符输出流)

**标注于类上，字段的值为空时，不参与序列化，不在返回结果中显示：`@JsonInclude(JsonInclude.Include.NON_NULL)`。**    
**标注于类上，字段的值为空时，参与序列化，仍在返回结果中显示：`@JsonInclude(JsonInclude.Include.ALWAYS)`。**   
  
#### 关于"==",equals()和hashCode
> "=="是判断两个变量或实例是不是指向同一个内存空间, "equals()"是判断两个变量或实例所指向的内存空间的值是不是相同
> "=="是指对内存地址进行比较。 "equals()"是对字符串的内容进行比较
> "equals()"在Object中与"=="是一样的，Object的子类一般需要重写该方法。即：Object的equal方法默认是两个对象的引用的比较，如果需利用对象里面的值来判断是否相等，就必须重载equals()方法

```java
public class Common {
     public static void main(String [] args) {
        String a = "123";
        String b = "123";
        String c = new String("123");
        String d = new String("123");
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(a.equals(b));
        System.out.println(c.equals(d));
        System.out.println(42 == 42.0);

        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1 == obj2);
     }
}
输出结果依次为:true,false,true,true,true,false,false
```
```java
public class Common {
    private int i;
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    //自定义重写hashCode方法
    @Override
    public int hashCode() {
        return i % 8;
    }
    //重写equals方法
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Common)) {
            return false;
        }
        Common common = (Common) obj;
        if (this.getI() == common.getI()) {
            return true;
        }
        return false;
    }
    public static void main(String [] args) {
        Common x = new Common();
        Common y = new Common();
        x.setI(1);
        y.setI(1);
        Set<Common> set = new HashSet<>();
        set.add(x);
        set.add(y);
        System.out.println(x.hashCode() == y.hashCode());
        System.out.println(x.equals(y));
        System.out.println(set);
    }
}
输出结果依次为：true,true,[com.example.demo.utils.Common@1]
```

### Java访问HTTP网页:  
```java
public class TestUrl {
        public static void main(String [] args) throws URISyntaxException, IOException {
            java.net.URI uri = new java.net.URI("http://www.baidu.com");
            java.awt.Desktop.getDesktop().browse(uri);
    }
}
//利用OKHttp3访问链接
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
Request reqTwo = new Request.Builder().url("your url").build();
Response respTwo = client.newCall(reqTwo).execute();
JSONObject jsonObject = JSONObject.parseObject(respTwo.body().string());
```

### JavaScript parseInt(string, radix)函数    
string是要解析的字符串，radix是要解析的数字的基数，介于2-36之间。      
> 当radix小于2或大于36时，返回NaN；       
> 当radix介于2-36之间时，将string解析为radix进制的整数返回;(会将string看成是一个数的radix进制数，返回结果是10进制的数,如果第一个参数遇到超出进制时，直接从此处截断，后面不再解析)。  
> 当radix为0或者省略时，parseInt根据string来判断数字的基数：  
>- 若string以“0x/0X”开头，将string的其余部分解析为16进制数返回； 
>- 若string以“0”开头，将string的其余部分解析为10进制数返回；  
>- 若string以1-9开头，直接将其解析为10进制数返回。

> 当string是一组数字数组时，只有第一组数字被返回。
```java
<html>
<head>
  <meta charset="utf-8">
  <title>Title</title>
</head>
<body>
<script>
  document.write(parseInt("10") + "<br>") ;  //直接当作10进制的10
  document.write(parseInt("10.33") + "<br>");
  document.write(parseInt("34 45 66") + "<br>");
  document.write(parseInt(" 60 ") + "<br>");
  document.write(parseInt("40 years") + "<br>"); //后面的year不是10进制范围的数，所以直接截断
  document.write(parseInt("He was 40") + "<br>");
  document.write("<br>");
  document.write(parseInt("19",2)+ "<br>");  //当作是2进制的19，由于9超出2进制范围，所以直接截断，只解析1
  document.write(parseInt("0xF", 16)+ "<br>");   //因为0x是16进制数的开头标志（当然该标志也可以省略），所以从F开始解析，当作是16进制的F, 结果为10进制的15
  document.write(parseInt("0xF", 8)+ "<br>");   //返回0，因为8进制并不会用0x开头，所以从0开始解析
  document.write(parseInt("123",5)+ "<br>");
  document.write(parseInt("0x10")+ "<br>");
  document.write(parseInt("10",16)+ "<br>");
</script>
</body>
</html>
结果依次为:10 10 34 60 40 NaN    1 15 0 38 16 16
```
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
 
## 三、springmvc与resteasy区别比较  
### 依赖注入：
> (1) 使用@Autowired注解，应用处格式为：首字母小写的bean类.方法名,方法不必静态化  
> (2) 不用@Autowired注解，应用处格式为：首字母大写的bean类.方法名，方法必须静态化 

### 注解区别：    
> (1) Controller层：    
>- spring中使用@Controller注解类上，使用@RequestMapping(value=“url,metdod=RequestMethod=GET/POST)注解方法上， 使用@RequestParam和@PathVariable注解参数，使用@RequestBody 注解body部; resteasy中使用@Service注解类上，使用@GET/@POST, @Path(value="url")注解方法上，使用@Context注解request对象。  

> (2) Service层：     
>-  spring中使用@Service注解类上; resteasy中可以使用@Service注解在类上，也可使用通用的@Component注解在类上。  

> (3) Dao层： 
>- Spring中使用通用的@Component注解在类上，然后在Mapper层使用@Mapper注解在类上;resteasy中使用@MybatisRepository注解在类上。     
>- spring中dao层与mapper.xml文件的参数传递： 
```java 
dao: void func(String param1,Integer param2); 
mapper: select * from table where pam1 = #{0} and pam2 = #{1} 
dao: void func(@Param(pam1)String param1,@Param("pam2")Integer param2)
mapper: select * from table where pam1 = #{pam1} and pam2 = #{pam2} 
```

### springmvc处理请求流程：
```java
DispatcherServelt(前端控制器)------>HandlerMapping(映射器处理器) ；      
DispatcherServelt(前端控制器)------>HandlerAdapter(处理器适配器) ；     
HandleAdapter(处理器适配器)------>Handler(处理器) ；
DispatcherServelt(前端控制器)------>ViewResolver(视图解析器) ；  
View(视图)------>DispatcherServelt(前端控制器) 。  
```   

## 四、Maven常用语句 
 mvn clean install -Dmaven.test.skip=true   
 mvn clean compile  
 mvn clean test  
 mvn clean package  
 mvn clean install  
### 使用maven命令构建Java项目：  
```java
 mvn archetype:generate/create -DgroupId=packageName -DartifactId=projectName -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
 ```
 **pom中添加插件`spriong-boot-maven-plugin`的两个好处**：   
 > 打包项目为可执行jar包，即可运行`java -jar xxx.jar`启动项目;    
 > 运行`spring-boot:run` 直接启动项目。      

### maven构建多模块项目:
 1. 构建system-parent项目，删除生成的src文件夹，修改pom.xml1`<packaging>jar</packaging>`为`<packaging>pom</packaging>` ,pom表示其为一个被继承的模块 ；  
 2. 进入system-parent项目 , 构建system-domain项目，system-parent的pom文件中新增`<module>system-domain</module>` , 删除其pom文件的`<groupId>me.gacl</groupId>`和`<version>1.0-SNAPSHOT</version>` （因为会继承父母模块的）， 并加上`<packaging>jar</packaging>` ；
 3. 进入system-parent项目 , 构建system-dao项目，system-parent的pom文件中新增`<module>system-dao</module>` , 删除其pom文件的`<groupId>me.gacl</groupId>`和`<version>1.0-SNAPSHOT</version>` （因为会继承父母模块的）， 再加上`<packaging>jar</packaging>` , 并添加对system-domain的依赖   
 `<dependency>`  
`<groupId>me.gacl</groupId>`  
` <artifactId>system-domain</artifactId>`  
 `<version>${project.version}</version>`   
`</dependency> `  
4.  进入system-parent项目 , 构建system-service项目，system-parent的pom文件中新增`<module>system-service</module>` , 删除其pom文件的`<groupId>me.gacl</groupId>`和`<version>1.0-SNAPSHOT</version>` （因为会继承父母模块的）， 再加上`<packaging>jar</packaging>` , 并添加对system-dao的依赖   
 `<dependency>`  
`<groupId>me.gacl</groupId>`  
` <artifactId>system-daon</artifactId>`  
 `<version>${project.version}</version>`   
`</dependency> `    
5. 进入system-parent项目 , 构建system-web项目，system-parent的pom文件中新增`<module>system-web</module>` , 删除其pom文件的`<groupId>me.gacl</groupId>`和`<version>1.0-SNAPSHOT</version>` （因为会继承父母模块的）， 再加上`<packaging>war</packaging>` , 并添加对system-dservice的依赖   
 `<dependency>`  
`<groupId>me.gacl</groupId>`  
` <artifactId>system-service</artifactId>`  
 `<version>${project.version}</version>`   
`</dependency> `   

### maven引入第三方依赖
1. 在项目的根目录下新建文件夹libs,将外部jar包放进去
2. pom.xml文件中这样引用
```java
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-eureka-server</artifactId>
	<version>1.4.4.RELEASE</version>
	<scope>system</scope>
	<systemPath>${basedir}/libs/spring-cloud-starter-eureka-server-1.4.4.RELEASE.jar</systemPath>
</dependency>
```
### 将外部jar包加入本地maven仓库
1. 进入jar所在路径
2. 执行以下内容
```java
mvn install:install-file -Dfile=<完整的文件名，eg:spring-cloud-dependencies-Finchley.M8.pom> -DgroupId=org.springframework.cloud -DartifactId=spring-cloud-dependencies -Dversion=Edgware.SR3 -Dpackaging=pom

mvn install:install-file -Dfile=<完整的文件名> -DgroupId=org.springframework.cloud -DartifactId=spring-cloud-dependencies -Dversion=Edgware.SR3 -Dpackaging=jar
```

## 五、单元测试Junit
> (1) 测试方法用@Test注解，public void 修饰        
> (2) @BeforeClass和@AfterClass只执行一次，在所有方法都被调用之前/后            
> (3) @Before和@After在每个方法被调用前/后执行          
 
## 六、linux常用命令  
> ls ;    ls -l ;   ls -s -S ;   ls -a ;  
>- 显示所有目录和文件 

> cd ..      
>- 返回上层目录

> mkdir DIRECTORY     
>- 创建目录

> rmdir DIRECTORY        
>- 删除空目录

> rm -r DIR       
>- 删除目录

> rm FILE    
>- 删除文件

> rm *    
>- 删除当前目录所有文件

> cp FILE1 FILE2     
>- 将文件1复制成文件2 

> cp FILE /DIR1/DIR2    
>- 将文件复制到指定目录

> cp -V -R * DIR      
>- 将当前目录下所有文件复制到指定目录中

> echo 'content' > FILE    
>- 创建指定内容的指定文件

> grep --color = auto 'content' /DIR/FILE     
>- 突出显示指定文件中的指定内容

> more/cat FILE       
>- 显示文件内容  

> cat FILE1 >> FILE2       
>- 将文件1内容附加到文件2内容之后   

> mv FILE ..          
>- 将文件移动到上层目录

> mv FILE1 FILE2     
>- 将文件1重命名为文件2

> services.msc        
>- 进入本地服务界面

> date    
> cal      
> bc  
>- 显示日期，日历，计算器(回车后输入要计算的表达式再回车即显示结果)

> cat / tac FILE  
>- 从第一行/最后一行 查看文件内容 

> touch FILE   
>- 新建一个文档

>file FILE    
>- 查看文件类型 

> cls, command+K
>- 清屏(windows，mac) 

> command+D，command+T
>- 页面分屏， 新打开一个页面

> history | grep "xxx"  
>- 查询指定的输入历史

> cd ~/data/log    
>- 绝对路径，进入根目录/data/log

> cd ./data/log    
>- 相对路径，进入当前目录的/data/log路径下

> lsof -i tcp:<端口>    
>- 查看指定端口占用的进程

> kill PID     
>- 杀死该标识所代表的进程

> CH-yfy@Yuans-iMac ~$ cd /data/    
>- pwd = /data, 即当前所在路径

> CH-yfy@Yuans-iMac ~$ cd data/    
>- pwd = CH-yfy@Yuans-iMac/data, 即当前所在路径

> ps -ef | grep <进程名> 
>- 查看某进程是否运行

> ifconfig
>- 查看激活网卡的详细信息,eth/en代表以太网网卡信息（ipv4,ipv6,broadcast,mask)

> ps -A / ps -e
>- 查看所有进程    
         
### Linux下文件权限设置
1. linux下，文件的权限有读(r)，写(w)，可执行(x)三种，文件访问的用户类别有创建者(owner),与创建者同组用户(groups)，其他用户(others)三种。   
2. 文件的权限由10个字符组成:         
第1个字符的含义为: "d" 表示目录, "-" 表示文件, 还有"l, "b", "c"。       
其余的9个字符，分为三组，第一组代表创建者的权限，第二组代表同组用户的权限，第三组代表其他用户的权限。每组三个字符，均为”rwx“三个参数的组合且顺序是固定的。如果没有某个权限，就用"-"代替。  
3. 文件权限的修改, 命令:chmod   
> (1) 用数字组合表示权限：`r=4, w=2, x=1`       
>- 文件的权限为”-rwxrwx-w-“时，owner=4+2+1=7, groups=4+2+1=7, others=0+2+0=2, 所以文件的权限数字就是772。 
```java
可执行命令:
chmod 772 mytest.sh
```     
> (2)用字母字符表示权限：     
> `owner=u, groups=g, others=o, all(全部用户)=a`, `+(添加权限)， -(减少权限)`      
>- 已知文件的权限为”-rwxrwxrwx"  
```java
//要删除所有用户的可执行权限时
chmod a-x mytest.sh
//要删除其他用户的写权限时
chmod o-w mytest.sh
```
4. 修改文件的拥有者(只有root用户才有权限，因此有时需要加上sudo)
chown <账号名称> <文件/目录>
```java
//修改前
drwxr-xr-x   2 root  wheel    68  4 10 14:20 data
执行 CH-yfy@YuanFayangs-iMac opt$sudo chown CH-yfy data/  
//修改后
drwxr-xr-x   2 CH-yfy  wheel    68  4 10 14:20 data
```        
## 七、Tomcat虚拟目录映射方式
web应用开发好之后，若想供外界访问，需要把web应用所在目录交给web服务器管理，这个过程称之为虚拟目录的映射。   
1. 在server.xml文件的host元素中配置：  
> (1)`<Host></Host>`这对标签中添加`<Context path = "/demoapps" docBase = "F:\DemoProject"/>`即可将F盘的DemoProject应用映射到demoapps这个虚拟目录上，它是磁盘上不存在的目录，是我们自己定义的。  
>- path必须以"/"开头； docBase对应JavaWeb项目的所在目录。      

> (2)浏览器访问：`http://localhost:8080/demoapps/demo.jsp` 。 

2. tomcat服务器自动映射：
> tomcat服务器会自动管理webapps目录下的所有web应用，并把它映射成虚拟目录，所以直接将F盘下的DemoProject应用放在webapps文件夹下。  
> 浏览器访问： `http://localhost:8080/DemoProject/demo.jsp` 。  

3. 添加xml文件：  
> 在conf/Catalina/localhost目录下添加一个以xml为扩展名的demo.xml文件，文件中添加`<Context docBase = "F:\DemoProject"/>`。  
> 浏览器访问： `http://localhost:8080/demo/demo.jsp`,这里的虚拟目录就是文件名。  

4. 查看tomcat日志: tail -f catalina.out
5. 查看tomcat版本信息: cd Documents/tools/tomcat/bin 运行`./version.sh`

## 八、前后端分离跨域问题解决
1. 过滤器filter: 
```java
public void doFilter(ServletRequest req, ServletResponse res,  FilterChain chain) throws IOException, ServletException {  
    HttpServletResponse response = (HttpServletResponse) res;  
    response.setHeader("Access-Control-Allow-Origin", "*");  
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
    response.setHeader("Access-Control-Max-Age", "3600");  
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
    chain.doFilter(req, res);  
}  
```
2. 拦截器interceptor:
```java
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //添加跨域CORS
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
    response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
    return true;
  }
```

## 九、关于jar包
1. 打成jar包/解压jar包:
```java
mvn clean install  //即根据pom文件，打成jar或war包

E:\myproject\> jar cvf project.war */ .  //命令行进入该项目的文件夹执行
 
E:\> jar -cvf myproject.war myproject   //进入该项目的父文件夹执行

jar -xvf myjar-4.0.1-SNAPSHOT.jar  //解压jar包
```
2. 创建jar文件：
```java
jar cf test.jar testDir
//当前路径下的testDir目录下生成一个test.jar文件
```
3. 创建jar文件，并显示圧缩过程：
```java
jar cvf test.jar testDir
```
4. 不使用清单文件( MEAT-INF/MANIFEST.MF):
```java
jar cvfM test.jar testDir
```
5. 查看jar包内容：
```java
jar tf test.jar
```
6. 查看jar包详细内容:
```java
jar tvf test.jar
```
7. 解压缩jar包:
```java
jar xf test.jar
//带提示信息的解压缩
jar xvf test.jar
```
## 十.关于重定向和转发
请求转发是服务器行为，浏览器只做了一次访问请求 
请求重定向是客户端行为，浏览器做了至少两次的访问请求    
1. 转发过程：
> 客户浏览器发送http请求
> web服务器接受此请求
> 调用内部的一个方法在容器内完成请求的处理和转发动作
> 将目标资源返回给客户。 转发路径必须是同一个web服务器下的url,浏览器路径栏显示的是首次访问的地址，客户感觉不到服务器做了抓转发。

2. 重定向过程:
> 客户浏览器发送http请求
> web服务器接受后发送302响应码和新的location给客户浏览器
> 客户浏览器根据新的location，再自动发送一个新的http请求
> 服务器根据此请求url寻找资源并返回给客户。客户浏览器路径栏显示的是重定向的地址，用户可以观察到地址的变化。