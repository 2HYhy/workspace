### mysql基本语句
关系型数据库。    
优点:
> 体积小，速度快， 成本低   
> 支持多种操作系统,多种语言(C,C++,Java,PHP)    
> 开源高效    

缺点:
> 不支持热备份    
> 安全系统复杂且不标准   

分为DDL(数据定义语言)，DML(数据操作语言)，DCL(数据控制语言)，DQL(数据查询语言))四种。      
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
 > `DATE_ADD(xx,INTERVAL num type)`  日期xx增加num个以type为单位的值  
 > `DATE_SUB(xx,INTERVAL num type)`  日期xx减少num个以type为单位的值   
      
10. limit函数：  
```java
select * from table limit m , n
//数据库中的记录下标是从0开始的，即从记录的第 m+1 条开始，共选择 n 条数据。    
select * from table limit x
//取出前x条记录 
```      

11. `alter table 表名 add  column 列名 数据类型`   在表中添加列  
12. `alter table 表名 change 列名 新列名 数据类型`  修改表中的列 
13. `alter table 表名 drop column 列名`   删除某一列     
14. `truncate table xxxxxx`    清除某个表的全部数据 
```java
//查看event是否开启:
 show variables like 'event_scheduler'; 
//将事件计划开启: 
set global event_scheduler=1; 
```      

### mysql相关操作命令：          
> 连接远程数据库:    
> mysql  -h <主机> -P <端口> -u <用户名> -p<密码>    p之后不加空格直接登录，也可不输入密码，待回车后单独输入密码      

> exit;  退出数据库 

> net start mysql;  启动数据库

> net stop mysql;   关闭数据库 

> show databases;  每一条语句必须以分号结尾

> use db_name;   选择数据库

> mysql -V
> status
> select version(); 
>- 查看mysql版本

> autoReconnect=true&useUnicode=true&characterEncoding=utf-8
>- 配置数据源时，添在数据库名后面用于解决中文乱码问题

> jdbc:mysql://http://localhost:3306//test?allowMultiQueries=true
>- 用于mybatis+mysql的批量插入和更新

> 本地启动连接mysql:   
>- 查看mysql状态: `sudo Documents/tools/mysql/support-files/mysql.server status`    
>- 启动mysql服务: `sudo Documents/tools/mysql/support-files/mysql.server start`   
>- 停止mysql服务: `sudo Documents/tools/mysql/support-files/mysql.server stop`     
>- 连接本地mysql: `sudo Documents/tools/mysql/bin/mysql -u root -p` or `sudo Documents/tools/mysql/bin/mysql -h 127.0.0.1 -u root -p`       
**此处是没有配置环境变量，所以必须加上路径**

### 几大数据类型： 
1. unsigned属性，特殊应用场景：存放ip  
> INET_ATON()函数：将IP转换为数值类型;  
>- `select INET_ATON('127.0.0.1')` 

> INET_NTOA()函数：将数值类型转换为IP。   
>- `select INET_NTOA(2130706433)`

2. decimal(M,D)
> 其中，M代表正数和小数部分的总位数，D代表小数部分的位数。  

3. timestamp和datetime    
> 显示结果一样，都是固定的“YYYY-mm-dd hh:MM:ss”的形式。     
```java
 insert into table(t1 timestamp,t2 datetime) values(now(),now()); 
 select * from table; 
 t1="2017-10-06 16:12:56" ,  t2="2017-10-06 16:12:56"
 ```   
> timestamp时间范围为"1970-01-01 00:00:01" UTC 到"2038-01-19 03:14:07" UTC, UTC是协调世界时，就如我们中国的时区是UTC+8，显示的时间范围要加上8小时。        
> 保存一个timestamp类型的时间时，它的值以UTC格式存放，即就是从"1970-01-01 00:00:01"到当前的秒数。  

### 关于表结构的修改语句:
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

### 关于分级，组合，内连接查询：  
1. 分级查询group by 
> group by单独使用时，只显示出每组的第一条记录，意义不大;  
> group by + group_concat用来放置每一组的某字段的值的集合;
>- `select sso, GROUP_CONCAT(phone) from user group by sso`

> group by + 聚合函数最常用于统计   
> group by + having用户限制分组结果的显示  
> group by + with rollup会在最后加上一条新纪录，统计各分组总和。  
>- `select app_id, count(uid) as counts from t_ucop_user_test group by app_id  with rollup`  

> group by分级查询中,(1)在select中指定返回的字段，要么包含在group by语句的后面，作为分组依据，要么就要被包含在聚合函数中。(2)where子句的作用是在对查询结果进行分组前，将不符合条件的行去掉，即在分组之前过滤数据，不能包含聚合函数；having子句的作用是筛选满足条件的组，即在分组之后过滤数据，经常包含聚合函数。   

2. 组合查询union  
用于合并两个select的结果集，只要两个结果集的列数相等，顺序相同。默认消去结果集中的重复行，若要返回重复数据，使用union all。    
`select uid from user union select uid from user_test`   

3. 内连接inner join   
实质是两表先进行笛卡尔乘积运算(用第一个对象的每一项乘以第二个对象的每一项，即交叉乘积)，然后再根据on后面的限制条件对结果进行筛选。   
    
### mysql语句集锦：  
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

### mysql查询的整个执行过程：
> 客户端向mysql服务器发送一条查询请求  
> 服务器首先检查缓存，若命中缓存，则直接将存储在缓存中的结果返回给客户端，否则执行下一条    
> 服务器进行SQL解析，预处理，再由优化器生成执行计划  
> mySql根据执行计划调用存储引擎的接口执行查询  
> 将结果返回给客户端，同时缓存查询结果

### 关于索引：
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
> 查询条件应避免多个范围条件，否则mysql只能任选一列的索引，而不能同时使用它们
>- `select * from table where time > '2018-03-20' and age between 10 and 30`

> 覆盖索引是指其包含了所有需要查询的字段，可以极大地提高性能   
>- `select * from table where id=x and name=x and birthday=x`, 覆盖索引为{id,name,birthday} 

> 若已经有一个索引{a, b}, 再去创建一个索引{a}的话，就是冗余索引(相同的列上按相同的顺序创建的相同类型的索引),应该避免    

### mysql性能优化的三大方面:
（一）数据类型优化     
（二）创建高性能索引    
（三）特定类型查询优化  
> 优化count()查询
```java
count(列): 统计某一列值的数量，要求列值非空，不会统计NULL
count(*): 统计行数
explain执行计划出来的rows行数就是count()的近似值，对数据精确性要求不高时可代替
```
> 优化关联查询
```java
1. ON子句中的列上最好有索引。 
当关联顺序为 select * from A join B on A.uid = B.uid 时，整个关联查询只会用到B表的索引，不会使用A表的索引，因此，只需在B表的uid上创建索引，加速查询，而不用在A表的uid上创建索引，增加负担
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

### mysql函数:
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
dayname(): 返回对应的星期
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

### mysql用户账户管理:
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