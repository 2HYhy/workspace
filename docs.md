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
 > `String time = TimeHandle.getNowTimeStamp();` 获取当前毫秒级时间   
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
2. 运行javac 编译  :  javac 类名.java
3. 运行 java 类名 输出结果       
### 数据类型转换： 
`int intdate = Integer.parseInt(stringdata);`  
`String stringdata = String.valueOf(intdata);` 
`String stringdata = JSONObject.toJSONString(jsonObject);`  
`JSONObject json = JSONObject.parseObject(stringdata);`
`ClassDO classDO = JSON.parseObject(stringdata,ClassDO.class);`  
`ClassDO classDO = JSONObject.parseObject(String.valueOf(jsonObject),ClassDO.class);`    
`String stringdata = JSON.toJSONString(classDO);`
`List<ClassDo> list = JSONArray.parseArray(jsonArray.toJSONString());`    
### 数据类型所占字节：    
> 整型数所占字节：
>- int 4字节， short 2字节，long 8字节，byte 1字节。    

> 浮点数所占字节：  
>- float 4字节，double 8字节。         
### 子类与超类：  
> 子类（派生类）要调用并重载超类（基类）的方法，正确的格式 ：   
```java    
public int getSalary(){
    int s1 = super.getSalary();
    return s1 + s2;
}   
```      
> 一个基类变量可以既可引用本类的对象，也可以引用它任何一个派生类的对象：  
```java  
public class Employee{};  
public class Manager extends Employee{};  
Employee e = new Manager();  
```      
### 散列码hashCode：    
> String类计算散列码的方法 ：  
```java  
String data = "  " ;
int hash = 0 ;  
for (int i = 0;i < data.length() ; i++) {
    hash = 31 * hash + chharAt(i) ;
}   
```      
### toString()的逆方法是valueOf()： 
```java 
Enum Size {
    SMALL, MIDDLE,BIG
}
String string = Size.SMALL.toString();
Size s = Enum.valueOf(Size.class,string);  
```   
### 字符串函数： 
> `char charAt(int index)` : 返回给定位置的代码单元   
> `int compareTo(String onestring)` :  按照字典顺序，若字符串位于onestring之前，返回负数，之后返回正数，相等，返回0。  
> `int indexOf / lastindexOf(String twostring)` : 返回与给定代码单元匹配的第一个/最后一个子串的位置。  
> `boolean startsWith(String prefix) / endWith(String suffix)` : 如果字符串以prefix开始/以suffix结尾，返回true,否则返回false。  
> `String trim()`  : 返回删除了原字符串头部和尾部空格的子字符串。    
### 可变长字符串函数： 
> `StringBuilder append(String something)` : 在字符串后拼接给定字符串。  
> `void charAt(int x , char y)`: 将第x个代码单元设置为y。  
> `StringBuilder insert(int offset , String one/char two)` : 在offset位置插入一个字符串或代码单元。  
> `StringBuilder delete(int start , int end)` : 删除从start到end-1的字符串。  

### java输入流：  
```java
Scanner in = new Scanner(System.in);   
T content = in.nextLine() / in.next() / in.nextInt() / in.nextDouble();     
// 读取可能包含空格的一行内容/读取一个单词/读取一个整数/读取一个双精度浮点数  
```     
电脑上的数据有三种存储方法：外存，内存，缓存。存储量：外存>内存>缓存；数据读取速度：缓存>内存>外存。  
一个字符是16位，占用两个字节。   
将数据从外存读取到内存的成为输入流，将数据从内存写入外存的称为输出流。   
**输入流抽象类:**   
> InputStream(字节输入流)，Reader(字符输入流)   

**输出流抽象类：**    
> OutputStream(字节输出流)，Writer(字符输出流)

### Compareable<T>接口中的方法：     
 `int compareTo(T other);`  将当前对象与other对象进行比较，若小于，返回负数，若大于，返回正数，若等于，返回0。  
### 当内部类不需要访问外围类对象时，应该使用静态内部类。    
### 标注于类上，字段的值为空时，不参与序列化，不在返回结果中显示：`@JsonInclude(JsonInclude.Include.NON_NULL)`。    
### 标注于类上，字段的值为空时，参与序列化，仍在返回结果中显示：`@JsonInclude(JsonInclude.Include.ALWAYS)`。
 
## 三、spring与resteasy  
### 依赖注入：
> (1) 使用@Autowired注解，应用处格式为：首字母小写的bean类.方法名,方法不必静态化  
> (2) 不用@Autowired注解，应用处格式为：首字母大写的bean类.方法名，方法必须静态化 
### 注解区别：    
> (1) Controller层：spring中使用@Controller注解类上 ，使用@RequestMapping（value=“url,metdod=RequestMethod=GET/POST）注解方法上 ， 使用@RequestParam和@PathVariable注解参数 ， 使用@RequestBody 注解body部分 ；resteasy中使用@Service注解类上 ， 使用@GET/@POST , @Path(value="url")注解方法上 ， 使用@Context注解request对象。  
> (2) Service层： spring中使用@Service注解类上 ； resteasy中可以使用@Service注解在类上，也可使用通用的@Component注解在类上。  
> (3) Dao层：Spring中使用通用的@Component注解在类上 ，然后在Mapper层使用@Mapper注解在类上 ；  resteasy中使用@MybatisRepository注解在类上。    
> ### spring中dao层与mapper.xml文件的参数传递：  
dao: `void func(String param1,Integer param2);`  
mapper: `select * from table where pam1 = #{0} and pam2 = #{1}`  
dao: `void func(@Param(pam1)String param1,@Param("pam2")Integer param2);`  
mapper: `select * from table where pam1 = #{pam1} and pam2 = #{pam2}`  
### springmvc处理请求流程：
```java
DispatcherServelt(前端控制器)------>HandlerMapping(映射器处理器) ；      
DispatcherServelt(前端控制器)------>HandlerAdapter(处理器适配器) ；     
HandleAdapter(处理器适配器)------>Handler(处理器) ；
DispatcherServelt(前端控制器)------>ViewResolver(视图解析器) ；  
View(视图)------>DispatcherServelt(前端控制器) 。  
```   

