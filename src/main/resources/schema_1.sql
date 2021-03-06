-- 用户表
create table sys_users(
id int primary key auto_increment comment '主键',
userName varchar(100) unique comment '用户名',
password varchar(64) not null,
salt varchar(100) not null comment '盐',
realName varchar(100) not null,
createTime timestamp,
enabled tinyint comment '用户状态 1 启用 0 禁用'
);
-- 角色表
create table sys_roles(
id int primary key auto_increment comment '主键',
roleName varchar(100) comment '角色名'
);
ALTER table sys_roles add (description VARCHAR(100) COMMENT '角色描述',available tinyint COMMENT '是否可用');
-- 用户角色表
create table sys_user_role_links(
role_id int,
user_id int,
primary key(role_id,user_id),
foreign key(user_id) references sys_users(id),
foreign key(role_id) references sys_roles(id)
);
-- 权限表
create table sys_rights(
id int primary key auto_increment comment '主键',
name varchar(100) comment '权限名称',
url varchar(100) comment '资源地址'
);
ALTER TABLE sys_rights ADD (priority int COMMENT '显示顺序',parent_id int COMMENT '父级编号',available TINYINT COMMENT '是否可用',type VARCHAR(50) COMMENT '资源类型');
ALTER table sys_rights add icon VARCHAR(100) COMMENT '菜单图标';
-- 角色权限表
create table sys_role_right_links(
role_id int,
right_id int,
primary key(role_id,right_id),
foreign key(role_id) references sys_roles(id),
foreign key(right_id) references sys_rights(id)
);