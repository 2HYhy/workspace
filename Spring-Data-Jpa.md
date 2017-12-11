## Spring data jpa 自动生成sql语句  
### 1.添加maven依赖
```java
 <!--spring data jpa, 自动生成SQL语句-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!--mysql数据库-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.38</version>
    </dependency>
```
### 2.application.approperties定义数据源及spring data  
```java
//datasourcce
spring.datasource.url=jdbc:mysql://localhoat:3306/root?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=pwd
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.max-active=20
spring.datasource.max-idle=8
spring.datasource.min-idle=8
spring.datasource.initial-size=10

//spring data jpa(可选)
spring.jpa.properties.hibernate.hbm2ddl.auto=update  //作用是自动创建、更新、验证数据库表结构，其他值有create,create-drop,validate . 
```
> create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。  
> create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。  
>  update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。    
> validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。  

### 3.创建实体类，自动生成相应的表
```java
import javax.persistence.*;

@Entity
public class PersonDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, name = "createtime")
    private String createTime;

    public PersonDO(String name, Integer age, String time){
        this.name=name;
        this.age=age;
        this.createTime=time;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
```    

### 4.创建数据访问dao层
> 接口继承关系： Repository<--CrudRepository<--PagingAndSortingRepository<--JpaRepository.  
> jpa的查询方式有三种：1.方法命名查询；2.@NameQuery查询；3.@Query查询.  
```java
public interface UserDAO extends JpaRepository<UserDO, Integer> {
     //一、命名查询法
    List<UserDO> findByAge(Integer age); //相当于JPQL:select * from <table> where age=?1

    UserDO findByNameAndAge(String name, Integer age); //相当于JPQL:select * from <table> where name=?1 and age=?2

    List<UserDO> findFirst10ByAge(Integer age); //相当于JPQL：select * from <table> where age = ?1 limit 0,10

    List<UserDO> findTop10ByAge(Integer age);

    //二、@NameQuery查询法 : 是在实体类上写，不是在接口里写，会将dao里的方法重新定义；如果要对多个方法重新定义，可以用@NameQueries包含。

    //三、@Query查询法

    @Query("select * from user where name =?1 and age = ?2")   //根据索引匹配
    UserDO withNameAndAgeQuery(String name, Integer age);

    @Query("select * from user where id = :id and password = :password")  //根据名称匹配
    UserDO findByIdAndPassword(@Param("id")Integer id, @Param("password")String password);

    /**
     * spring data jpa 使用@Modifying和@Query注解组合来表示更新
     */
    @Modifying
    @Transactional
    @Query("update user u set u.name = ?1")
    int setName(String name);
}
```  