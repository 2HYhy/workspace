drop table if exists m_fruit;
create table m_fruit(
id int auto_increment primary key,
fruitname varchar(20) not null comment '水果名',
source varchar(16) not null comment '水果产地',
price varchar(11) not null comment '水果价格',
description varchar(255) comment '用户备注'
);