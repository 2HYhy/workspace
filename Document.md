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

> **iterm重新打开一个页面:** 
> - `commond + N`

> **iterm在原来的页面里打开新的页面:**
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

3. 获取浏览器cookie的方式   
> 在控制台输入:javascript:alert(document.cookie);按回车即可.

### Markdown语法
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
 
   
1. 电脑上的数据有三种存储方法：外存，内存，缓存。存储量：外存>内存>缓存；数据读取速度：缓存>内存>外存。将数据从外存读取到内存的称为输入流，将数据从内存写入外存的称为输出流。  

2. 标注于类上，字段的值为空时，不参与序列化，不在返回结果中显示：  `@JsonInclude(JsonInclude.Include.NON_NULL)`。   
标注于类上，字段的值为空时，参与序列化，仍在返回结果中显示：`@JsonInclude(JsonInclude.Include.ALWAYS)`。    

3. Java代码访问HTTP网页
```java
// 方法一
public class TestUrl {
  public static void main(String [] args) throws URISyntaxException, IOException {
    java.net.URI uri = new java.net.URI("http://www.baidu.com");
    java.awt.Desktop.getDesktop().browse(uri);
  }
}

// 方法二
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
OkHttpClient client = new OkHttpClient();

//默认是GET请求
Request request = new Request.Builder().url("your url").build();
//POST请求
MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
Request request = new Request.Builder().url("your url").post(RequestBody.create(mediaType, "your String body")).build();

Response response = client.newCall(request).execute();
JSONObject jsonObject = JSONObject.parseObject(response.body().string());

//方法三
CloseableHttpClient httpclient = HttpClients.createDefault();

//GET请求
List<NameValuePair> params = new ArrayList<>();     //有多个参数时，可以创建参数队列      
params.add(new BasicNameValuePair("key", "value"));        
try {
    //将请求参数转为字符串
    String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
    HttpGet httpget = new HttpGet("your url" + "?" + paramsStr);
    //请求头
    httpget.setHeader("key1", "value1");
    CloseableHttpResponse response = httpclient.execute(httpget);
    System.out.println("响应码是: " + response.getStatusLine());
    //获取响应内容
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      System.out.println("响应内容是: " + EntityUtils.toString(entity);
    }
    //释放资源
    response.close();
    httpclient.close();
} catch (Exception ex) {
    ex.printStackTrace();
}
//POST请求
JSONObject jsonObject = new JSONObject();    //创建参数字符串,用于封装请求报文
jsonObject.put("name", name);
jsonObject.put("appKey", appKey);
jsonObject.put("secretKey", secretKey);
jsonObject.put("description", description);
try {
    //设置请求报文格式
    StringEntity body = new StringEntity("your String body", ContentType.APPLICATION_JSON);
    HttpPost httppost = new HttpPost("your url");
    httppost.setHeader("key1", "value1");
    httppost.setEntity(body);
    CloseableHttpResponse response = httpclient.execute(httppost);
    System.out.println("响应码是: " + response.getStatusLine());
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        return new Result(EntityUtils.toString(entity));
    }
    //释放资源
    response.close();
    httpclient.close();
} catch (Exception ex) {
    ex.printStackTrace();
}
```

4. JavaScript的parseInt(string, radix)函数    
> string是要解析的字符串，radix是要解析的数字的基数，介于2-36之间。      
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

5. springmvc
> (1) 使用@Autowired注解，应用处格式为：首字母小写的bean类.方法名,方法不必静态化  
> (2) 不用@Autowired注解，应用处格式为：首字母大写的bean类.方法名，方法必须静态化    
> (3) Dao层与mapper.xml文件的参数传递    
```java 
dao: void func(String param1,Integer param2); 
mapper: select * from table where pam1 = #{0} and pam2 = #{1} 
dao: void func(@Param(pam1)String param1,@Param("pam2")Integer param2)
mapper: select * from table where pam1 = #{pam1} and pam2 = #{pam2} 
```
   
6. 单元测试Junit
> 测试方法用@Test注解，public void 修饰        
> (2) @BeforeClass和@AfterClass只执行一次，在所有方法都被调用之前/后          
> (3) @Before和@After在每个方法被调用前/后执行          
 
7. linux常用命令  
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
         
8. linux下文件权限
> linux下，文件的权限有读(r)，写(w)，可执行(x)三种，文件访问的用户类别有创建者(owner),与创建者同组用户(groups)，其他用户(others)三种。

> 文件的权限由10个字符组成:         
第1个字符的含义为: "d" 表示目录, "-" 表示文件, 还有"l, "b", "c"。       
其余的9个字符，分为三组，第一组代表创建者的权限，第二组代表同组用户的权限，第三组代表其他用户的权限。每组三个字符，均为”rwx“三个参数的组合且顺序是固定的。如果没有某个权限，就用"-"代替。  

