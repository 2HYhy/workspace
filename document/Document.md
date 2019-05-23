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
5. $ `: wq`，保存文件,`!q` 为不保存   
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

3. cookie的处理方式   
> 前端: javascript:alert(document.cookie);按回车即可.
> 后端: Cookie[] cookies = req.getCookies();
> 浏览器端获取cookie: document.cookie
> 浏览器端设置cookie: document.cookie="name=apple"
> 服务器端向浏览器写入cookie: response.addCookie(Cookie实例);

4. Markdown语法
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
 
> 关于插入图片：
![alt-text](/images/redis2.png)  
   
5. 电脑上的数据有三种存储方法：外存，内存，缓存。存储量：外存>内存>缓存；数据读取速度：缓存>内存>外存。将数据从外存读取到内存的称为输入流，将数据从内存写入外存的称为输出流。  

6. 标注于类上，字段的值为空时，不参与序列化，不在返回结果中显示：`@JsonInclude(JsonInclude.Include.NON_NULL)`。   
标注于类上，字段的值为空时，参与序列化，仍在返回结果中显示：`@JsonInclude(JsonInclude.Include.ALWAYS)`。    

7. Java代码访问HTTP网页
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

8. JavaScript的parseInt(string, radix)函数    
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

9. springmvc
> (1) 使用@Autowired注解，应用处格式为：首字母小写的bean类.方法名,方法不必静态化  
> (2) 不用@Autowired注解，应用处格式为：首字母大写的bean类.方法名，方法必须静态化    
> (3) Dao层与mapper.xml文件的参数传递    
```java 
dao: void func(String param1,Integer param2); 
mapper: select * from table where pam1 = #{0} and pam2 = #{1} 
dao: void func(@Param(pam1)String param1,@Param("pam2")Integer param2)
mapper: select * from table where pam1 = #{pam1} and pam2 = #{pam2} 
```    
 
10. linux常用命令  
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

> kill -9 PID     
>- 强制杀死该标识所代表的进程

> CH-yfy@Yuans-iMac ~$ cd /data/    
>- pwd = /data, 即当前所在路径

> CH-yfy@Yuans-iMac ~$ cd data/    
>- pwd = CH-yfy@Yuans-iMac/data, 即当前所在路径

> ps -ef | grep <进程名> 
>- 查看某进程运行信息

> ifconfig
>- 查看激活网卡的详细信息,eth/en代表以太网网卡信息（ipv4,ipv6,broadcast,mask)

> ps -A / ps -e
>- 查看所有进程   

> lsof -i tcp:port  /  lsof -i:port
>- 查看端口占用

> ping <域名>
>- 检查网络是否通畅或者网络连接速度的命令

> telnet <ip> <port>
>- 测试目标机器的3389端口是否开放, 然后按住ctrl和]键就可以执行telnet命令(close,quit等)

> top  
>- 查看系统的CPU、内存、运行时间等信息
         
11. linux下文件权限
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
>- chown <账号名称> <文件/目录>
  ```java
  //修改前
  drwxr-xr-x   2 root  wheel    68  4 10 14:20 data

  命令行执行: sudo chown CH-yfy data/  

  //修改后
  drwxr-xr-x   2 CH-yfy  wheel   68  4 10 14:20 data
  ```        

12. Tomcat虚拟目录映射方式   
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

13. 前后端分离跨域问题解决   
> 浏览器发送请求的url的协议(http,https)，域名，端口，任一与当前页面地址不同，即为跨域。
> 常用的跨域方案有JSONP(json with padding)和CORS(cross origin resource sharing)。前者只支持get的提交，后者支持get和post请求，但对客户端浏览器的版本有要求。           
> CORS跨域请求原理: 当浏览器发现发送的请求不符合同源策略时，会给该请求加一个请求头Origin，后台如果确定接受该请求，就在返回结果的响应头中加上Access-Control-Allow-Origin。浏览器判断响应头中是否包含Origin的值，有就会处理，能拿到数据; 没有就直接驳回，拿不到数据。

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

14. 关于jar包
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
15. 关于重定向和转发
请求转发是服务器行为，浏览器只做了一次访问请求; 请求重定向是客户端行为，浏览器做了至少两次的访问请求。    
##### 转发过程：
> 客户浏览器发送http请求
> web服务器接受此请求
> 调用内部的一个方法在容器内完成请求的处理和转发动作
> 将目标资源返回给客户
转发路径必须是同一个web服务器下的url,浏览器路径栏显示的是首次访问的地址，客户感觉不到服务器做了转发。

##### 重定向过程:
> 客户浏览器发送http请求
> web服务器接受后发送302响应码和新的location给客户浏览器
> 客户浏览器根据新的location，再自动发送一个新的http请求
> 服务器根据此请求url寻找资源并返回给客户。
客户浏览器路径栏显示的是重定向的地址，用户可以观察到地址的变化。

