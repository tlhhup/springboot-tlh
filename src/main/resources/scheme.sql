-- 用户表
create table users(
id int primary key auto_increment comment '主键',
userName varchar(100) unique comment '用户名',
password varchar(64) not null,
realName varchar(100) not null,
createTime timestamp,
enabled tinyint comment '用户状态 1 启用 0 可以用'
);
-- 角色表
create table roles(
id int primary key auto_increment comment '主键',
roleName varchar(100) comment '角色名'
);
-- 用户角色表
create table user_role_links(
role_id int,
user_id int,
primary key(role_id,user_id)
);
-- 权限表
create table rights(
id int primary key auto_increment comment '主键',
name varchar(100) comment '权限名称',
url varchar(100) comment '资源地址'
);
-- 角色权限表
create table role_right_links(
role_id int,
right_id int,
primary key(role_id,right_id)
);