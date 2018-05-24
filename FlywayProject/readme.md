1. 添加依赖
```java
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.44</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
	<version>4.2.0</version>
</dependency>

<plugin>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-maven-plugin</artifactId>
	<version>4.2.0</version>
</plugin>
```
2. resource下创建文件夹以及sql脚本
> /db/migration
> V1.1__user.sql
>- 读取sql脚本的默认位置:db/migration
>- sql脚本的命名规范:V+版本号(版本号的数字间以"."或"_"分隔开)+双下划线+文件描述+后缀名(.sql)
