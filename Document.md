**小知识点集锦**     
1. Windows应用技巧:
```java
1. 一键锁屏：windows + L 
2. 显示桌面： windows + D
3. 命令提示符： windows + R 
4. 关掉页面: ctrl + W
5. 重新打开一个窗口： ctrl + N
6. 查找某个内容： ctrl + F      
```
2. mac电脑基本操作 
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
将数据从外存读取到内存的成为输入流，将数据从内存写入外存的称为输出流。   
**输入流抽象类:**   
> InputStream(字节输入流)，Reader(字符输入流)   

**输出流抽象类：**    
> OutputStream(字节输出流)，Writer(字符输出流)

**标注于类上，字段的值为空时，不参与序列化，不在返回结果中显示：`@JsonInclude(JsonInclude.Include.NON_NULL)`。**    
**标注于类上，字段的值为空时，参与序列化，仍在返回结果中显示：`@JsonInclude(JsonInclude.Include.ALWAYS)`。**   
  
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

## 四、Git命令
### 基本命令
> 设置用户名、邮箱：  
>- git config --global user.name "Your Name"  
>- git config --global user.email "Your email"    

> 查看当前git用户名邮箱
>- git config user.name 
>- cat ~/.gitconfig

> 初始化一个目录为本地仓库：  
>- git init  

> 提交文件： 
>- git add FileName 
>- git add .  //批量添加  
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
>- git remote add origin git@github.com : 用户名/远程库名.git  

> 删除已关联的远程库:
>- git remote rm origin  

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

### git上传代码及ssh-key生成  
#### ssh key： 
1. 先查看ssh key 是否已经存在： 
> windows进入`C:\Users\Administrator\.ssh`,查看`id_rsa`和`id_rsa.pub`文件是否存在;
> mac 输入`cd ~/.ssh`查看, `open id_rsa.pub`查看文件内容;  
2. 输入命令 `ssh-keyen -t rsa -C "your email" `,一直回车，即可生成上述两个文件;   
3. 打开id_rsa.pub文件，复制其全部内容至gitlab中SSH-Add SSH Key中,title可以任意填。

#### git上传代码：  
**本地新建的项目首次提交到git远程库，必须先要创建一个目录作为与远程库对应的本地库，然后再进行如下系列操作**
1. 通过`git init` 把当前目录变成git可以管理的本地仓库 ；  
2. 通过`git add 文件/文件夹`将文件添加到暂存区 ， 通过`git commit -m "备注"`将文件提交到本地仓库 ；  
3. 通过`git remote add origin 远程仓库地址`将本地仓库与远程仓库关联 ；  
4. 通过`git push -u origin master`将本地master分支内容推送至远程master分支， -u只有第一次推送时添加，目的是将两处的master分支关联。

#### idea 关于git操作：
```java
//1. 可以进行git的pull和push
VCS -> Git -> Enable Version Control Integration -> Git
//2. 查看默认的远程库地址
VCS -> Git -> Pull 
//3. 如果不对，进行修改
//4. Push的远程库地址也是和Pull一样的

当用idea导入从git上clone，或者push上git的项目时，打开VCS-Git-Pull时，就能看到远程库地址了。(自己首次push的需要先添加版本分布控制策略)。
```

#### 同一台机器使用两个GIT账号
1. 两个账号对应生成两个公钥，并保存在不同的文件里：id_rsa.pub 和id_rsa_tem.pub
2. 打开名为ssh目录下,名为config无后缀的文件(没有就vim新建),修改其内容为:
```java
Host github.com  
    HostName github.com  
    PreferredAuthentications publickey  
    IdentityFile ~/.ssh/id_rsa  
  
Host tem.github.com  
    HostName github.com  
    PreferredAuthentications publickey  
    IdentityFile ~/.ssh/id_rsa_tem  
```
3. 测试配置是否正常：
`ssh -T git@github.com `  和 `ssh -T git@tem.github.com `     
 下图表示配置成功:     
 ![alt-text](/images/git.png)      
 
 `git remote -v`  可以查看当前目录所连接的github库地址
 
4. 可以按正常流程提交代码，只不过另一个账号关联远程库时要用 `git@tem.github.com`。