### java访问网页  
```java
public class TestUrl {
        public static void main(String [] args) throws URISyntaxException, IOException {
            java.net.URI uri = new java.net.URI("http://www.baidu.com");
            java.awt.Desktop.getDesktop().browse(uri);
    }
}
```

## 四、Git命令
> 设置用户名、邮箱：  
>- git config --global user.name "Your Name"  
>- git config --global user.email "Your email"    

> 初始化一个目录为本地仓库：  
>- git init  

> 提交文件： 
>- git add FileName   
>- git commit - m "xxx"  

> 查看状态:
>- git status    

> 查看文件内容，删除文件：  
>- cat File  
>- rm File  

> 查看文件做了哪些修改：
>- git diff File    

> 查看提交历史，确定回退版本：  
>- git log 或者 git log -- pretty=oneline  

> 回退版本：  
>- git reset --hard HEAD         回到上一个版本  
>- git reset --hard commintId    回到任一个版本  

> 查看命令历史，回到未来：  
>- git reflog  

> 撤销工作区修改：  
>- git checkout -- File   

> 撤销暂存区修改：
>- git reset HEAD File  
>- git checkout -- File   

>删除版本库文件：
>- git rm File 
>- git commit -m "xxx"  

> 本地关联远程库：
>- git remote add origins git@github.com : 用户名/远程库名.git  

> 本地推送至远程库：
>- git push (-u) origin master  

> 从远程库克隆：
>- git clone git@github.com : 用户名/远程库名.git 

> 创建并切换分支:
>- git checkout -b Branch  

> 创建分支：
>- git branch Branch 

> 切换分支：
 >- git checkout Branch  

 > 合并分支到当前分支：
 >- git merge Branch  

 > 删除分支：
 >- git branch -d Branch 

 ## 五、MySql基本语句  
 1. select column from table where volumn = value
 2. select volumn from table where volumn1 = value1 and(or) volumn2= value2
 3. select volumn from table where volumn in List
 4. select volumn from table where volumn between value1 and value2
 5. insert into table (volumn) values (value)
 ```java
 //一次性插入多条记录
insert into user_login_log_test(uid，logintime) values 
(value11, value12),
(value21, value22);
```   
 6. update table set volumn = value where volumn = value
 7. delete from table where volumn = value
 8. create database / drop database
 9. 关于日期的函数：  
> `DATE(xx)` 提取时间xx的日期部分  
> `DATE_ADD(xx,INTERVAL num type)`  日期xx增加num个单位  
> `DATE_SUB(xx,INTERVAL num type)`  日期xx减少num个单位    
10. limit函数：  
` select * from table limit m , n `   数据库中的记录下标是从0开始的，即从记录的第 m+1 条开始，共选择 n 条数据。    
` select * from table limit x ` 取出前x条记录     
11. `alter table 表名 add  列名 数据类型`   在表中添加列  
12. `alter table 列名 change 列名 新列名 数据类型`  修改表中的列      
13. `truncate table xxxxxx`    清除某个表的全部数据 
```java
//查看event是否开启:
 show variables like 'event_scheduler'; 
//将事件计划开启: 
set global event_scheduler=1; 
```      
  
14. 安装命令行连接mysql：     
> 进入mysql/bin目录路径下：    
> MySQL  -hlocalhost -uroot -proot， 分别为本地连接的地址，用户名，密码  
> exit;  退出数据库 
> net start mysql; 启动数据库
> net stop mysql;  关闭数据库 

15. 关于几大数据类型：   
#### unsigned属性，特殊应用场景：存放ip  
> INET_ATON()函数：将IP转换为数值类型;   
> INET_NTOA()函数：将数值类型转换为IP。   
> - IPV4的最大地址(255.255.255.255)转换后即为unsigned int的最大值(4294967295), 而int的范围为-2147483648-21474833647, 所以要用unsigned int 存储IP地址。  

#### DECIMAL(M,D)
> 其中，M代表正数和小数部分的总位数，D代表小数部分的位数。  

#### TIMESTAMP和DATETIME 
> 显示结果一样，都是固定的“YYYY-mm-dd hh:MM:ss”的形式。

```java
 insert into table(t1 timestamp,t2 datetime) values(now(),now()); 
 select * from table; 
t1="2017-10-06 16:12:56" ,  t2="2017-10-06 16:12:56"
 ```

 >timestamp时间范围为"1970-01-01 00:00:01" UTC 到"2038-01-19 03:14:07" UTC , UTC是协调世界时，就如我们中国的时区是UTC+8，显示的时间范围要加上8小时。    
> 保存一个timestamp类型的时间时，它的值以UTC格式存放，即就是从"1970-01-01 00:00:01"到当前的秒数。  

#### char(M)和varchar(M)  
> M指的是字符数；  

> 常规的laint1字符集，1个字符=1个字节，char存储最大占用255个字节； 国内使用的UTF-8字符集，1个字符=3个字节，char存储最大占用255*3=765个字节。

> char用于保存固定长度的字符串，最大char(255),保存比指定长度大的值将被截断，而比指定长度小的值将会用空格进行填补。保存时，数据库自动填充，读取时，数据库自动删除； 

> varchar用于保存可变长字符串，最大varchar(21844)存储长度是字符串的长度+一个记录字符串本身长度的字节，字符串长度<=255时，算个字节，字符串长度>255时，算2个字节。

>
 | 字符串  | char(4) | 存储需求 | varchar(4) |存储需求 |
 |:-------| --------:| :------: |:---------|:------|
 | " | '    ' | 12个字节 | '' | 1个字节 |  
 |'ab'   | 'ab  ' | 12个字节 | 'ab' | 7个字节 |
 | 'abcd' | 'abcd' | 12个字节 | 'abcd' | 13个字节 |
 | 'abcdefg' | 'abcd' | 12个字节 | 'abcd' | 13个字节 |


