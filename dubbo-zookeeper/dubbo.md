## Dubbbo简介
Dubbo是一个分布式服务框架，采用全Spring配置方式，架构由五部分组成：   
> Provider: 暴露服务的服务提供方     
> Consumer: 调用远程服务的服务消费方     
> Registry: 服务注册与发现的注册中心     
> Monitor: 统计服务的调用次数和时间的监控中心      
> Container: 服务运行容器   
1. 服务容器负责启动、加载、运行服务提供者；  
2. 服务提供者在启动时，向注册中心注册自己提供的服务；  
3. 服务消费者在启动时，向注册中心订阅自己所需的服务；  
4. 注册中心返回服务提供者地址列表给消费者；  
5. 服务消费者从地址列表中，基于软负载均衡算法，选一台提供者进行调用；   
6. 服务消费者和提供者在内存中累计点击调用次数和时间，定时每分钟发送一次到监控中心。   

## 搭建dubbo+zookeeper框架  
(一)dubbo控制台源码准备：     
1. dubbo控制台源码下载：[:https://github.com/alibaba/dubbo](https://github.com/alibaba/dubbo)，重点关注dubbo-admin模块。  
2. 由于源码依赖有些失效，所以先做以下修改：  
**dubbo-parent的pom文件**  
```java 
cd dubbo
git clone https://github.com/alibaba/opensesame.git
cd opensesame
mvn install
 ```      
```java
修改opensesame的pom文件，注释掉repositories，distributionManagement，pluginRepositories这些标签，还得注释dubbo-parent的pom.xml中的repositories标签。
```  
```java
修改dubbo-parent的pom.xml的
<fastjson_version>1.1.8</fastjson_version>
为
<fastjson_version>1.1.15</fastjson_version>
```
3. 进入dubbo-parent目录路径，运行`mvn clean install -Dmaven.test.skip=true`,编译成功，得到target文件夹下的war包。     
4. 进入dubbo-admin目录路径，对pom文件做以下修改： 
```java
1. webx的依赖改为3.1.6版;  
2. 添加velocity的依赖; 
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.7</version>
</dependency>  
3. 对依赖项dubbo添加exclusion，避免引入旧spring;
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>dubbo</artifactId>
    <version>${project.parent.version}</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework</groupId>
            <artifactId>spring</artifactId>
        </exclusion>
    </exclusions>
</dependency>
4. 注释掉dubbo-admin里面原有的spring依赖; 
5. 修改<build>中的<port>为8080以外的端口，eg:8090 。
```  
(二)zookeeper注册中心准备：     
1. 配置文件修改   
2. 启动zookeeper，进入bin目录，运行：`sh zkServer.sh start `(mac操作)，或者双击`zkServer.cmd`(windows操作)，不要关掉窗口。  

(三)tomcat启动准备
**两种方式：**
> (方式一)
1) 进入conf/server.xml文件，修改端口为8090，避免与zookeeper冲突，其默认端口也是8080。
2) 将dubbo-admin模块下的war包拷入tomcat的webapps文件夹下后，启动tomcat,进入bin目录，运行`sh startup.sh`(mac操作)，或者双击`startup.bat`(windows操作)，解压war包。   
3) 打开dubbo-admin中WEB-INF/dubbo.properties文件，记住用户名和账号root，后面登录需要。      
4) 浏览器输入`http://localhost:8090/dubbo-admin-2.5.4-SNAPSHOT/`， 出现页面：  

>(方式二)  
1) 进入dubbo-admin目录路径，运行：`mvn jetty:run -Ddubbo.registry.address=zookeeper://127.0.0.1:2181`,直接启动dubbo控制台。 
2) 浏览器输入`http://localhost:8090` ，即可出现控制台页面。  

(四)配置服务提供者和消费者   
新建maven项目Dubbo,包含两个模块：dubbo-provider和dubbo-consumer。 
1. 提供者项目模块，依次配置好pom.xml,applicationContext.xml,TestService接口及实现类，TestServiceTest测试类。在保证zookeeper，tomcat启动的前提下，运行TestServiceTest:

consule控制台提示：  
![Alt text](/dubbo-zookeeper/photos/dubbo.png)     
dubbo控制台显示：  
![Alt text](/dubbo-zookeeper/photos/aa.png)   
![Alt text](/dubbo-zookeeper/photos/bb.png)   
![Alt text](/dubbo-zookeeper/photos/cc.png)  
![Alt text](/dubbo-zookeeper/photos/dd.png) 

3. 消费者项目模块，依次配置好pom.xml,applicationContext.xml,ConsumerServiceTest测试类。在保证zookeeper，tomcat,provider都已启动的前提下，运行ConsumerServiceTest:  

consule控制台提示：  
![Alt text](/dubbo-zookeeper/photos/consule.png)
dubbo控制台显示：
![Alt text](/dubbo-zookeeper/photos/ee.png)   
![Alt text](/dubbo-zookeeper/photos/ff.png) 