### 工作区，暂存区储藏
当前develop分支:
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }

  //修改内容
  @GetMapping("/two")
  public String test() {
    return "Hello ,it is modified text";
  }
}
```

```java
git stash
git stash list   //查看储藏列表
git status //工作区就变成干净的了
```
切换master分支：
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }
}
/**
   修改内容的内容并没有同步过来
  */
```
切换develop分支:
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }
}
/**
   修改内容的内容已经被隐藏
  */
```
 
```java
git stash apply    
//或者 
git stash apply —index  //不仅恢复工作区，也会恢复暂存区
//或者 
git stash pop  //重新应用储藏，同时立刻将其从堆栈中移走
git stash drop  //删除储藏

/**
如果再次直接切回master，修改会同步，需要重新进行储藏
*/
```  

 ## 五、MySql基本语句  
 1. select column from table where column = value
 2. select column from table where column = value1 and(or) column= value2
 3. select column from table where column in List
 4. select column from table where column between value1 and value2
 5. insert into table (column) values (value)
 ```java
 //一次性插入多条记录
insert into user_login_log_test(uid，logintime) values 
(value11, value12),
(value21, value22);
```   
 6. update table set volumn = value where volumn = value
 7. delete from table where column = value
 8. create database / drop database
 9. 关于日期的函数：  
 > `DATE(xx)` 提取时间xx的日期部分  
 > `DATE_ADD(xx,INTERVAL num type)`  日期xx增加num个单位  
 > `DATE_SUB(xx,INTERVAL num type)`  日期xx减少num个单位   
      
10. limit函数：  
```java
select * from table limit m , n
//数据库中的记录下标是从0开始的，即从记录的第 m+1 条开始，共选择 n 条数据。    
select * from table limit x
//取出前x条记录 
```           
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
> 进入mysql/bin目录路径下（无环境变量时，需进入mysql的安装目录下）     
> 连接远程:    
> mysql  -h <主机> -P <端口> -u <用户名> -p<密码>  //p之后不加空格直接登录，也可不输入密码，待回车后单独输入密码      

> exit;  退出数据库 

> net start mysql; 启动数据库

> net stop mysql;  关闭数据库 

> show databases;  //每一条语句必须以分号结尾

> use db_name

> 本地启动连接mysql:   
>- 查看mysql状态: `sudo Documents/tools/mysql/support-files/mysql.server status`    
>- 启动mysql服务: `sudo Documents/tools/mysql/support-files/mysql.server start`   
>- 停止mysql服务: `sudo Documents/tools/mysql/support-files/mysql.server stop`     
>- 连接本地mysql: `sudo Documents/tools/mysql/bin/mysql -u root -p` or `sudo Documents/tools/mysql/bin/mysql -h 127.0.0.1 -u root -p`       
**此处是没有配置环境变量，所以必须加上路径**

15. 关于mysql的几大数据类型： 
#### unsigned属性，特殊应用场景：存放ip  
> INET_ATON()函数：将IP转换为数值类型;  
>- `select INET_ATON('127.0.0.1')` 

> INET_NTOA()函数：将数值类型转换为IP。   
>- `select INET_NTOA(2130706433)`

> IPV4的最大地址(255.255.255.255)转换后即为unsigned int的最大值(4294967295), 而int的范围为-2147483648-21474833647, 所以要用unsigned int 存储IP地址。  

#### decimal(M,D)
> 其中，M代表正数和小数部分的总位数，D代表小数部分的位数。  

#### timestamp和datetime    
> 显示结果一样，都是固定的“YYYY-mm-dd hh:MM:ss”的形式。     
```java
 insert into table(t1 timestamp,t2 datetime) values(now(),now()); 
 select * from table; 
 t1="2017-10-06 16:12:56" ,  t2="2017-10-06 16:12:56"
 ```   
> timestamp时间范围为"1970-01-01 00:00:01" UTC 到"2038-01-19 03:14:07" UTC, UTC是协调世界时，就如我们中国的时区是UTC+8，显示的时间范围要加上8小时。        
> 保存一个timestamp类型的时间时，它的值以UTC格式存放，即就是从"1970-01-01 00:00:01"到当前的秒数。  

#### char(M)和varchar(M)  
> M指的是字符数        
> 常规的laint1字符集，1个字符=1个字节，char存储最大占用255个字节； 国内使用的UTF-8字符集，1个字符=3个字节，char存储最大占用255*3=765个字节。     
> char用于保存固定长度的字符串，最大char(255),保存比指定长度大的值将被截断，而比指定长度小的值将会用空格进行填补。保存时，数据库自动填充，读取时，数据库自动删除。   
> varchar用于保存可变长字符串，最大varchar(21844)存储长度是字符串的长度+一个记录字符串本身长度的字节，字符串长度<=255时，算个字节，字符串长度>255时，算2个字节。   
>
 | 字符串  | char(4) | 存储需求 | varchar(4) |存储需求 |
 |:-------| --------:| :------: |:---------|:------|
 | " | '    ' | 12个字节 | '' | 1个字节 |  
 |'ab'   | 'ab  ' | 12个字节 | 'ab' | 7个字节 |
 | 'abcd' | 'abcd' | 12个字节 | 'abcd' | 13个字节 |
 | 'abcdefg' | 'abcd' | 12个字节 | 'abcd' | 13个字节 |


16. 关于表结构的修改语句:
> ALTER TABLE <旧表名> rename <新表名>    
> ALTER TABLE <表名> modify  <属性>  <数据类型>  
> - ALTER TABLE user MODIFY c CHAR(10);
  
> ALTER TABLE <表名> change <旧属性名>  <新属性名>  <新数据类型>    
> ALTER TABLE <表名> ADD <属性名> <数据类型> [完整性约束条件]  
> - ALTER TABLE user ADD refresh_token VARCHAR(1000);  

> ALTER TABLE <表名> DROP <属性名>     
> ALTER TABLE <表名> DROP FOREIGN KEY <外键别名>         
> ALTER TABLE user CHANGE i j BIGINT;  
>- i是要修改的列名，j是修改后的列名。    

> ALTER TABLE user ALTER i SET DEFAULT 1000;      
> SHOW COLUMNS FROM user;

17. 关于分级，组合，内连接查询：  
#### 分级查询group by 
> group by单独使用时，只显示出每组的第一条记录，意义不大;  
> group by + group_concat用来放置每一组的某字段的值的集合;
>- `select sso, GROUP_CONCAT(phone) from user group by sso`

> group by +聚合函数最常用于统计   
> group by + having用户限制分组结果的显示  
> group by + with rollup会在最后加上一条新纪录，统计各分组总和。  
>- `select app_id, count(uid) as counts from t_ucop_user_test group by app_id  with rollup`  

> group by分级查询中,(1)在select中指定返回的字段，要么包含在group by语句的后面，作为分组依据，要么就要被包含在聚合函数中。(2)where子句的作用是在对查询结果进行分组前，将不符合条件的行去掉，即在分组之前过滤数据，不能包含聚合函数；having子句的作用是筛选满足条件的组，即在分组之后过滤数据，经常包含聚合函数。   

#### 组合查询union  
用于合并两个select的结果集，只要两个结果集的列数相等，顺序相同。默认消去结果集中的重复行，若要返回重复数据，使用union all。    
`select uid from user union select uid from user_test`   

#### 内连接inner join   
实质是两表先进行笛卡尔乘积运算(用第一个对象的每一项乘以第二个对象的每一项，即交叉乘积)，然后再根据on后面的限制条件对结果进行筛选。   
    
18. MySql语句集锦：  
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
> 插入一条数据
```java
insert into t2(title,note) select title, 'others' from t1
```
> 查询两表不同的记录
```java
//方法一：
select id, title from (select id,title from t1 union all select id, title from t2) tb group by id,title having count(*) = 1 order by id   //两表数据相同，count(*)=2
select id, title from (select id,title from t1 union all select id, title from t2) tb group by id,title having count(*) = 2 order by id   //两表数据不同，count(*)=1
//方法二：
select id,title from t1 where not exists (select id, title from t2 where t2.id=t1.id and t2.title=t1.title) union all 
select id,title from t2 where not exists (select id, title from t1 where t1.id=t2.id and t1.title=t2.title)
```
> 查询一张表中的重复记录
```java
select email, count(email) as rows from contacts group by email having rows > 1   //重复的email及重复的总数
select first_name,count(first_name) as rows1, last_name, count(last_name) rows2, email, count(email) rows3 from contacts
group by first_name, last_name, email having rows1>1 and rows2>1 and rows3>1;   //多列同时重复的值
```
> 删除有重复记录的行
```java
delete t1 from contacts t1 inner join contacts t2  where t1.id<t2.id and t1.email=t2.email;   //删除重复email的行，并保留最大id的行
```
> 统一数据库中复制表
```java
create table if not exists table_new select * from table_old;   //只复制了表数据和字段
create table table_new select * from table_old where xxx;  