16. 关于表结构的修改语句:
> ALTER TABLE 旧表名 rename 新表名    
> ALTER TABLE <表名> modify  属性  数据类型    
> ALTER TABLE <表名> change 旧属性名 新属性名 新数据类型    
> ALTER TABLE 表名 ADD 属性名1 数据类型 [完整性约束条件]    
> ALTER TABLE 表名 DROP 属性名     
> ALTER TABLE 表名 DROP FOREIGN KEY 外键别名 

17. 关于分级，组合，内连接查询：  
#### 分级查询group by 
> group by单独使用时，只显示出每组的第一条记录，意义不大;  
> group by + group_concat用来放置每一组的某字段的值的集合;
>- `select sso, GROUP_CONCAT(phone) from user group by sso`

> group by +聚合函数最常用于统计   
> group by + having用户限制分组结果的显示  
> group by + with rollup会在最后加上一条新纪录，统计各分组总和。  
>- `select app_id, count(uid) as counts from t_ucop_user_test group by app_id  with rollup`  

> group by分级查询中，（1）在select中指定返回的字段，要么包含在group by语句的后面，作为分组依据，要么就要被包含在聚合函数中。（2）where子句的作用是在对查询结果进行分组前，将不符合条件的行去掉，即在分组之前过滤数据，不能包含聚合函数；having子句的作用是筛选满足条件的组，即在分组之后过滤数据，经常包含聚合函数。   

#### 组合查询union  
用于合并两个select的结果集，只要两个结果集的列数相等，顺序相同。默认消去结果集中的重复行，若要返回重复数据，使用union all。    
`select uid from user union select uid from user_test`   

#### 内连接inner join   
实质是两表先进行笛卡尔乘积运算(用第一个对象的每一项乘以第二个对象的每一项，即交叉成绩)，然后再根据on后面的限制条件对结果进行筛选。   

18. 一些sql语句集锦：  
> 两表联合，按分组进行更新  
```java
UPDATE table1 A
INNER JOIN (SELECT id,COUNT(*) AS Stat FROM table2 WHERE enable=1 GROUP BY id) as B
ON B.id = A.id
SET A.UpdateColumn = B.Stat
```    
> 统计某天注册后连续登录3天的用户数
```java
select idd, count(tti) as num from 
(select distinct(ull.uid) as idd, left(logintime,10) as tti
from user_login_log  ull inner join  user u  on ull.uid=u.uid
where left(registertime,10) = "2017-06-26" and left(logintime,10) between DATE_ADD("2017-06-26",INTERVAL 1 DAY)  and  DATE_ADD("2017-06-26",INTERVAL 3 DAY)
)a group by idd having num >=3 
```
> 统计存在于一个表而另一个表不存在的数据   
```java
select count(*) from user_test where uid not in (select uid from user)
```

## 六、Maven常用语句 
 mvn clean install -Dmaven.test.skip=true   
 mvn clean compile  
 mvn clean test  
 mvn clean package  
 mvn clean install  
### 使用maven命令构建Java项目：  
 > `mvn archetype:generate/create -DgroupId=packageName -DartifactId=projectName -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`   
 **pom中添加插件`spriong-boot-maven-plugin`的两个好处**：
 > 打包项目为可执行jar包，即可运行`java -jar xxx.jar`启动项目  
 > 运行`spring-boot:run` 直接启动项目    

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

## 七、单元测试Junit
> (1) 测试方法用@Test注解，public void 修饰 
> (2) @BeforeClass和@AfterClass只执行一次，在所有方法都被调用之前/后  
> (3) @Before和@After在每个方法被调用前/后执行  

## 八、Java项目打war/jar包
1. Maven Projects -->项目名称 --> Lifecycle --> package。
1. Build --> Build Artifacts --> shcms --> Edit --> Web Application Archive --> OK, build之后即可打成war包。

> Finder  --> Commond+Shift+G 快捷键 -->  输入/private/etc --> 即可看到hosts文件，但不能修改，可复制一份至其他地方，待修改后，再拖进去替换原来的。

> 命令行进入该项目的文件夹，` E:\myproject\> jar cvf project.war */ . `      
> 进入该项目的父文件夹， ` E:\> jar -cvf myproject.war myproject `     

## 九、git上传代码及ssh-key生成  
### ssh key： 
1. 先查看ssh key 是否已经存在： windows进入`C:\Users\Administrator\.ssh`查看`id_rsa`和`id_rsa.pub`文件是否存在 ， mac 输入`cd ~/.ssh`查看 ;  
2. 输入命令 `ssh-keyen -t rsa -C "your email" `,一直回车，即可生成上述两个文件  
3. 打开id_rsa.pub文件，复制其全部内容至gitlab中SSH-Add SSH Key中,title可以任意填 ；  
4. 输入命令`%userprofile%\.ssh\id_rsa.pub`查看ssh-key是否已存在 ；   
5. mac 打开 ssh文件： open ~/.ssh 。  
### git传代码：  
1. 通过`git init` 把当前目录变成git可以管理的本地仓库 ；  
2. 通过`git add 文件/文件夹`将文件添加到暂存区 ， 通过`git commit -m "备注"`将文件提交到本地仓库 ；  
3. 通过`git remote add origin 远程仓库地址`将本地仓库与远程仓库关联 ；  
4. 通过`git push -u origin master`将本地master分支内容推送至远程master分支， -u只有第一次推送时添加，目的是将两处的master分支关联 。
 
