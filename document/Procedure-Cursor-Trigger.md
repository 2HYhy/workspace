存储过程-游标-触发器

## 数据库变量  
### 系统变量：查看时使用两个@@
> show variables  
>- 查看所有系统内置变量  

> select @@变量名  
>- 查看某个系统变量    

> set 变量名 = 值  
>- 修改变量值 

### 自定义变量：查看时使用一个@
> set @变量名 = 值   
> select @变量名  

 
## mySQL存储过程  
*转载自易百教程*
 
特点: 提高应用程序的性能，减少应用程序和数据库服务器之间的流量。
使用Navicat新建函数时，只包括封闭代码块部分。   

创建存储过程:   
```java
DELIMITER //
create procedure getAllUcopUsers()
begin
select count(*) from table;
end //
DELIMITER ;

//调用存储过程
call getAllUcopUsers();
```

声明变量:
```java
declare name varchar(20) default 'Jhon';
declare x, y;  //默认值为NULL
declare create_time datetime default NOW();
```

变量赋值:
```java
set x = 10;
select count(uid) into y from table;
```

显示存储过程:
```java
//显示数据库中所有存储的存储过程基本信息，包括所属数据库，存储过程名称，创建时间
show procedure status
show procedure status where name like '%user%'
//显示存储过程Procedure_Name的详细信息
show create procedure <存储过程名>;
```