create table table_new like table_old;
insert into table_new select * from table_old;  //复制了表数据、字段、依赖对象(索引，主键，外键)
```
> 正则表达式模糊查询
```java
select productname from products where productName regexp '^(A|B|C)'   //以A、B、C开头
select productName from products where productName regexp 'f$'         //以f结尾
select productName from products where productName regexp 'ford'      //包含了ford
select productName from products where productName regexp '^.{10}$'   //有10个字符
```

19. mySql查询的整个执行过程：
> 客户端向mysql服务器发送一条查询请求  
> 服务器首先检查查询缓存，若命中缓存，则直接将存储在缓存中的结果返回给客户端，否则执行下一条  
> 服务器进行SQL解析，预处理，再由优化器生成执行计划  
> mySql根据执行计划调用存储引擎的接口执行查询  
> 将结果返回给客户端，同时缓存查询结果

20. 关于索引：
> 当索引列是表达式的一部分，或者函数的参数时，mysql不会使用索引
>- `select * from table where id += 10`

> 当多个索引做相交操作时(AND), 一个包含所有列的多列索引要优于多个只含一列的独立索引   
> 当多个索引做联合操作时(OR), 走索引不如走全表扫描   

> 多列索引的顺序对查询至关重要，应把索引选择性高的字段放在前面，以便通过第一个字段就能过滤掉大部分不符合条件的数据     
> 索引选择性=不重复的索引值/总记录值, 唯一索引选择性最高=1  
```java
select * from table where app_id = xx and uid = xx
//计算选择性
select count(distinct uid) / count(*) as uidSelecitivty, count(distinct app_id) / count(*) as appSelectivity from table
//比较两个选择性值，若uidSelecitivty > appSelectivity, 则多列索引为{uid,app_id},而非{app_id,uid}
```
> 查询条件避免多个范围条件，否则mysql只能人选一列的索引，而不能同时使用它们
>- `select * from table where time > '2018-03-20' and age between 10 and 30`

> 覆盖索引是指其包含了所有需要查询的字段，可以极大地提高性能   
>- `select * from table where id=x and name=x and birthday=x` , 覆盖索引为{id,name,birthday} 

> 若已经有一个索引{a, b}, 再去创建一个索引{a}的话，就是冗余索引(相同的列上按相同的顺序创建的相同类型的索引),应该避免    

21. mysql性能优化的三大方面:
（一）数据类型优化     
（二）创建高性能索引    
（三）特定类型查询优化  
> 优化count()查询
```java
count(咧): 统计某一列值的数量，要求列值非空，不会统计NULL
count(*): 统计行数
explain执行计划出来的rows行数就是count()的近似值，对数据精确性要求不高时可代替
```
> 优化关联查询
```java
1. ON子句中的列上最好有索引。 
当关联顺序为 select * from A join B on A.uid = B.uid 时，整个关联查询只会用到B表的索引，不会使用A表的索引，因此，只需在B表的uid上创建索引，加速查询，而不用在A表的uid上创建索引，增加负担。
2. GROUP BY 和 ORDER BY 中的表达式最好只涉及一个表，以便于mysql使用索引进行优化
```
> 优化limit分页
```java
当limit pageNum, pageSize的偏移量pageNum很大时,会导致查询效率低下。
SELECT * FROM table LIMIT 50000, 5;  //需要查询50005条记录，只返回5条
//两种优化方式
select * from table where id >= (select id from table limit 50000,1) limit 5
select * from table t1 join (select id from table limit 50000, 5) t2 on t1.id = t2.id
```
> 优化union查询
```java
尽量避免使用 UNION，除非一定要求结果去重，否则就用 UNION ALL
```

22. 查看mysql版本:
> mysql -V
> status
> select version();

23. mySQL函数:
instr(): 返回子字符串在字符串中第一次出现的位置
```java
select instr("MYSQL BACK","SQL")   //3
select instr("MYSQL BACK", binary "sql")  //0 //binary表示区分大小写
select productName from table where instr(productName, 'car') > 0
//等价于
select productName from table where productName like '%car%'
select produceName from table where productName like '190%' //当模糊条件有前缀时，like的查询效率高于instr
```
substring(): 从特定位置开始提取一个子字符串
```java
select substring("apple pear", 7)   //pear
//等价于
select substring("apple pear" from 7)
select substring("apple pear", 0)  //空字符串
select substring("apple pear", -4)  //pear