16. nginx
主要用作反向代理和负载均衡。

反向代理是指以代理服务器来接收Internet上的连接请求，然后将请求转发给内部网络上的服务器；并将从服务器上得到的结果返回给Internet上请求连接的客户端，此时代理服务器对外就表现为一个服务器。   

HTTPS的固定端口号是443，不同于HTTP的80端口。

##### 相关操作命令:
```java
cd /usr/local/nginx/sbin
sudo ./nginx   //启动(sudo nginx)
sudo ./nginx -s stop    //停止，无日志
sudo ./nginx -s quit    //停止，有日志
sudo ./nginx -s reload  //重新加载

cd /usr/local/nginx/logs
cat nginx.pid    //启动后查看主进程号，nginx 启动后有 master 和 worker 进程

ps -ef|grep nginx   //查看与nginx相关的进程信息
/**
  501 27493     1   0  2:47下午 ??         0:00.00 nginx: master process nginx
  501 27494 27493   0  2:47下午 ??         0:00.00 nginx: worker process
  501 27496 14904   0  2:47下午 ttys003    0:00.00 grep --color=always nginx
*/
kill -quit <进程号>   //停止nginx服务，eg: kill -quit 27493， 是要“nginx:master”的那个进程号
lsof -i:80    //查看80端口是否被正确监听，出现HTTP(LISTEN)
```

##### nginx单台服务器反向代理
for example: 本地服务器想用www.portal.com域名访问192.168.1.108:8081服务器上的接口。
1. 本地host解析
```java
127.0.0.1   www.portal.com
```
2. nginx.conf配置文件
```java
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       80;
        server_name  www.portal.com;

        location /hyh/ {
            proxy_pass http://192.168.1.108:8081/;
            proxy_buffer_size 64k;
            proxy_buffers 4 64k;
            proxy_redirect  off;
            proxy_set_header  Host $host;
            proxy_set_header  X-Real-IP  $remote_addr;
            proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header  X-Real-Port $remote_port;
        }

        location / {
            root   html;
            index  index.html index.htm;
        }

        location /test/ {
          root  /Users/Home/data;   #去/Users/Home/data/test/目录下寻找资源
          alias /Users/Home/data/;  #去/Users/Home/data/目录下寻找资源，必须以/结尾
        }
   }
}
```
3. 超级管理员启动nginx, 访问http://www.portal.com/hyh/xxxxxx即可。

##### nginx多台服务器负载均衡
for example: 某项目部署在不同的服务器上，想通过统一的域名来进行访问。
1. 本地host解析
```java
127.0.0.1   www.portal.com
```
2. nginx.conf配置文件
```java
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    upstream eureka_server {
         server  192.168.1.108:9001;
         server  192.168.1.108:9002;
    }

    server {
        listen       80;
        server_name  www.portal.com;

        location / {
            root   html;
            index  index.html index.htm;
        }

        location /hyh/ {
            proxy_pass http://eureka_server/;
            proxy_buffer_size 64k;
            proxy_buffers 4 64k;
            proxy_redirect  off;
            proxy_set_header  Host $host;
            proxy_set_header  X-Real-IP  $remote_addr;
            proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header  X-Real-Port $remote_port;
      }
   }
}
```
3. 启动nginx,访问http://www.portal.com/hyh/xxxxxx即可。

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

17. 数据库(事务)四大特性(ACID)            
> 事务是用户定义的一个数据库操作序列。在关系数据库中，一个事务可以是一条SQL语句，也可以是多条SQL语句。用于保证数据的一致性和完整性。查询操作不会对数据库内容进行更改，所以不必开启事务。

> 1. 原子性(Atomicity): 事务包含的所有操作要么全部成功，要么全部失败回滚。

> 2. 一致性(Consistency): 事务必须使数据库从一个一致性状态变换到另一个一致性状态。

> 3. 隔离性(Isolation): 多个用户并发访问数据库时，一个事务不能被其他事务的操作干扰，要相互隔离。

> 4. 持久性(Durability): 一个事务一旦被提交了，对数据库中数据的改变就是永久性的，即使数据库系统遇到故障也不会丢失。

> 当多个事务同时操作数据库中的数据时(即事务的并发操作)，如果不考虑隔离性，就会带来以下三个问题:

> 1. 脏读
>- 事务A读取了正在处理当中，还未提交的事务B中的数据。

> 2. 不可重复读(对应修改操作)
>- 事务A多次查询得到了不同的数据值，由于事务B在其查询间隔进行了修改并提交。即读取了前一个事务提交的数据。

> 3. 虚读/幻读(对应插入操作)
>- 事务A对数据全部修改后，事务B又插入了一条数据，事务A再去查询时，以为之前没有修改到。也是读取了前一个事务提交的数据。

