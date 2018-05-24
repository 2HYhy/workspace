drop table if exists m_user;
create table m_user(
id int auto_increment primary key,
username varchar(20) not null comment '用户姓名',
password varchar(16) not null comment '用户密码',
phone varchar(11) not null comment '用户手机',
address varchar(255) not null comment '用户地址',
mark int default 0 comment '用户标记位',
description varchar(255) comment '用户备注'
);