select substring('Today is monday', 10, 6)  //monday
//等价于
select substring('Today is monday' from 10 for 6)
select substring('ThisApple', 5, 10)  //Apple
```
trim(): 删除字符串中不需要的字符
```java
select trim('   This is apple   ')
//等价于
select trim(both from '   This is apple   ')   //删首尾空格
select trim(leading from '   This is apple   ')
//等价于
select ltrim('   This is apple   ')   //删首部空格
select trim(trailing from '   This is apple   ')
//等价于
select rtrim('   This is apple   ')   //删尾部空格
```
find_in_set(): 在逗号分隔的字符串列表中查找指定字符串的位置
```java
select find_in_set('C', 'A,B,C')     //3
select find_in_set('D', 'A,B,C')     //0
select find_in_set(NULL, 'A,B,C')   //null
select find_in_set('C', NULL)       //null
select id, name from table where find_in_set('apple', fruits)  //fruits中包含apple
select id, name from table where not find_in_set('apple', fruits)    //fruits中b不包含apple
```
group_concat(): 连接各分组的字符串
```java
select group_concat(distinct province order by province desc separator '>>') from table //四川省>>江西省>>山东省
select group_concat(distinct province order by province desc) from table   //黑龙江省,陕西省,山东省
select user_id, group_concat(distinct app_id order by app_id separator '&') as appIds from table group by user_id   //appId1&appId2&appId3
```
concat(): 将多个字符串组合成一个字符串,concat_ws(): 用分隔符连接字符串
```java
select concat('this is', 'apple')   // this is apple
select concat('abc', 'def', null)   //null
select concat_ws('>>', 'abc', 'def')   //abc>>def
select concat_ws('>>', null, 'abc', null)   //abc
```
replace(): 替换字符串中的子字符串
```java
select replace('15120581234', '2058', '****')  //151****1234
update table set username = replace(username, "lily miss", 'monday') where uid = "1q2w3e4r5t6y"  
```
ifnull(): 若第一个参数不为空，则返回第一个参数，否则返回第二个参数
```java
select ifnull(1, 'apple')     //1
select ifnull('', 999)       //空字符串
select ifnull(null, 'apple')  //apple
```
nullif(): 若第一个参数等于第二个参数，返回null，否则返回第一个参数
```java
select nullif(1, 1)                 //null
select nullif('apple', null)       //apple
select nullif(null, 999)          //null
select nullif('apple',999)       //apple
```
curdate(): 返回当前日期
```java
select curdate()          //2018-04-03
select curdate() + 0      //20180403
select current_date, current_date(), curdate()    //都是2018-04-03
select DATE(now())        //2018-04-03
```
datediff(): 计算两个日期之间相差的天数
```java
select datediff('2018-04-01 12:00:00','2018-04-03 16:00:00')   //-2
select datediff('2018-04-03','2018-04-01')   //2
select round(datediff('2018-04-10','2018-04-03') /7 ,2) as week   //1.00  //ROUND函数用于舍入结果
```
```java
select day('2018-04-03')         //3
select last_day('2018-04-03')    //2018-04-30   //当月的最后一天
select day(last_day('2018-04-01'))    //30
```
date_add(): 加日期, date_sub(): 减日期
```java
select date_add('2018-04-01', interval 1 second)     //2018-04-01 00:00:01
select date_add('2017-01-01', INTERVAL '5 2' HOUR_MINUTE)    //2017-01-01 05:02:00
select date_add('2018-04-01', interval '1 1' MINUTE_SECOND)  //2018-04-01 00:01:01
select date_add('2018-04-01', interval '-1.2' day_hour)    //2018-03-30 22:00:00
select date_add('2018-04-01', interval '-1 2' day_hour)    //2018-03-30 22:00:00
select date_add('2018-02-30', interval 10 day)    //null  //第一个参数为无效日期
select date_add('2018-01-30', interval 1 month)   //2018-02-28   //超过了新日期范围
select '2018-04-03' + interval 1 day    //2018-04-04
select '2018-04-03' + interval -2 month    //2018-02-03
```
dayname(): 返回对应的日期
```java
select @@lc_time_names  //en_US
select dayname('2018-04-01')   //Sunday
set @@lc_time_names = 'zh_CN'
select dayname('2018-04-01')  //星期日
```
extract(): 提取日期的一部分
```java
select extract(day from '2018-04-03 10:11:52')        //3
select extract(day_hour from '2018-04-03 10:11:52')        //310
select extract(day_minute from '2018-04-03 10:11:52')      //31011
select extract(hour_second from '2018-04-03 10:11:52')     //101152
select extract(second_microsecond from '2018-04-03 10:11:52')   //52000000
```
timediff(): 计算两个日期之间的差值, timestampdiff(): 计算两个日期之间的差值
```java
select timediff('12:00:00', '10:00:00')         //02:00:00
select timediff('2018-04-11 01:00:00', '2018-04-12 01:00:00')   //-24:00:00
select timediff('2018-04-01', '2018-04-01 10:00:00')   //null  //两个时间的类型必须一致 
select timediff('2018-04-01 01:00:00', '2018-02-01 01:00:00')    //838:59:59    //该函数有范围限制，最大差值为838:59:59