##### mysql定义的四种隔离级别
> Serializable,可串行化。能解决脏读，不可重复读，虚读。           
> Repeatable Read, 可重复读。能解决脏读，不可重复读。就是在开始读取数据(事务开启)时，不再允许修改操作。              
> Read Committed, 读已提交。能解决脏读。就是一个事务要等另一个事务提交后才能读取数据。                      
> Read UnCommitted, 读未提交。什么都不能保证。 就是一个事务可以读取另一个未提交事务的数据。            

> 以上四种，级别由高到低排列，级别越高，执行效率越低。mysql支持4种级别，默认的是Repeatable Read; oracle支持Serializable和Read Committed2种级别，默认的是Read Committed。    

> autocommit是事务自动提交的标志，默认为on(开启状态)。即每一条单独的查询都是一个事务，自动开始，执行完毕后自动提交。off为关闭状态，即执行语句后，仍处于一个事务中，直到执行commit或者rollback，才会结束当前事务，重新开始新的事务。

18. 数据库连接池
> 是指将数据库连接作为对象存储在内存中，当用户需要访问数据库时，不是建立一个新的连接，而是从连接池中取出一个已建立的空闲连接对象。用户使用完后，不是将连接关闭，而是将连接放回连接池中，以供下一个请求访问使用。而连接的建立、断开都由连接池自身来管理。  

> 常见数据库连接池有druid，c3p0，dbcp。    
> 基本配置:   
![Alt-text](/images/jdbc_cruial.png) 

> 关键配置:
![Alt-text](/images/jabc_base.png) 

19. Spring
##### IOC(控制反转)和DI(依赖注入)    
> 控制反转涉及3个概念: 应用对象，IOC容器，应用对象所依赖的外部对象         
> 控制反转是指将对象的创建、初始化等一系列工作交给Spring容器。即容器将控制对象的生命周期和对象间的关系。(以前，当一个应用对象A需要引用/依赖另一个对象B时，需要自己去创建和销毁B，B的一切由A控制；现在，所有的对象A和B都交由Spring进行控制。)          
> 依赖注入是指Spring向应用对象提供其所需要的外部对象。(应用对象需要某个依赖，不需要自己去实现，只要告诉Spring，Spring准备好该依赖后，送给应用对象。) 
> IOC是从容器的角度说，之前要手动在程序里创建的对象，现在由容器自己去创建；DI是从应用程序的角度说，程序将需要的对象告诉容器，待其创建好后直接返回给自己，程序无需知道该对象是如何创建的。

##### Bean
> 我们负责开发和配置Bean，Spring容器负责创建Bean。Spring能创建出什么样的Bean，取决于我们在配置文件中的配置。 
> 容器中bean常用的作用域:
>- singleton: 整个容器中只有该bean只有一个实例，默认作用域。Spring负责监测bean的状态，维护bean的生命周期行为。                 
>- protitype: 每次通过容器的getBean方法，都会产生一个新的bean实例。Spring只负责bean的创建。     

> 有三种配置方式: 1. 基于XML(`<bean id="",class="">`)； 2. 基于注解(`@Repository,@Service,@Controller`)； 3. 基于Java类(`@Configuration,@Bean`)

20. 集中式和分布式
互联网架构设计的五大要素：高性能、高可用、可伸缩性、可扩展性、安全。   

> 集中式系统: 由一台或多台计算机组成中心节点，整个系统的所有业务单元都集中部署在该中心节点上。每个终端或客户端只需要负责数据的输入和输出，数据的存储、控制、处理完全交由主机完成。

> 分布式系统: 多台计算机在空间随意分布，可能放在不同的机房，不同的城市。没有主从之分，具有对等性。各个计算机之间通过传递消息进行通信及协调行动。     
>- 副本是分布式系统对数据和服务提供的一种冗余方式。数据副本是指在不同的节点上持久化同一份数据，一旦某个节点上存储的数据丢失时，可以从副本上读到该数据；服务副本是指多个节点提供同样的服务，每个节点都有接收并处理外部请求的能力。

> 集中式缓存: 缓存集中在一台服务器/一台服务器的一个内存条/一台服务器的一个硬盘上。     
>- 局限:(1)节点的内存和磁盘容量是有限的，随着缓存数据的增加，将无法满足; (2)节点的访问量是有上限的，随着业务数据的增加，将无法满足; (3)若节点数据没有备份，一旦遇到宕机、重启等情况，数据便会丢失，即单点故障。    

> 分布式缓存: 缓存集中在不同服务器/一台服务器的不同内存条/一台服务器的不同硬盘上。
>- 优势: (1)高性能(将高速内存作为数据对象的存储介质，解决了大规模数据放量时，响应延迟的问题); (2)动态扩展性(可通过动态地增加或减少节点应对变化的数据访问负载，提高资源利用率); (3)高可用性(无单点故障问题，不会因为一台服务器故障导致缓存服务中断或数据丢失)。    

> 本地缓存: 数据存储在应用代码所在的内存空间，快速访问数据，但数据无法分布式共享。   







