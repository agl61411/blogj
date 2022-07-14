drop database if exists blog;

create database blog;

use blog;

drop table if exists account;

create table `account` (
    `id` bigint(20) not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `deprecated` tinyint(1) unsigned not null default '0' comment '是否弃用 0否 1是',
    `username` varchar(50) not null default '' comment '手机号 邮箱 用户名或第三方应用的唯一标识',
    `password` varchar(255) not null default '' comment '密码',
    `nickname` varchar(50) not null default '' comment '昵称',
    `avatar` varchar(255) default null comment '头像地址',
    primary key (`id`),
    unique key `username` (`username`)
) engine=innodb default charset=utf8mb4 comment='账号表';

drop table if exists account_token;

create table `account_token` (
    `id` bigint(20) not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `deprecated` tinyint(1) unsigned not null default '0' comment '是否弃用 0否 1是',
    `username` varchar(50) not null default '' comment '手机号 邮箱 用户名或第三方应用的唯一标识',
    `access_token` text comment '密码',
    `refresh_token` text comment '昵称',
    primary key (`id`),
    unique key `username` (`username`)
) engine=innodb default charset=utf8mb4 comment='token表';

drop table if exists blog;

create table `blog` (
    `id` bigint(20) not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `deprecated` tinyint(1) unsigned not null default '0' comment '是否弃用 0否 1是',
    `title` varchar(50) comment '标题',
    `content` text comment '内容',
    `tags` varchar(255) comment '标签',
    `account_id` bigint(20) not null comment '关联用户',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='blog';

drop table if exists diary;

create table `diary` (
    `id` bigint(20) not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `deprecated` tinyint(1) unsigned not null default '0' comment '是否弃用 0否 1是',
    `content` text comment '内容',
    `account_id` bigint(20) not null comment '关联用户',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='diary';