select timestampdiff(hour, '2018-04-01', '2018-02-01')    //-1416
select timestampdiff(minute, '2018-04-03 10:00:00', '2018-04-03 17:20:50')   //440
```
coalesce(): 返回第一个非NULL参数
```java
select coalesce(null, null, 'apple', null)    //apple
```
greatest(): 返回最大值, least(): 返回最小值
```java
select greatest(10, 'apple')     //10
select greatest('banana', 'apple')    //banana
select greatest(5, null, 'abc')   //null
select least(5, null, 'abc')   //null
select least(10, 'apple', 6)   //0 
select least('banana', 'apple')   //apple
```
isnull(): 若参数为null，返回1，否则返回0
```java
select isnull(null)   //1
select isnull(100)   //0
```

24. mySQL用户账户管理:
> 创建用户(只是创建一个没有任何权限的新用户帐户)
>- 用户帐号由用户名，以及使用@字符分隔的主机名组成
```java
create user_account identified by password
create user hyh@localhost identified by '123456';       //user=hyh,host=localhost
create user 'momo@localhost' identified by '12345678'   //user=momo@localhost,host=%
create user hyh@% identified by '123456';      //user=hyh host=%
create user hyh;     //user=hyh host=%
create user 'haha'@'localhost' identified by '12345678'  //user=haha,host=localhost
```
> 查看用户权限
```java
show grants for user_account
show grants for hyh@localhost 或者 show grants for 'hyh'@'localhost'
```
> 授予用户权限
>- privileges包含:all, alter, create, delete, drop, event, index, insert, select, update, usage(表示没有任何权限)等。
```java
grant privileges on database.table to user_account [identified by password] [with grant option]
//全部权限，全部数据库的全部表，with grant option表示允许用户有权授予或撤销其他帐户的权限
grant all on *.* to hyh@localhost with grant option  
grant SELECT,UPDATE,INSERT on user-center.* to hyh@localhost identified by '123'  //查，改，插
```
> 撤销用户权限:
```java
revoke privileges on database.table from uer_account
revoke all privileges,grant option from hyh@localhost
revoke update,select on user-center.* from hyh@localhost
```
> 重置密码:
```java
//方法1
set password for user_account = password('xxx')
set password for hyh@localhost = password('123456')
//方法2
update user set password='123456' where user='hyh' and host='localhost'
flush privileges
```
> 删除用户:
```java
drop user user_account
drop user 'hyh'@'localhost'
drop user 'momo@localhost'
```
> 查询用户
```java
select user from mysql.user
select user()
select current_user()
//所有进程
show processlist
```
## 六、Maven常用语句 
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
mvn install:install-file -Dfile=spring-cloud-dependencies-.pom -DgroupId=org.springframework.cloud -DartifactId=spring-cloud-dependencies -Dversion=Edgware.SR3 -Dpackaging=pom

mvn install:install-file -Dfile=spring-cloud-dependencies.pom -DgroupId=org.springframework.cloud -DartifactId=spring-cloud-dependencies -Dversion=Edgware.SR3 -Dpackaging=jar
```

