drop database if exists blog;

create database blog;

use blog;

drop table if exists account;

create table `user` (
    `id` int not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `username` varchar(50) not null default '' comment '手机号 邮箱 用户名或第三方应用的唯一标识',
    `password` varchar(255) not null default '' comment '密码',
    `enabled` tinyint(1) unsigned not null comment '是否弃用 0否 1是',
    `account_non_expired` tinyint(1) unsigned not null comment '是否弃用 0否 1是',
    `credentials_non_expired` tinyint(1) unsigned not null comment '是否弃用 0否 1是',
    `account_non_locked` tinyint(1) unsigned not null comment '是否弃用 0否 1是',
    primary key (`id`),
    unique key `username` (`username`)
) engine=innodb default charset=utf8mb4 comment='用户表';

drop table if exists user_token;

create table `user_token` (
    `username` varchar(50) not null default '' comment '手机号 邮箱 用户名或第三方应用的唯一标识',
    `access_token` char(36) comment '',
    `refresh_token` char(36) comment '',
    `issue_time` datetime comment '分发时间',
    primary key (`username`)
) engine=innodb default charset=utf8mb4 comment='token表';

drop table if exists record;

create table `record` (
    `id` int not null auto_increment,
    `creationtime` datetime default current_timestamp comment '创建时间',
    `modifiedtime` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `title` varchar(50) comment '标题',
    `content` varchar(1000) comment '内容',
    `tags` varchar(255) comment '标签',
    `user_id` int not null comment '关联用户',
    `record_time` date not null comment '',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='record';