## 十、linux常用命令  
> ls ;    ls -l ;   ls -s -S ;   ls -a ;  
> cd ..      
<font color = "green">返回上层目录</font>  
> mkdir DIRECTORY     
<font color = "green">创建目录</font>   
> rmdir DIRECTORY        
<font color = "green">删除空目录</font>    
> rm -r DIR       
<font color = "green">删除目录</font>   
> rm FILE    
<font color = "green">删除文件</font> 
> rm *    
<font color = "green">删除当前目录所有文件</font>  
> cp FILE1 FILE2     
<font color = "green">将文件1复制成文件2  </font> 
> cp FILE /DIR1/DIR2    
<font color = "green">将文件复制到指定目录 </font>  
> cp -V -R * DIR      
<font color = "green">将当前目录下所有文件复制到指定目录中</font>     
> echo 'content'>FILE    
<font color = "green">创建指定内容的指定文件</font>   
> grep --color = auto 'content' /DIR/FILE     
<font color = "green">突出显示指定文件中的指定内容</font>   
> more/cat FILE       
<font color = "green">显示文件内容</font>     
> cat FILE1 >> FILE2       
<font color = "green">将文件1内容附加到文件2内容之后</font>   
> mv FILE ..          
<font color = "green">将文件移动到上层目录</font>   
> mv FILE1 FILE2     
<font color = "green">将文件1重命名为文件2</font>    
> services.msc        
<font color = "green">进入本地服务界面</font>     
> date    
> cal
> bc   
<font color = "green">显示日期，日历，计算器</font>  
> cat / tac FILE  
<font color = "green">从第一行/最后一行 查看文件内容</font>  
> touch FILE   
<font color = "green">新建一个文档</font>  
>file FILE  
<font color = "green">查看文件类型</font>     
> cls  
<font color = "green">清屏</font>  
> history | grep "xxx"   
<font color = "green">查询指定的输入历史</font>      
> cd ~/data/log    
<font color = "green">绝对路径，进入根目录/data/log</font>   
> cd ./data/log    
<font color = "green">相对路径，进入当前目录的/data/log路径下</font>  
> lsof -i tcp:<端口>    
<font color = "green">查看指定端口占用的进程</font>    
> kill <PID>     
<font color = "green">杀死该标识所代表的进程</font>    
> CH-yfy@Yuans-iMac ~$ cd /data/    
<font color = "green">pwd = /data</font>    
> CH-yfy@Yuans-iMac ~$ cd data/    
<font color = "green">pwd = CH-yfy@Yuans-iMac/data</font>  
> chown <所有者> <文件名>  
<font color = "green">更改文件的所有者:将文件的所有者更改为它</font>  
> chown -R <用户>:<组> <目录>  
<font color = "green">更改目录的所有者:将目录中所有文件的所有者和组更改为用户和组</font>   
> chmod <u|g|a|o>  <+|-|=>  <r|x|w> <文件名>  
<font color = "green">改变文件或目录的访问权限</font>    
> ps -ef | grep <进程名> 
<font color = "green">查看某进程是否运行</font>  
> ifconfig
<font color = "green">查看激活网卡的详细信息,eth/en代表以太网网卡信息（ipv4,ipv6,broadcast,mask）</font>  
> ifconfig eth0 172.16.0.118 netmask 255.255.0.0 = ifconfig eth0 172.16.0.118/16 
<font color = "green">配置临时IP：只需要两个参数，一个IP地址，一个子网掩码</font>  
> ps -A / ps -e
<font color = "green">查看所有进程</font>    
    
 ## 一、Windows应用技巧
 1. 一键锁屏：windows + L 
 2. 显示桌面： windows + D
 3. 命令提示符： windows + R 
 4. 关掉页面: ctrl + W
 5. 重新打开一个窗口： ctrl + N
 6. 查找某个内容： ctrl + F       

## 二、mac电脑基本操作 
1. 新建一个文件：  
> $ `cd dir` 
> `vim filename`  
> 按 i 键，输入文件内容  
> 按 `esc` 或 `：+ shift` 键，退出编辑  
> $ `: wq`  ，保存文件,`!q` 为不保存   

2. 配置环境变量：  
> $ `vim ~/.bash_profile`  ，打开系统配置文件  
> 输入`export PATH = $PATH:/文件路径`  
> 保存之后，$`source ~/.bash_profile` ，即可生效    

3. 修改host文件： 
> $ `vim /etc/hosts` ，进入文件编辑   
**启动可执行文件的命令**： $`sh file` 或 `./file`  
**`commond + N`**：重新打开一个页面  
**`commond + T`**：在原来的页面里打开新的页面  

4.  查看本机ssh连接到的host： vi ~/.ssh/known_hosts  
5. Homebrew---mac的软件包管理工具：
> **brew安装** , 输入`ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`      
> **brew安装软件**, 输入`brew install 软件名`  
> **brew卸载软件**, 输入`brew uninstall/remove 软件名`   
> **brew查询软件**, 输入`brew search 软件名`      
> **brew列出已安装软件**, 输入`brew list`       
> **更新brew**, 输入`brew update`      
> **用浏览器打开brew的官方网站**, 输入`brew home`     
> **显示软件信息**, 输入`brew info 软件名`  
> **显示包依赖**, 输入`brew deps`           
  
## 三、redis
### springBoot集成redis缓存
1. 添加依赖：
```java
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>1.5.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-redis</artifactId>
            <version>1.3.8.RELEASE</version>
        </dependency>
```
2. application.properties添加配置：
```java
// redis数据库的索引，默认为0
spring.redis.database=0　　　　
spring.redis.host=127.0.0.1
#spring.redis.password=pwd
spring.redis.port=6379
// 最大空闲链接数
spring.redis.pool.max-idle=8　
// 最小空闲连接数　
spring.redis.pool.min-idle=0　
// 连接池最大连接数，负数表示无最大连接数　
spring.redis.pool.max-active=8　
// 连接池最大阻塞等待时间，负数表示没有
spring.redis.pool.max-wait=-1　　
```
3. 启动类或者具体的DAO类中添加注解@EnableCaching:
此注解会对每个bean中被@Cacheable, @CachePut, @CacheEvict修饰的public方法进行缓存操作。   

### @Cacheable用法(value属性是必须的)
```java
@Cacheable(value = "companyCache", key = "'myCompanyCache:'.concat(#root.methodName)")
public void findByCompanyId(){ }
```
> redis中生成: `companyCache~keys` 和 `myCompanyCache: (文件夹)   
myCompanyCache:findByCompanyId (key)
`

**正确应设置成**:  
```java
@Cacheable(value = "companyCache:", key = "'companyCache:'.concat(#root.methodName)")
```
### @CacheEvict用法
```java
 @CacheEvict(value = "companyCache", key = "'myCompanyCache:'.concat('findByCompanyId')")
    public void updateCompanyId(){}
```
> 可用在update类方法上，也可用在remove类方法上。