## 七、单元测试Junit
> (1) 测试方法用@Test注解，public void 修饰        
> (2) @BeforeClass和@AfterClass只执行一次，在所有方法都被调用之前/后            
> (3) @Before和@After在每个方法被调用前/后执行          
 
## 八、linux常用命令  
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
         
## 九、docker基础   
docker是一个开源的容器引擎，基于go语言，可以让开发者打包他们的应用及依赖包到一个轻量级、可移植的容器中。          
容器与镜像的关系：前者相当于对象实例是可以读写的，后者相当于类，只能读。先声明镜像，在创建容器。一个镜像可以有多个容器。

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
> docker inspect -f '{{.Name}} {{.NetworkSettings.IPAddress}}' ContainerName/Id
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
>-- 获取容器主机名   

>- root@xxxxxx:/# cat /etc/hosts    
>-- 打开文件，可看到<IP地址：该容器Id>配置项  

>- root@xxxxxx:/# ip a      
>-- 查看该容器的网络配置

>- root@xxxxxx:/# ps -aux   
>-- 查看容器中运行的进程

>- root@xxxxxx:/# apt-get && apt-get install <软件名>   
>-- 在容器中安装软件

>- root@xxxxxx:/# exit  
>-- 退出shell，也停止容器
  
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
5. docker查看日志
```java
1. docker inspect <容器>
//找到logPath
2. sudo sl -l logPath  //查看总日志数量
3. sudo grep 'XXX' logPath
4. grep 'XXX' info.log
5. vi info.log
``` 