1. 带有输入、输出、输入输出参数
```java
drop procedure if exists `getUidByProvince`;
delimiter //
create procedure getUidByProvince(in proName varchar(36))
BEGIN
select user_id from table where province = proName; 
END //
delimiter ;

//调用存储过程
call getUidByProvince('陕西省')
```
```java
drop procedure if exists `getCountByMark`;
delimiter //
create procedure getCountByMark(in mark INT, out total INT)
begin
select count(*) into total from table where mark = mark;
end //
delimiter ;

//调用存储过程
call getCountByMark(0, @total);
select @total;
```
```java
delimiter //
create procedure addCounter(inout counter INT(2), in num INT(2))
begin
set counter = counter + num;
end //
delimiter ;

//调用存储过程
set @counter = 1;
call addCounter(@counter,1);
call addCounter(@counter,3);
select @counter;
```
2. 返回多个值
```java
delimiter //
create procedure getMultiInfo(IN customerNo int, OUT shipped int, OUT deleted int, OUT resolved int, OUT disputed int)
BEGIN
select count(*) into shipped from table where customerNumber = customerNo and status = 'Shipped';
select count(*) into deleted from table where customerNumber = customerNo and status = 'Canceled';
select count(*) into resolved from table where customerNumber = customerNo and status = 'Resolved';
select count(*) into disputed from table where customerNumber = customerNo and status = 'Disputed';
END //
delimiter ;

//调用存储过程
call getMultiInfo(141, @shipped,@deleted,@resolved,@disputed);
select @shipped,@deleted,@resolved,@disputed;
```
3. 带有IF语句
```java
delimiter //
create procedure getUserLevel(in number int, out levels varchar(20))
begin
declare scope double;
select creditLimit into scope from table where customerNumber = number;
IF scope < 10000 THEN
set levels = 'Copper';
ELSEIF scope >= 10000 and scope <= 40000 THEN
set levels = 'Silver';
ELSE 
set levels = 'Gold';
END IF;
end //
delimiter ;

//调用存储过程
call getUserLevel(112, @levels)
select @levels
```
4. 带有CASE语句
```java
delimiter //
create procedure getSendDays(in customerNo int, out sendDay varchar(20))
BEGIN
declare temp varchar(30);
select country into temp from table where customerNumber = customerNo;
case temp
when 'USA' then set sendDay = 'two day';   //when可以是常量，也可以是表达式
when 'Canada' then set sendDay = 'three day';
else set sendDay = 'five day';
end case;
END //
delimiter ;

//调用存储过程
call getSendDays(166,@sendDay)
select @sendDay
```
5. 带有WHILE循环
```java
delimiter //
drop procedure if exists `createString`;
create procedure createString()
BEGIN
declare i int default 0;
declare string varchar(30);
set string = '';
while i <= 10 do 
set string = CONCAT(string,i,'%');
set i = i + 2;
end while;
select string;
END //
delimiter ;

//调用存储过程
call createString();
```
6. 带有REPEAT循环
```java
delimiter //
drop procedure if exists createString //
create procedure createString()
BEGIN
declare i int default 0;
declare string varchar(30);
set string = '';
repeat 
set string = CONCAT(string,i,',,');
set i = i + 2;
until i > 10
end repeat;
select string;
END //
delimiter ;

//调用存储过程
call createString();
```
7. 带有LOOP循环
```java
delimiter //
drop procedure if exists createString //
create procedure createString()
BEGIN
declare i int default 0;
declare string varchar(30);
set string = '';
loop_label: LOOP
if i > 10 then 
leave loop_label;  //leave相当于break,退出整个循环
end if;
set i = i + 1;
if(i mod 2) then
iterate loop_label;  //iterate相当于continue，退出本次循环
else 
set string = CONCAT(string,i,'--');
end if;
end LOOP;
select string;
END //
delimiter ;

//调用存储过程
call createString();
```
8. 不带循环的游标
```java
delimiter //
create procedure getUserList()
BEGIN
declare uid varchar(20) default 0;
declare username varchar(30);
declare nickname varchar(30);
declare my_finished int default 0;

declare user_cursor cursor for select user_id as uid, user_name as username, nick_name as nickname from user;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET my_finished = 1;

open user_cursor;

fetch user_cursor into uid, username, nickname;

-- 输出结果
select uid, username, nickname;
close user_cursor;
END //
delimiter ;

//调用存储过程
call getUserList()
```
9. 带有LOOP循环的游标
```java
drop procedure if exists `getEmailList`;
delimiter //
create procedure getEmailList(INOUT emailList varchar(5000))
BEGIN
-- 声明变量
declare my_finished int default 0;
declare my_email varchar(100) default "";
-- 声明游标
declare email_cursor cursor for select email from employees;
-- 声明not found处理程序:如果没有更多的行要提取，将finished变量的值设为1并继续执行封闭代码块begin-end
declare continue handler for not found set my_finished = 1;
-- 打开游标
open email_cursor;
-- 遍历电子邮件列，得到邮件列表
get_email_list: LOOP
fetch email_cursor into my_email;
if my_finished = 1 then 
leave get_email_list;
end if;
set emailList = CONCAT(my_email, ";", emailList);
end LOOP get_email_list;
close email_cursor;
END //
delimiter ;

//调用存储过程
set @email_list = "";
call getEmailList(@email_list);
select @email_list;
```
10. 带有WHILE循环的游标
```java
drop procedure if exists `addUser`;
delimiter //
create procedure addUser()
BEGIN
declare finished int default 0;
declare uid varchar(20);
declare ucity varchar(30);

declare user_cursor cursor for select officeCode as uid,city as ucity from offices where officeCode < 7;

declare continue handler for NOT FOUND set finished = 1;

open user_cursor;

fetch user_cursor into uid, ucity;

while finished != 1 do
insert into utest(uid, ucity) values(uid, ucity); 
fetch user_cursor into uid, ucity;   //必须在循环体中存在
end while;
close user_cursor;
END //
delimiter ;

//调用存储过程
call addUser();
```
11. 带有REPEAT循环的游标
```java
drop procedure if exists `countUsers`;
delimiter //
create procedure countUsers(IN pro_id int)
BEGIN
declare finished int default 0;
declare total double default 0.0;
declare count double default 0.0;

declare pro_cursor cursor for select amount from payments where customerNumber = pro_id;

declare continue handler for NOT FOUND set finished = 1;

open pro_cursor;
repeat 
fetch pro_cursor into count;
if finished != 1 then 
set total = total + count;
end if;
until finished = 1
end repeat;
close pro_cursor;
select total;
END //
delimiter ;

//调用存储过程
call countUsers(114);
```
12. 存储过程错误处理程序
> 声明处理程序:
`DECLARE action HANDLER FOR condition_value statement;`
>- action:CONTINUE(继续执行封闭代码块BEGIN~END);EXIT(终止封闭代码块的执行)。
>- condition_value:一个MySQL错误代码;SQLWARNING，NOTFOUND或SQLEXCEPTION。
>- statement:一个简单的语句或由BEGIN和END关键字包围的复合语句。

> `DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET has_error = 1;`
>- 如果发生错误，则将has_error变量的值设置为1并继续执行。

