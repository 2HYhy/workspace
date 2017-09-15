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

## 自定义函数
```java
create function funName(参数列表) 
returns 数据类型
begin
// 函数体
//返回值
end 
```  
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
存储过程定义完毕之后再使用DELIMITER ;恢复默认结束符
*/
delimiter ;
```   
**调用函数：** `select func2();`

## 存储过程  
1. 创建存储过程:  
```java
create procedure proceduserName(in/out/inout 参数名 参数数据类型) 
begin
//过程体
end
``` 
2. 调用存储过程:
```java
call proceduserName(@param1,@param2)
``` 
3.删除存储过程:
```java
drop procedure proceduserName
drop procedure if exists proced_name;  //先判断是否有Proc() 这个存储过程，有就drop掉
```  
> show procedure status 
>- 显示数据库中所有存储的存储过程基本信息，包括所属数据库，存储过程名称，创建时间等

> show create procedure Procedure_Name 
>- 显示存储过程Procedure_Name的详细信息   

4.存储过程创建方式三种:    
> cmd命令行方式  
>- 
```java
//创建不带参数的存储过程：
create procedure selectAll() select * from user;

//创建带有IN类型参数的存储过程：
create procedure deleteById(IN p_id int(2))
BEGIN
delete from user where id = p_id;
END
call deleteById(9);

//创建带有IN和OUT类型参数的存储过程,这是一个有输入值和返回值的存储过程:
create procedure del_query()
begin
delect from user where phone=p_phone;
select count(uid) from user into p_sum;
end   
创建带有多个OUT类型的存储过程:
create procedure mix_operate(in p_age int(2), out deleteHang int(5), out countHang int(5))
begin
delete from user where age=p_age;
select row_count() into deleteHang;
select count(uid) from user into countHang;
end
//call mix_operate(23,@a,@b); 
//select @a,@b;
``` 
**一个从创建到调用，到显示结果的完整示例：**  
DELIMITER //     
CREATE PROCEDURE removeUserAndReturnUserNums(IN p_id INT,OUT userNums)    
BEGIN     
DELETE FROM imooc_goddess WHERE id = p_id;  
SELECT COUNT(id) FROM imooc_goddess INTO userNums;       
END     
DELIMITER ;     
`call removeUserAndReturnUserNums(6,@userNums);`  
`select @userNames;`   

> Navicat 新建查询  
1. 
```java
create  procedure vv1() select version(); //查看sql版本  
create procedure vv2() select COUNT(*) into param from t_ms_user  //统计表中的总记录数
```
2. 
```java
call vv1();
call vv2();
``` 
> Navicat 新建函数  
```java
BEGIN
//过程体
END
```  
### 存储过程三种循环语法  
```java
//1
begin
declare i int;
set i=0;
while i<6 DO
  insert into blog(user_id) values(i);
  set i=i+1;
end while;
end
//2
begin
declare x int default 0;
repeat 
   insert into blog(user_id) values(x);
   set x=x+1;
   until x>=5;
end repeat;
end
//3
begin
declare i int default 0;
loop_label:LOOP
insert into table1(id) values(i);
set i = i+1;
if i>=5 then 
leave loop_label;
end if;
end loop;
end
```

### 存储过程Navicat简单示例 
![Alt text](/images/photo1.png)   
![Alt text](/images/photo2.png)      
![Alt text](/images/photo3.png)     
![Alt text](/images/photo4.png)    
![Alt text](/images/photo5.png)    
![Alt text](/images/photo6.png)   
 
## 触发器 
### 相关定义：  
1. 触发器创建语法四要素：1.监视地点(table) 2.监视事件(insert/update/delete) 3.触发时间(after/before) 4.触发事件(sql语句的insert/update/delete)。   
2. MySql使用触发器注意事项：1.触发器使用的表需要支持事务（就是InnoDB);2.创建触发器的表，不能再sql语句（就是BEGIN...END）中再次操作该张表。 

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

在INSERT型触发器中,新插入的行用new来表示，行中的每一列的值用new.列名来表示. 即：NEW用来表示将要（BEFORE）或已经（AFTER）插入的新数据;    
在DELETE型触发器中，原本有一行,后来被删除，想引用被删除的这一行，用old来表示，old.列名可以引用被删除的行的值.即：OLD用来表示将要或已经被删除的原数据;     
在UPDATE型触发器中被修改的行，修改前的数据，用old来表示，old.列名引用被修改之前行中的值；修改的后的数据，用new来表示，new.列名引用被修改之后行中的值;        
before是先玩成数据的增删改，再去触发，after是先完成触发，在进行增删改。  

> show triggers [from databaseName]   
>- 查看触发器

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
| id        | name           | num  |
| ---------- |:---------:| -----:|
| 1    | 电视 | 10 |
| 2     | 空调      |   10 |
| 3 | 冰箱     |    10|

**订单表o**:订单号，产品号，产品数量      
| oid        | pid      | quantity  |
| ---------- |:---------:| -----:|
     
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

## 游标 
1. 定义语法：   
```java
begin
    declare done boolean default 0; //定义一个循环标记默认值为false
    declare tmp int; //定义局部变量
    declare youbiao cursor for select name from t; //定义游标
    //当出现02000错误时把局部变量的值设为true
    declare continue handler for sqlstate '02000' set done 1; 
    open youbiao; //打开游标
    repeat
       fetch youbiao into tmp; //获取游标当前指向的数据行，并将指针指向下一行
       insert into tt(t_name) values(tmp);
    until done end REPEAT;  //当done为true时结束repeat
    close youbiao; //关闭游标
end
```

2. 
```java
begin
declare name varchar(20);
declare num int;
declare done int default false;
declare mycur cursor for select appId, count(uid) from table1 where appId="test";
declare continue handler for not found set done = true;
open mycur;
  fetch mycur into name,num;
close mycur;
select num;
end
```

3. 原始表tt：<font color="red">创建一个存储过程实现：统计apple的总数量</font>     

| id  | t_name   | t_count  |  
| --- |:--------:| --------:|  
| 1   | pear     | 10       |  
| 2     |  apple     |   7|  
| 3 | banana     |    3|   
|  4 | apple | 4 |
| 5     |  banana     |   9|
| 6 | apple     |    2|  

```java(repeat循环)
begin
declare name varchar(20);
declare num int;
declare total int;
declare done int default false;
declare mycur cursor for select t_name, t_count from tt where t_name="apple";
declare continue handler for not found set done = true;
set total = 0;
open mycur;
repeat
  fetch mycur into name,num;
  set total = total + num;
until done end repeat;
close mycur;
select total;
end
```
```java(loop循环)
begin
declare name varchar(30); 
declare count int;
declare total int;
declare done boolean default 0;
declare mycur cursor for select t_name,t_count from tt where t_name="apple";
declare continue handler for not found set done = 1;
set total = 0;
open mycur;
read_loop:loop 
  fetch mycur into name, count;
  if done then 
    leave read_loop;
  end if;
  set total = total+count;
end loop;
close mycur;
select total;
end
```






