## 十、Tomcat虚拟目录映射方式
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

## 一、Linux下文件权限设置
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
4. 修改文件的拥有者
chown <账号名称> <文件/目录>
```java
//修改前
drwxr-xr-x   2 root  wheel    68  4 10 14:20 data
//CH-yfy@YuanFayangs-iMac opt$sudo chown CH-yfy data/ 
//修改后
drwxr-xr-x   2 CH-yfy  wheel    68  4 10 14:20 data
```

## 二、Nginx简单配置负载均衡  
1. 启动退出nginx:
```java
//1.启动
cd /usr/local/nginx/sbin
./nginx
//2.启动,进入部署目录
start nginx

//停止,进入部署目录
nginx -s stop
//退出,进入部署目录
nginx -s quit

//修改后重载
cd /usr/local/nginx
nginx -s reload
```
2. 配置负载均衡
负载均衡服务器C0: 外网=www.user.com, 内网=192.168.1.0     
web服务器1C1: 内网=192.168.1.1    
web服务器2C2: 内网=192.168.1.2   
web服务器3C3: 内网=192.168.1.3  

```java
//C0的配置文件nginx.conf
upstream www.mylocal.com {
    server 192.168.1.1:8081
    server 192.168.1.2:8082
    server 192.168.1.3:8083
}
server {
    listen: 80;
    server_name: www.user.com
    location / {
      proxy_pass http://www.mylocal.com;  //反向代理的地址
      proxy_set_header Host $host;   //主机头
      proxy_set_header X-Real-IP $remote_addr;   //客户端真实地址
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;   //客户端真实ip
    }
}

//C1的配置
server{ 
    listen 8081; 
    server_name www.user.com; 
    index index.html;
}
//C2的配置
server{ 
    listen 8082; 
    server_name www.user.com; 
    index index.html;
}
//C3的配置
server{ 
    listen 8083; 
    server_name www.user.com; 
    index index.html;
}
```