```java
DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;
SELECT 'An error has occurred, operation rollbacked and the stored procedure was terminated';
END;
//如果发生错误，回滚上一个操作，发出错误消息，并退出当前代码块。
```

> `DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_row_found = 1;`
>- 如果没有更多的行要提取，将no_row_found变量的值设置为1并继续执行。

> `DECLARE CONTINUE HANDLER FOR 1062;  SELECT 'Error, duplicate key occurred';`
>- 1062是MySQL错误码，如果发生重复的键错误，发出错误消息并继续执行。

```java
delimiter //
create procedure addArticle(in articleId int, in tagId int)
begin 
declare continue handler for 1062
select CONCAT('duplicate key(', articleId,',',tagId, ') is already ecist !') as errorMsg;

insert into article(article_id, tag_id) values(articleId, tagId);
select count(*) from article;
end //
delimiter ;

//调用存储过程
call addArticle(1, 11)
call addArticle(1, 12)
call addArticle(1, 13)
//当再次输入call addArticle(1, 13)时，返回的结果有两个，一是errorMsg = ‘duplicate key(1,13) is already ecist !’,而是 count(*) = 3
```

### 存储过程Navicat简单示例 
![Alt text](/images/photo1.png)   
![Alt text](/images/photo2.png)      
![Alt text](/images/photo3.png)     
![Alt text](/images/photo4.png)    
![Alt text](/images/photo5.png)    
![Alt text](/images/photo6.png)   

## mySQL存储函数
特点:
所有参数默认均为IN参数。不能为参数指定IN，OUT或INOUT修饰符;      
可以在存储过程中进行调用;    
若已经包含了SQL语句，则不应在其他SQL语句中调用; 否则将减慢查询的速度。

```java
/**
delimiter是分界符，“DELIMITER //”语句的作用是将MYSQL的结束符设置为//,代表存储过程开始
*/
delimiter //
CREATE FUNCTION adduser(userName VARCHAR(20))
RETURNS INT 
BEGIN
INSERT user(username) VALUES (userName);
/**
 声明局部变量param
*/
declare param varchar(30); 
set param = userName;
return param;
END
//
/**
存储过程定义完毕之后再使用DELIMITER;恢复默认结束符
*/
delimiter ;

//调用存储函数
select addUser();
```
```java
delimiter //
create function getLevels(scope double) returns varchar(15) deterministic 
begin 
declare levels varchar(15);
if scope < 10000 then set levels = 'Copper';
elseif scope >= 10000 and scope < 40000 then set levels = 'Silver';
elseif scope >= 40000 then set levels = 'Gold';
end if;
return (levels);
end //
delimiter ;

//在SQL语句中调用
select customerNumber, getLevels(creditLimit) from customers limit 0, 10;
```
 
## 触发器 
### 相关定义：  
1. 触发器创建语法四要素：  
> 1.监视地点(table) 2.监视事件(insert/update/delete) 3.触发时间(after/before) 4.触发事件(sql语句的insert/update/delete)。     

2. MySql使用触发器注意事项：  
> 1.触发器使用的表需要支持事务（就是InnoDB);2.创建触发器的表，不能在封闭代码块（就是BEGIN...END）中再次操作该张表。 

3. 特点：
> 当对表执行数据修改事件时，会自动调用触发器，而存储过程必须要明确地调用; 触发器可以调用存储过程或存储函数。  

### 语法格式即命令：  
```java 
create trigger triggerName triggerTime triggerEvent on tableName
for each row 
begin
triggerBody
end 
```  
> triggerName: 标识触发器名称，可自行指定;
> triggerTime: 标识触发时间，取值为BEFORE 或 AFTER;
> triggerEvent: 标识触发事件，取值为INSERT、UPDATE 或 DELETE;
> tableName: 标识建立触发器的表名，即在哪张表上建立触发器;
> triggerBody: 触发器程序体，可以是一句SQL语句，或者用 BEGIN 和 END 包含的多条语句.  

