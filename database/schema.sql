create database if not exists bgg;

use bgg;

create table if not exists user (
	id				varchar(8) not null,
    username		varchar(50) not null,
    name			varchar(50),
    constraint user_pk primary key (id)
);

create table if not exists task (
	task_id			int not null auto_increment,
    description		varchar(255),
    priority		int check (priority between 1 and 3),
    due_date		date,
    user_id			varchar(8),
    constraint task_pk primary key (task_id),
    constraint fk_user foreign key (user_id) references user (id)
);