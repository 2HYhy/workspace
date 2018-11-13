### maven安装配置

1. 概念   
maven是一个采用纯Java编写的开源项目管理工具,基于一种项目对象模型(POM)的概念来管理项目的构建。

2. 下载     
在maven的官网地址 [http://maven.apache.org/](http://maven.apache.org/) 中下载maven组件。

3. 配置环境变量       
windows系统:   
(1) 新建系统变量:     
变量名=MAVEN_HOME，变量值=[ maven的安装地址，eg: D:\Java\apache-maven-3.3.9 ];     
(2) 编辑系统变量:   
变量名=path，变量值=[ 将`%M2_HOME%\bin;`添在最后面 ]。   
mac系统:   
(1) 终端打开环境变量配置文件:    
`vim ~/.bash_profile`;     
(2) 添加maven配置内容:   
`export MAVEN_HOME=[ maven的安装地址，eg: /usr/local/maven/apache-maven-3.3.9] `       
`export PATH=$PATH:$MAVEN_HOME/bin`        

4. 通过终端运行`mvn -v`或者`mvn -version`命令，出现maven的版本信息及其他相关信息，即表示环境变量配置成功。   

### maven基本知识

1. maven坐标与仓库   
坐标:   
`groupId` 是项目组织标识，也就是包名   
`artifactId` 是项目名称    
`version` 是项目当前版本       
仓库:    
仓库是用来统一存储所有Maven共享构建的位置，有本地仓库和远程仓库(中央仓库+私服)之分。           
maven加载依赖时，先在本地仓库进行搜索，如果没找到，再去maven的配置文件setting.xml中指定的远程仓库进行搜索，而搜索的依据就是maven的坐标。     
2. maven生命周期    
一个完整的maven项目包含三个彼此之间相互独立的生命周期。每个生命周期包含多个阶段，每个阶段是按顺序执行的，运行后一个阶段时，前阶段会自动被执行。     
(1) clean: 清理项目，使用的构建命令是`mvn clean`。   
(2) default: 构建项目，包括编译、测试、打包、安装四个阶段，使用的构建命令一次是`mvn compile`, `mvn test`, `mvc package`, `mvn install`。    
(3) site: 根据pom文件的内容生成项目站点。    

3. maven项目目录结构   
src/main/java: 存放项目所有开发的.java文件   
src/main/resources: 存放项目开发所有资源文件    
src/test/java: 存放项目所有测试的.java文件   
src/test/resources: 存放项目测试所有资源文件    
target: 项目最终的输出位置
pom.xml: 标识该项目是一个maven项目  

### maven项目创建

#### 使用命令的方式构建maven项目 (*不常用*)
```java
mvn archetype:generate -DgroupId=<项目包名> -DartifactId=<项目名称>  -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
// -DarchetypeArtifactId表示指定项目骨架。最常用的maven-archetype-quickstart是创建Java Project, maven-archetype-webapp是创建Web Project。
// -DinteractiveMode表示是否使用交互模式。
```
使用这种方式创建的maven项目缺失开发和所需的资源文件，需要手动添加src/main/resources和src/test/resources。      

#### 借助IDEA等工具构建maven项目 (*常用*)
具体操作步骤略。  

1. pom.xml文件的基本形式
```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

  <modelVersion>项目POM版本</modelVersion>
  <groupId>项目包名</groupId>
  <artifactId>项目名</artifactId>
  <version>快照版本</version>
  <packaging>打包方式，默认为jar，可手动改成war</packaging>
 
 //项目的父依赖，只能有一组
  <parent>
    <dependency>
      <groupId>父依赖的包名</groupId>
      <artifactId>父依赖的名称</artifactId>
      <version>父依赖的版本</version>
    </dependency>
  </parent>
  
  <properties>项目属性，可以配置多个</properties>

//项目的所有依赖，由一组或多组<dependency></dependency>标签组成
  <dependencies>
    <dependency>
      <groupId>依赖包名</groupId>
      <artifactId>依赖名称</artifactId>
      <version>依赖版本</version>
    </dependency>
    ......
  </dependencies>

  <build>
    //项目需要的maven插件，由一组或多组<plugin></plugin>标签组成
    <plugins>
      <plugin>
        groupId>插件包名</groupId>
        <artifactId>插件名称</artifactId>
        <version>插件版本</version>
      </plugin>
      ......
    </plugins>
  </build>

// pom文件还有许多其他的标签，此处不作详细罗列
</project>
```

2. maven构建多模块项目的步骤 (用以下例子说明)   
>- 创建system-parent项目, 删除生成的src文件夹, 修改pom.xml中`<packaging>jar</packaging>`为`<packaging>pom</packaging>`, pom表示它是一个可以被继承的模块;     

>- 进入system-parent项目, 构建system-domain模块。system-parent的pom文件中新增`<module>system-domain</module>`,  system-domain的pom文件中删除`<groupId>`和`<version>`标签(因为会继承父模块的);

>- 进入system-parent项目, 构建system-dao模块。system-parent的pom文件中新增`<module>system-dao</module>`, system-dao的pom文件中删除`<groupId>`和`<version>`标签, 同时添加对system-domain的依赖: 
```java
        <dependency>
            <groupId>xxx</groupId>
            <artifactId>system-domain</artifactId> 
            <version>${project.version}</version>
        </dependency>
```

>- 进入system-parent项目, 构建system-service项目。system-parent的pom文件中新增`<module>system-service</module>`, system-service的pom文件中删除`<groupId>`和`<version>`标签, 同时添加对system-dao的依赖:
```java
        <dependency>
            <groupId>xxx</groupId>
            <artifactId>system-dao</artifactId> 
            <version>${project.version}</version>
        </dependency>
```   

>- 进入system-parent项目, 构建system-controller项目。system-parent的pom文件中新增`<module>system-web</module>`, system-controller的pom文件中删除`<groupId>`和`<version>`标签, 同时添加对system-service的依赖:         
```java
        <dependency>
            <groupId>xxx</groupId>
            <artifactId>system-service</artifactId> 
            <version>${project.version}</version>
        </dependency>
```    

3. 外部jar包安装到本地maven仓库      
有时候项目中需要的依赖在本地仓库和中央仓库都没有下载到，就可以通过导入jar的方式安装此依赖。   
(1) 进入jar包所在目录    
(2) 执行以下命令:    
    ```java
    mvn install:install-file -Dfile=<完整的文件名> -DgroupId=<依赖包名> -DartifactId=<依赖名称> -Dversion=<依赖版本> -Dpackaging=jar
    ```
    (3) 依赖成功安装，可通过pom.xml文件的`<dependency>`标签引入。

4. maven项目的发布过程  
- 4.1 编译   
进入maven项目的根目录下，执行命令`mvn clean compile` (clean用于清除项目缓存，一般都采用构建命令组合的形式)。     
编译成功后，生成target目录。target\classes目录中存放的是源代码的class文件。    

- 4.2 测试   
进入maven项目的根目录下，执行命令`mvn clean test`。   
测试成功后，target下生成test-class。target\test-classes目录中存放的是测试代码的class文件。   

- 4.3 打包    
进入maven项目的根目录下，执行命令`mvn clean package`。   
打包成功后，target目录下生成该maven项目的jar包，即xx-xx-SNAPSHOT.jar文件。

- 4.4 安装      
进入maven项目的根目录下，执行命令`mvn clean install` (实际项目中只执行这一条命令，前面三条就会被自动执行)。     
安装成功后，会将该maven项目的jar包安装到本地maven仓库中。

- 4.5 启动    
- - 4.5.1 ( Java Project )        
进入maven项目的target目录下，通过运行jar包的方式。    
若打包时指定了主类，执行命令`java -jar xxx.jar`;   
若打包时没有指定主类，执行命令`java -cp xxx.jar <完整的主类名>` 。      
- - 4.5.2 ( Web Project )       
将项目的war包放在tomcat目录的webapps下, tomcat服务器会自动管理webapps目录下的所有web应用, 通过启动tomcat服务器的方式。






 






    