在INSERT型触发器中,新插入的行用new来表示，行中的每一列的值用new.列名来表示. 即：NEW用来表示将要（BEFORE）或已经（AFTER）插入的新数据。仅可以使用NEW关键字;      
在DELETE型触发器中，原本有一行,后来被删除，想引用被删除的这一行，用old来表示，old.列名可以引用被删除的行的值.即：OLD用来表示将要或已经被删除的原数据，仅可以使用OLD关键字;         
在UPDATE型触发器中被修改的行，修改前的数据，用old来表示，old.列名引用被修改之前行中的值；修改的后的数据，用new来表示，new.列名引用被修改之后行中的值;        
before是先完成数据的增删改，再去触发，after是先完成触发，再进行数据的增删改。  

> show triggers [from databaseName]   
>- 查看触发器

> SHOW TRIGGERS FROM yiibaidb WHERE `table` = '表名';
>- 查看指定表的触发器

> select * from information_schema.triggers
>- 查看所有触发器

> select * from information_schema.triggers where trigger_schema = "数据库名" and trigger_name = "触发器名"
>- 查看指定条件下的触发器

> select * from informaion_schema.triggers where trigger_schema = "数据库名" and event_object_table = "表名"
>- 查看指定条件下的触发器

> drop trigger [if exists] [databaseName.] triggerName     
>- 删除触发器

> show table status like 'table_name'    
>- 查看当前数据库中某个表的状态信息

> show table status   
>- 查看当前数据库中所有表的状态

> show triggers from t_user_regist_user  
>- 查看当前数据库中指定表的触发器

> show triggers   
>- 查看当前数据库中所有表的触发器信息  

### mysql触发器示例： 
原始两张表:
**产品表p**   
| id        | name      | num  |
| --------- |:---------:| ----:|
| 1         | 电视       |   10 |
| 2         | 空调       |   10 |
| 3         | 冰箱       |   10 |

**订单表o**:订单号，产品号，产品数量      
| oid        | pid      | quantity  |
| ---------- |:--------:| ---------:|
     
1. <font color="red">创建一个触发器实现：当订单表插入一条订单记录时，产品表中产品的数量要对应减少</font>  
```java
create trigger tg1
after insert on o
for each row 
begin
update p set num=num-new.quantity where id=new.pid;
end
```    
>- eg:插入一条记录 `insert into o(pid,quantity) values(1,3)`之后，表p中的电视数量变为7。    

2. <font color="red">创建一个触发器实现：当用户撤销一个订单时，产品表中产品的数量要再加回去</font>     
```java
create trigger tg2
after delete on o
for each row
update p set num = num + old.quantity where id = old.pid;
```  
3. <font color="red">创建一个触发器实现：当用户修改一个订单时，产品表中产品的数量要进行改变</font>    
```java
create trigger tg3
after update on o
for each row
begin
update p set num = num+old.quantity-new.quantity where id = old/new.pid;
end
```  
>- 先把旧的数量恢复再减去新的数量就是修改后的数量,这时old.pid=new.pid   

4. <font color="red">创建一个触发器实现：当新增一个订单时，先判断订单的产品数量，若大于产品表中产品的数量，则默认为产品表中产品的数量</font>   
```java
create trigger tg4
before insert on o
for each row
begin
if new.quantity > 10 then 
  set new.quantity = 10;
end if;
update p set num = num - new.quantity where id = new.pid;
end
```   
```java
//单一触发器
delimiter //
create trigger before_employees_update before update 
on table1 for each row 
begin 
insert into table2
set action = 'update', employeeNumber = OLD.employeeNumber, lastname = OLD.lastname, changeDate = now();
end //
delimiter ;


delimiter //
create trigger after_user_insert after insert 
on table1 for each row 
begin
insert into table2(user_id, login_time, login_sn) values(new.user_id, now(), new.user_name);
end //
delimiter ;
```  
```java
//多个触发器
delimiter //
CREATE TRIGGER before_products_update1 BEFORE UPDATE
ON table1 FOR EACH ROW 
BEGIN
INSERT INTO table2(pro_code, price) values(old.productCode, old.msrp);
END //
delimiter ;

delimiter //
create trigger before_products_update2 before update 
on table1 for each row follows before_products_update1 
begin
update table2 set update_person = user() where pro_code = old.productCode;
end //
delimiter ;
```
```java
//获取触发器的执行顺序:
//查询information_schema数据库的triggers表中的action_order列
select trigger_name, action_order 
from 
information_schema.triggers 
where 
trigger_schema = '数据库名'
```