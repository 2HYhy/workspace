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

6. Dockerfile示例
```java 
FROM java:8-jre
ENV VERSION="0.0.1-SNAPSHOT"
//指定容器中的工作目录为/local
WORKDIR "/local"
//复制本地的jar包到容器中的/local目录下
ADD target/eureka-client-0.0.1-SNAPSHOT.jar /local/
//复制本地的sh文件到容器中的/local/entrypoint.sh文件中，./表示相对路径
ADD entrypoint.sh ./entrypoint.sh
//为创建者授予可执行权限，运行容器中的entrypoint.sh文件
RUN chmod u+x /local/entrypoint.sh
//容器启动时执行sh文件
ENTRYPOINT ./entrypoint.sh
```
**对应的entrypoint.sh文件**
```java
java -jar -Dspring.profiles.active=$APP_ENV -Dfile.encoding=UTF-8 eureka-client-0.0.1-SNAPSHOT.jar

//容器创建与启动命令:
docrun --name eureka-client -d -p 9001:9001 -e "APP_ENV=dev" eureka-client:v1.0
```

```java
FROM java:8-jre
WORKDIR /local
//./表示相对路径,eureka-server.jar与entrypoint.sh中的jar包一致
ADD target/eureka-server-0.0.1-SNAPSHOT.jar ./eureka-server.jar
ADD entrypoint.sh ./entrypoint.sh
//先进入指定工作目录再授权
RUN cd /local/ && chmod u+x ./entrypoint.sh
ENTRYPOINT ./entrypoint.sh
```
```java
java -jar -Dfile.encoding=UTF-8 eureka-server.jar

//容器创建与启动命令:
docker run --name eureka-server -d -p 9000:9000 eureka-server:v1.0
```