### @cachePut用法
> 每次都会执行方法，并将结果进行缓存。用法与@Cacheable用法一致,用在update类方法上。

### @Caching用法
> 可以包含以上三个注解，key-value分别对应(cachable=[@Cacheable], put=[@CachePut], evict=[@CacheEvict])。

### @CacheConfig用法
> 类级的注解，统一指定缓存的value和key。

### 自定义KeyGenerator和CacheManager  
```java
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
}
```

1. 缓存注解  
> @Cacheable(value="缓存的名称",key= "key",condition="符合缓存的条件)  
```java  
@Cacheable(value = "redisName"，key="#username")  
public void func(String username){}  
```  
*调用该方法时，会先从一个名叫redisName的缓存中查询，如果没有，则执行实际的方法(即查询数据库),并将执行结果存入缓存；如果有，则返回缓存中的key(即username)对应的对象。*   
> @CachePut(value = "缓存的名称",key = "key",condition = "符合缓存的条件")    
```java  
@CachePut(value = "redisName",key = "#user.getUsername()",condition = "#user.getAge()>20")  
public User updateUser(User user){}  
``` 
*方法总是会被执行，其结果也会保存至缓存，确保缓存与数据库的同步更新。*    
> @CacheEvit(value = "",key = "",condition = "",allEntries = "是否清空所有缓存缺省为false",beforeInvocation = "是否在方法执行前清空缓存")   
```java 
@CacheEvit(value = "redisName",allEntries=true)  
public void removeCache(){}  
```  
*清除名为redisName的缓存内容。*   
> `@Caching(put = {@CachePut(value = "",key = ""),@CachePut(value = "",key = "")})`   
> `@Caching(evit = {@CacheEvit(value = "",key = ""),@CacheEvit(value = "",key = "")})`    
*多用于更新，清除，组合多个Cache注解。*    
  
2. 操作命令(redis是使用内存存储的非关系数据库)  
> redis-server.exe  
> redis-cli.exe -h 127.0.0.1 -p 6379 (重新打开一个命令框输入)
>- 启动该redis服务器  

> set key value   
> get key   
>- String字符串赋值，取值 

> hmset key field value  
>- hash哈希赋值,适用于存储对象

> hget all key  
>- 获取对象所有信息  

> hget key field  
>- 获取对象的某属性值  

> hkeys key  
>- 获取对象的所有属性值  

> hdel key field  
>- 删除对象的某属性值 

> lpush key value / rpush key value  
>- 添加一个元素到列表的头(左)部或尾(右)部  

> lrange key start end   
>- 获取指定范围内的元素  

> llen key  
>- 获取key的长度  

> lpop / rpop  key  
>- 取出并移除key中的第一个(最后一个)元素    

> sadd key value  
>- 添加元素到集合中，成功返回1，元素已存在返回0，其余返回nil  

> smembers key  
>- 取出集合中的元素

> srem key value  
>- 从集合中移除元素

> zadd key count value 
>- 添加元素到有序集合中  

> zrangebyscore key start end  
>- 获取有序集合中指定范围的元素  

> zcount key min max  
>- 获取有序集合中score在min和max之间的元素的个数  

> zscore key member   
>- 获取该元素的score  

> del key
>- 删除key  

> keys *  
>- 查看所有key

> dump key  
>- 序列化指定的key 

> more key db  
>- 当前db的key移到指定db中 

> exists key  
>- 检查key是否存在  

> persist key  
>- 移除key的过期时间 

> expire key seconds   
> pexpire key millseconds   
> epireat key timestamp   
>- 为key设定过期时间，单位：秒，毫秒，时间戳 

> rename key newname   
>- 修改key的名字 

> type key  
>- 返回key所存储的值的类型  

> config set requirepass "your pwd"  
> config get requirepass  
>- 为数据库设置密码，并查看密码

> flushdb  
>- 清空redis缓存

##  四、mongodb  
| database  |   table | row  | column  | index  |
|-----------|---------|------|---------|---------|
| database |  collection |  document  |   field   |  index  |     

> ./mongod (mac)    
> mongod.exe (windows)
>- 启动mongodb服务，使用默认的数据库目录/data/db  

> ./mongod --dbpath /Users/hyh/xxxxx(绝对路径)   
>- 启动mongodb服务，指定数据库目录 

> ./mongo     
> mongo.exe
>- 通过shell连接mongodb服务器  

> mongodb://localhost (windows)
>- 连接本地数据库服务器，端口默认  

> mongodb://<用户名>:<密码>@<主机地址>/<数据库名>  
>- 使用用户名/密码连接到指定服务器的某个数据库  

> show dbs 
>- 查看所有数据库   

> db  
>- 显示当前数据库

> db.dropDatabase() 
>- 删除当前数据库

> show tables
>- 查看数据库的所有集合

> db.createCollection(tableName)  
>- 创建集合（表）  

> db.TableName.drop()
>- 删除数据库的指定集合

#### MongoDB的增删改查
> db.CollectName.insert({key1:value1,key2:value2})   
>- 向数据库集合中插入文档  

> db.CollectName.find()
>- 查询文档

> db.CollectName.find().pretty()
>- 查询文档，并将结果格式化

> db.CollectName.findOne()
>- 只返回一个文档  

> db.CollectName.find( {key:value} )
>- 条件查询 

> db.CollectName.find({key1:value1, key2:value2})
>- and 条件查询 

> db.CollectName.find( {key: {$lt:IntNum} } )  
>- 小于条件查询(gt>,lte>=,ne!=)

>db.CollectName.find( {$or:[ {key1:value1},{key2:value2} ]} )  
>- or 条件查询 

> db.CollectName.find().limit(Num) 
>- 返回指定数量的文档

> db.TableName.update( {oldkey:oldvalue},{$set:{newkey:newvalue}},{upsert:boolean,multi:boolean})  
>- 更新集合中的文档，upsert表示无该记录时是否插入，multi表示有多条记录符合条件时是否全都更新，默认都为false 

> db.CollectionName.update( {oldkey:oldvalue},{$set:{newkey:newvalue}} )   
>- 只更新第一条记录  
 
> db.CollectName.remove( {key:value},{justOne:boolean})
>- 删除文档，justOne表示是否只删除一个符合条件的文档

> db.CollectName.find().sort( {key:1} )
>- 按指定字段升序排序，-1为降序  

> db.CollectionName.find( {key:{$type:2}} )  
>- 查找集合中指定key类型为String的记录  

> db.CollectionName.find().skip(10).limit(100)  
>- 获取从第10条记录后的100条记录   

> db.CollectionName.find( {key:{$in:[x,x,x]}} )  
>- 获取key在指定集合中的记录  

> db.CollectionName.find( {},{key1:1, _id:0})  
>- 只返回key1列，0表示不显示 1表示显示，_id不同于其他咧，如果不显式设为0，就会返回  

> db.CollectionName.find( {},{key1:1, key2:1 _id:0}).limit(3).skip(1)  
>- 跳过第一列后，返回三条记录，只返回key1,key2两列  

> db.CollectionName.remove() 
>- 清空集合

#### MongoDB的索引
> db.CollectName.ensureIndex( {key : 1} )
>- 按升序为指定字段创建单个索引   

> db.CollectName.ensureIndex({key1 : 1, key2 : -1})
>- 为指定字段创建复合索引 

> db.CollectName.dropIndex("ndexName")
>- 删除指定名字的索引,名字一般为:字段_1(-1)

> db.userLoginLogDO.dropIndexes()
>- 删除全部索引 

> db.CollectName.getIndexes()
>- 查看索引 

> db.CollectName.find({}).explain()
>- 获取执行计划，性能分析

#### MongoDB聚合查询
> $project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。  
> $match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。  
> $limit：用来限制MongoDB聚合管道返回的文档数。  
> $skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。   
> $group：将集合中的文档分组，可用于统计结果。  
> $sort：将输入文档排序后输出。 

```java
//返回结果字段名称：returnName， 表中属性字段名称：fieldName

db.CollectionName.count() 
db.runCommand({"distinct" : "CollectName", "key" : "fieldName"})  // 必须指定集合名和要区分的字段

db.CollectionName.aggregate([{"$group" : {"_id": null, "returnName":{"$sum" : 1}}}])  //不分组
db.CollectionName.aggregate([{"$group" : {"_id" : null, "total" :{"$sum" : "$quantity"}}}])  
//select sum(quantity) as total from tableName

db.CollectionName.aggregate([{"$group" : {"_id" : "$fieldName", "returnName": {"$sum" : 1}}}])  //分组

db.CollectionName.aggregate([{$group : {_id : "$fieldName", returnName : {$max : "$fieldName"}}}])
db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName1: {$max : "$fieldName"}}}, {$group: {_id: null, returnName: {$max: "$returnName1"}}}])

db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName: {$push: "$fieldName"}}}])   

db.CollectionName.aggregate({"$project" : {"key1" : 1, "key2" : 1, "_id" : 0}})
//结果中只返回指定的列
db.CollectionName.find({}, {key1: 1, key2:1, _id:0})

db.CollectionName.aggregate([{$group: {_id: "$fieldName", returnName: {$push: {time: "$fieldName", returnName: "$fieldName"}}}}])
//将指定字段的值添加到数组中

db.collectName.aggregate([{$group: {_id: "$uid", sets: {$addToSet: "$loginTime"}}}])  
//将指定字段的值添加到数组中，不允许重复值

db.collectName.aggregate([{$group: {_id: "$uid", results: {$first: "$loginTime"}}}]) 
//返回每组第一个文档
db.collectName.aggregate([{$group: {_id: "$uid", results: {$last: "$loginTime"}}}])  
//返回每组最后一个文档

db.CollectName.find({"uid": {"$in" : ["c7d6ad0c2ea34719", "6817f364aeb44ab1", "f020cbf41d7b4e51"] }})
select * from user_login_log where uid in [xxx, xxx, xxx]
// "$nin"  =  no in ,  "$ne"  =   !=

db.CollectName.find({"uid"  :  /6817/})
db.CollectName.find({"uid" : {"$regex" : "6817"}})
select * from tableName where uid like concat("%", "6817", "%")  //正则表达式

db.ConnectNam.find({"uid"  :  {"$all" : ["6817f364aeb44ab1", "c7d6ad0c2ea34719"]}})  
//必须满足all中的所有值，而in是满足其中一个即可

db.CollectName.find({"uid"  :  {"$exists" : true}})  //判断某个字段是否存在
```

#### MongoDB与日期时间相关查询
```java
db.CollectName.find({"fieldName" : { "$gte" : ISODate("2017-11-02 06:09:00.000Z")  
, "$lte" : ISODate("2017-11-02 06:09:00.000Z") }})    

db.CollectName.find({"fieldName" : {"$gte" : new Date("2017-11-02"), "$lte" : new Date("2017-11-06")}})

db.CollectName.aggregate([{"$match" : {"fieldName" : {"$gte" : new Date("2017-11-02"), "$lte" : new Date("2017-11-10")}}},
                                             {"$group" : {"_id" : "$fieldName", "returnName" : {"$sum" : 1}}}])  
```  

         












#### mongoTemplate, java版：
```java
public UserLoginLogDO func1(String param) {
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("uid").is(param));
        List<UserLoginLogDO> loginLogDOList = mongoTemplate.find(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (loginLogDOList.size() > 0) {
            return loginLogDOList.get(0);
        }
    }

 public String func2(String param1, String param2) {
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("deviceSn").is(param1), Criteria.where("macAddress").is(param2));
        List<UserLoginLogDO> loginLogDOList = mongoTemplate.find(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (loginLogDOList.size() > 0) {
            String uid = loginLogDOList.get(0).getUid();
            UserDO userDO = userMapper.findUserByUid(uid, livemode);
            return userDO.getPhone();
        }
    }

 public int func3(String start, String end, Integer livemode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
        try {
             sDate = sdf.parse(start);
             eDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("loginTime").gte(sDate), Criteria.where("loginTime").lte(eDate));
        Aggregation agg = newAggregation(match(base), group("uid"));
        AggregationResults<UserLoginLogDO> temp = mongoTemplate.aggregate(agg, UserLoginLogDO.class, UserLoginLogDO.class);
        List<UserLoginLogDO> results = temp.getMappedResults();
        if (results.size() > 0) {
            return results.size();
        }
    }

 public String func4(String stime, String etime, String uid, Integer livemode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = sdf.parse(stime);
            eDate = sdf.parse(etime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Criteria base = new Criteria();
        base.andOperator(Criteria.where("loginTime").gte(sDate), Criteria.where("loginTime").lte(eDate), Criteria.where("uid").is(uid));
        UserLoginLogDO userLoginLog = mongoTemplate.findOne(new Query(base).with(new Sort(Sort.Direction.DESC, "loginTime")).limit(1), UserLoginLogDO.class);
        if (userLoginLog != null) {
            return String.valueOf(userLoginLog.getLoginTime());
        }
    }
```

## 五、docker基础  
docker是一个开源的容器引擎，基于go语言，可以让开发者打包他们的应用及依赖包到一个轻量级、可移植的容器中 。  
**容器与镜像的关系**：前者相当于对象实例是可以读写的，后者相当于类，只能读。先声明镜像，在创建容器。一个镜像可以有多个容器。
1. docker命令
> docker info 
>- 输出docker的详细信息

> docker version
>- 查看docker版本

> docker search ImageName 
>- 搜索可用的某个镜像

> docker pull ImageName 
>- 下载镜像

> docker commit id ImageName 
>- 保存对镜像的修改

> docker inspect ImageId
>- 查看运行中的镜像

> docker push ImageName
>- 发布自己的镜像

> docker images
>- 查看本地所有镜像列表

> docker ps 
>- 查看正在运行的容器  

> docker ps -a
>- 查看所有运行过的容器

> docker start ContainerId/Name 
>- 启动容器

> docker stop ContainerId/Name
>- 停止容器

> docker rm ContainerId/Name 
>- 先删除容器

> docker rmi ImageId/Name 
>- 再删除镜像

> docker attach ContainerId/Name
>- 进入/附着在容器上，会打开shell，可能需回车才能进入会话 

> docker inspect ImageId/containerId
>- 查看镜像/容器的详细情况

> docker images <仓库名>  
>- 查看该仓库名下的所有镜像  

> docker images -f since/before=<仓库名>:<标签>   
>- 查看在指定镜像之后/之前建立的镜像

> docker export ContainerId > <文件名.tar>  
>- 导出容器库快照到本地压缩文件  

> cat <文件名.tar> | docker import - <镜像名:标签名>   
>- 从容器快照文件中再导入为镜像  
      
> docker rm -q <ContainerName/Id>   
>- 删除一个运行中的容器   

> docker rm (docker ps -a)  
>- 删除所有处于终止状态的容器

> docker rmi $(docker images -q -f dangling=true)   
>- 删除虚悬镜像  

> docker rmi $(docker images -q <仓库名>)   
>- 删除所有指定仓库名的镜像    

> docker images --digests  
>- 查看镜像摘要    

> docker top <容器>   
>- 查看容器内的所有进程   

> docker exec -it <容器名> bash         
>- 以交互式终端方式进入容器，并执行bash命令，即获得一个可操作的shell

> docker inspect --format="{{.xxx}}"  ContainerName/Id
>- 查看指定容器的某项属性

> docker tag ImageName:tag <打完标签的完整镜像名>
  
> docker run -p <主机端口>:<容器端口>     
> docker run -p <指定映射地址>：<主机端口>:<容器端口>   
>- 外部应用访问容器需要的ip和端口，默认是tcp,127.0.0.1     
     
> docker run -p <主机端口>:<容器端口/udp>         
>-  -p是容器内部接口绑定到指定的主机端口，-P是容器内部端口随机映射到主机的端口  

> docker port <容器> <端口>        
>- 查看映射端口配置  

> docker run --name <父容器> --link <子容器>:<连接别名> <镜像名>       
>- 容器互联，将新建的父容器连接到子容器，父容器将被允许访问子容器的信息  

> docker logs <容器名/Id>   
>- 获取全部容器日志  

> docker logs -f  <容器名/Id>  
>- 监控容器的日志，按Ctrl+C退出日志追踪  

> docker logs -f -t <容器名/Id>   
>- 为每条日志加上时间戳   

> docker exec -d <镜像> touch /etc/new_file  
>- 在容器内新建一个空白文件  

> docker ps -l   
>- 列出最后一次运行的容器   

> docker commit -m="xxx" --author="xxx" <容器ID> <最终的用户名/仓库名：b标签>  
>- 提交另一个新的定制容器    

> docker history <ImageId/Name>   
>- 从后向前查看某个镜像的构建过程和层级

> docker run -it <镜像> /bin/bash **或者** docker exec it <镜像> /bin/bash 打开交互式shell窗口后，就可对容器进行操作管理：  
>- root@xxxxxx:/# hostname    
>- <font color ="green">获取容器主机名</font>    
>- root@xxxxxx:/# cat /etc/hosts    
>- <font color ="green">打开文件，可看到<IP地址：该容器Id>配置项</font>   
>- root@xxxxxx:/# ip a      
>- <font color ="green">查看该容器的网络配置</font>      
>- root@xxxxxx:/# ps -aux   
>- <font color ="green">查看容器中运行的进程</font>     
>- root@xxxxxx:/# apt-get && apt-get install <软件名>   
>- <font color ="green">在容器中安装软件</font>    
>- root@xxxxxx:/# exit  
>- <font color ="green">退出shell，也停止容器</font>
  
2. docker安装   
- 官网安装之后，登录加速器网页[点击http://www.daocloud.io/mirror#accelerator-doc](http://www.daocloud.io/mirror#accelerator-doc), 取得url,eg(http://976he45.m.daocloud.io)；  
- 打开docker客户端-Daoemon-Basic-Registrymirrors, 填入上面的url；  
- Windows需要配置bip(防止host地址冲突)，进入Advanced,填入：   
```java
{
    "bip" : "172.19.0.1/24",
    "Registry-mirrors" : "["url"]"
} 
```      

3. DockerFile常用指令  
> 一般由四部分组成：   
>- 基础镜像信息(FROM)
>- 镜像维护者信息（可省略）(MAINTAINER) 
>- 构建镜像操作命令(RUN)
>- 启动镜像执行命令(CMD,ENTRYPOINT)    

**CMD:**
> `CMD <commond> <param1> <param2>` , shell格式    
*shell格式实际处理时会被包装为 `CMD ["/bin/sh", "-c", "<commond> <param1> <param2>"]`。*    

> `CMD ["<commond>","<param1>",<param2>"]` ,  exec格式    
> `CMD ["<param1>","<param2>"]` ， 作为ENTRYPOINT的参数   
>- `CMD /bin/bash` , 打开一个交互式shell窗口
>- `CMD ["/bin/ps"]` , 显示容器的当前进程  
>- `CMD ["echo","HelloWorld"]` , 打印出字符串    
>-  `CMD xxxxxx` + `docker run ImageName/Id` = `docker run ImageName/Id xxxxxx`     

> **启动容器时执行的命令，docker run 镜像名后的命令会覆盖CMD中的命令.**  
> **Dockerfile文件只能有一个CMD命令，若有多条，只有最后一条会被执行。**  

**ENTRYPOINT：**  
> `ENTRYPOINT <commond> <param1> <param2>` , shell格式   
> `ENTRYPOINT ["<commond>","<param1>",<param2>"]` ,  exec格式   

> **启动容器时执行的命令，不会被docker run 镜像名后提供的命令覆盖掉。**  
> **Dockerfile文件只能有一个CMD命令，若有多条，只有最后一条会被执行。**     
>  **ENTRYPOINT指定的命令也是可以强制重载的， 只需在docker run 时指定 --entrypoint 参数来重载即可**。

**CMD 与 ENTRYPOINT一起使用：**  
> `CMD ["echo","hello cmd"]` + `ENTRYPOINT ["echo","hello entry"]`  
>- docker run 结果为 `hello entry echo hello cmd `  

> `CMD echo "hello cmd"` +  `ENTRYPOINT ["echo","hello entry"]`   
>- docker run 结果为 `hello entry /bin/sh -c  echo hello cmd `  

> **当两者同时存在时，CMD（即使是个完整的命令）总是会被作为ENTRYPOINT的参数，添加在它的参数后面**。    
> **如果要同时使用两者，让CMD的指令正确执行，需要把ENTRYPOINT的命令写在一个shell脚本文件里，并使用`exec $@`来运行CMD中的命令**。

**RUN:** 
> `RUN <commond>`  , shell格式 
> `RUN ["<commond>","<param1>","<param2>"]`  , exec格式  

**FROM：**  
> `FROM <Image>` ,   `FROM <Image>:<tag>` , **Dockerfile的第一条指令，指明了基础镜像。**  

**MAINTAINER:**  
> `MAINTAINER <name>` , **指定维护者信息。**

**EXPOSE:**  
> `EXPOSE [port] (<port>)`, 服务端容器暴露的端口号，可以有多个，容器启动时需要通过 -p 再次指定。  

**ENV：**  
> `ENV <key> <value>` , 指定一个环境变量，可被后续RUN,COPY,等命令以$的形式使用。  

**ADD:**  
> `ADD <src> <dest>`  

> 复制本地指定路径下的文件/目录（可为Dockerfile所在目录的相对路径，也可为一个URL，可以是压缩文件，会自动解压）到容器中的指定路径中。  

**COPY：**  
> `COPY <src> <dest>`    

> 复制本地指定路径下的文件/目录（为Dockerfile所在目录的相对路径，不能为压缩文件）到容器中的指定路径中。   

**WORKDIR:**  
> `WORKDIR </path/to/workdir>`    

> 一个Dockerfile可以使用多个，为后续的RUN,CMD,ENTRYPOINT配置工作目录。后续命令若为相对路径，则会基于之前指定的路径，eg: `WORKDIR /a , WORKDIR b , WORKDIR c` , 则最终路径为/a/b/c。    

4. 镜像部署服务器流程     
可以采用执行自动化脚本文件的方式，会简便些，当然也可逐个命令运行。       
> 创建脚本文件build.sh:  
```java
mvn clean install -Dmaven.test.skip=true
docker build --no-cache -t <镜像名>:$1 .
docker tag <镜像名>:$1 <带有仓库地址的完整镜像名>:$1
docker push <带有仓库地址的完整镜像名>:$1  
```  

> 首次执行脚本文件时，为其添加权限:    
```java 
chmod +x build.sh  
```
> 每次更新时，在脚本文件目录下执行:  
```java
sh build.sh 1.5      //sh $0 $1
``` 

## 六、Tomcat虚拟目录映射方式
web应用开发好之后，若想供外界访问，需要把web应用所在目录交给web服务器管理，这个过程称之为虚拟目录的映射。   
1. 在server.xml文件的host元素中配置：  
> (1)`<Host></Host>`这对标签中添加`<Context path = "/demoapps" docBase = "F:\DemoProject"/>`即可将F盘的DemoProject应用映射到demoapps这个虚拟目录上，它是磁盘上不存在的目录，是我们自己定义的。  
>- path必须以"/"开头； docBase对应JavaWeb项目的所在目录。  

> （2）浏览器访问：`http://localhost:8080/demoapps/demo.jsp` 。 

2. tomcat服务器自动映射：
> tomcat服务器会自动管理webapps目录下的所有web应用，并把它映射成虚拟目录，所以直接将F盘下的DemoProject应用放在webapps文件夹下。  
> 浏览器访问： `http://localhost:8080/DemoProject/demo.jsp` 。  

3. 添加xml文件：  
> 在conf/Catalina/localhost目录下添加一个以xml为扩展名的demo.xml文件，文件中添加`<Context docBase = "F:\DemoProject"/>`。  
> 浏览器访问： `http://localhost:8080/demo/demo.jsp`,这里的虚拟目录就是文件名。  