## 三、前后端分离跨域问题解决
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

## 四、关于"==",equals()和hashCode
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

## 五、 关于jar包
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

## 六、关于Prometheus，Grafana, influxDB
### Prometheus:(pull型时间序列数据库)
Mac下载的发行版为darwin版
1. 启动prometheus
```java
tar -zxvf prometheus-2.2.1.darwin-amd64.tar.gz  //解压
cd <prometheus目录>
./prometheus  或者 ./prometheus --config.file=prometheus.yml  //运行
```
2. 访问 `http://localhost:9090` 即进入管理界面
3. 修改配置文件prometheus.yml
```java
//在最后添加
- job_name: mysql
  static_configs:
    - targets: [‘127.0.0.1:9104’]  //mysql对应的ip，mysqlId_exporter的默认监听端口
```
4. 重启prometheus,访问 `http://localhost:9090/targets`
```java
http://localhost:9090/metrics  //status是up

http://127.0.0.1:9104/metrics  //status是down，因为还没配置mysqlId_exporter
```
5. 退出为`exit`

#### 安装mysqlId_exporter
同prometheus的地址和版本
1. 配置文件.my.cnf
```java
vi .my.cnf
//内容为:
[client]
user=root
password=123456
```
> 如果要连接新的mysql账户，需要先连接mysql创建用户并授权
```java
//查看用户授予的权限
mysql> show grants for 'user-center';

mysql> GRANT REPLICATION CLIENT,PROCESS ON *.* TO 'apple'@'localhost' identified by '123456';
mysql> GRANT SELECT ON *.* TO ‘root’@‘localhost';
```
2. 启动
```java
./mysqld_exporter -config.my-cnf=".my.cnf"
```
3. 访问 `http://localhost:9104`
4. 刷新 `http://localhost:9090/targets`, status由down变为up

### Grafana
配置文件地址:
/usr/local/opt/grafana/share/grafana/conf/default.ini
/usr/local/etc/grafana/grafana.ini
1.安装
```java
brew update    //若无反应则执行:cd "$(brew --repo)" && git fetch && git reset --hard origin/master && brew update
brew install grafana
```
2. 启动：
`brew services start grafana`
3. 访问`http://localost:3000`, 进入管理界面，默认账号密码admin+admin
4. 配置数据源(name=influxdb / Prometheus)
5. 新建或引入dashboard即可
6. 停止为`brew services stop grafana`

### influxDB:(push型时序数据库)
安装位置：/usr/local/opt/influxdb
配置文件地址：/usr/local/etc/influxdb.conf
1. 安装启动
```java
brew update
brew install influxdb
brew services start influxdb
```
2. 访问 `http:localhost；8083` 进入管理界面 或者 命令行输入 `influx` 进入命令行界面
3. 增删改查
```java
//命令行格式：
//tag的值有空格时用\+空格，tag的value必须是String，field的value必须是数字(可以是Integer<数字后+i>，float，boolean)
insert <表名>[,<tag-key>=<tag-value>...] <field-key>=<field-value>[,<field2-key>=<field2-value>...] [unix-nano-timestamp]
//url格式：
curl -i -XPOST 'http://127.0.0.1:8086/write?db=<数据库名>’ --data-binary ‘<表名>[,<tag-key>=<tag-value>...] <field-key>=<field-value>[,<field2-key>=<field2-value>...] [unix-nano-timestamp]'

//url格式查询
curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=<数据库名>” --data-urlencode “<sql查询语句>”

//删除表
delete from <表名>

//查看数据保留策略
show retention policies on <数据库名>
//创建,必须双引号
create retention policy "<策略名>" on "<数据库名>" duration 4d replication 1 default  
//修改，单位可以是w, h, d 
alter retention policy "<策略名>" on "<数据库名>" duration 5d default 
//删除
drop retention policy "<策略名>" on "<数据库名>"

//新建用户并开通权限,注意单引号和双引号
create user “<用户名>” with password ‘<password>’ with all privileges  
//删除用户
drop user "<用户名>"
```
4. 退出用 `exit`