> 文件权限的修改命令是chmod   
> (1) 用数字组合表示权限：`r=4, w=2, x=1`       
>- 文件的权限为”-rwxrwx-w-“时，owner=4+2+1=7, groups=4+2+1=7, others=0+2+0=2, 所以文件的权限数字就是772。 用命令执行即: `chmod 772 mytest.sh` 

> (2)用字母字符表示权限：     
> `owner=u, groups=g, others=o, all(全部用户)=a`, `+(添加权限)， -(减少权限)`      
>- 已知文件的权限为”-rwxrwxrwx"，要删除所有用户的可执行权限为`chmod a-x mytest.sh`, 要删除其他用户的写权限为`chmod o-w mytest.sh`  

> 修改文件的拥有者(只有root用户才有权限，因此有时需要加上sudo)
chown <账号名称> <文件/目录>
  ```java
  //修改前
  drwxr-xr-x   2 root  wheel    68  4 10 14:20 data

  执行 CH-yfy@YuanFayangs-iMac opt$sudo chown CH-yfy data/  

  //修改后
  drwxr-xr-x   2 CH-yfy  wheel    68  4 10 14:20 data
  ```        

9. Tomcat虚拟目录映射方式   
web应用开发好之后，若想供外界访问，需要把web应用所在目录交给web服务器管理，这个过程称之为虚拟目录的映射。      
A: 在server.xml文件的host元素中配置：  
(1)`<Host></Host>`这对标签中添加`<Context path = "/demoapps" docBase = "F:\DemoProject"/>`即可将F盘的DemoProject应用映射到demoapps这个虚拟目录上，它是磁盘上不存在的目录，是我们自己定义的。*path必须以"/"开头； docBase对应JavaWeb项目的所在目录。*      
(2)浏览器访问：`http://localhost:8080/demoapps/demo.jsp`    
B: tomcat服务器自动映射：      
(1)tomcat服务器会自动管理webapps目录下的所有web应用，并把它映射成虚拟目录，所以直接将F盘下的DemoProject应用放在webapps文件夹下。  
(2)浏览器访问： `http://localhost:8080/DemoProject/demo.jsp` 。    
C: 添加xml文件：  
(1)在conf/Catalina/localhost目录下添加一个以xml为扩展名的demo.xml文件，文件中添加`<Context docBase = "F:\DemoProject"/>`。  
(2)浏览器访问： `http://localhost:8080/demo/demo.jsp`,这里的虚拟目录就是文件名。  
> 查看tomcat日志: tail -f catalina.out  
> 查看tomcat版本信息: cd Documents/tools/tomcat/bin 运行`./version.sh`

10. 前后端分离跨域问题解决   
> 浏览器发送请求的url的协议(http,https)，域名，端口，任一与当前页面地址不同，即为跨域。
> 常用的跨域方案有JSONP(json with padding)和CORS(cross origin resource sharing)。前者只支持get的提交，后者支持get和post请求，但对客户端浏览器的版本有要求。           
> CORS跨域请求原理: 当浏览器发现发送的请求不符合同源策略时，会给该请求加一个请求头Origin，后台如果确定接受该请求，就在返回结果中杰瑞个响应头Access-Control-Allow-Origin。浏览器判断响应头中是够包含Origin的值，有就会处理，能拿到数据，没有就直接驳回，拿不到数据。

> 后端过滤器filter: 
```java
public void doFilter(ServletRequest req, ServletResponse res,  FilterChain chain) throws IOException, ServletException {  
    HttpServletResponse response = (HttpServletResponse) res;  
    // 指定允许其他域名访问
    response.setHeader("Access-Control-Allow-Origin", "*");  
    // 允许的请求方法
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
    // 相关配置的缓存
    response.setHeader("Access-Control-Max-Age", "3600");  
    // 服务器支持的所有头信息字段，用逗号分隔
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Accept"); 
    // 不支持的头信息字段
    response.setHeader("Access-Control-Expose-Headers", "");
    // 是否支持跨域cookie
    response.setHeader("Access-Control-Allow-Credentials", "true");
    chain.doFilter(req, res);  
}  
```
> 后端拦截器interceptor:
```java
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //添加跨域CORS
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
    response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
    return true;
  }
```

#### 关于jar包
1. 打成jar包/解压jar包:
```java
mvn clean install  //即根据pom文件，打成jar或war包
E:\myproject\> jar cvf project.war */ .  //命令行进入该项目的文件夹执行
E:\> jar -cvf myproject.war myproject   //进入该项目的父文件夹执行
jar -xvf myjar-4.0.1-SNAPSHOT.jar  //解压jar包
```
2. 创建jar文件：
```java
jar cf test.jar testDir  //当前路径下的testDir目录下生成一个test.jar文件
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
jar xvf test.jar   //带提示信息的解压缩
```
#### 关于重定向和转发
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





