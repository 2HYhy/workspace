### restdocs是通过单元测试生存snippets文件，然后snippets根据插件生成htm文档的

1. 添加依赖
```java
<dependency>
    <groupId>org.springframework.restdocs</groupId>
    <artifactId>spring-restdocs-mockmvc</artifactId>
    <scope>test</scope>
</dependency>
```
2. 创建单元测试

3. 在pom中添加插件并设置snippet目录

4. 运行mvn package，生成html文档

### MockMvcBuilder构造器有两种实现:StandaloneMockMvcBuilder(独立安装测试)和DefaultMockMvcBuilder(集成web环境测试)
