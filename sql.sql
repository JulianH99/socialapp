create database if not exists socialapp;

use socialapp;


create table if not exists users (
	id bigint auto_increment,
    name varchar(50) unique not null,
    password varchar(250) not null,
    email varchar(50) not null unique,
    register_date date,
    gender smallint not null,
    primary key(id)
);


create table if not exists animes(
	id bigint auto_increment,
    name varchar(60) unique not null,
    image_path varchar(200) unique not null, 
    primary key(id)
);

create table if not exists anime_genders(
	id bigint auto_increment,
    name varchar(40) not null,
    primary key(id)
);

create table if not exists anime_gender_rels(
	id bigint auto_increment,
    anime_id bigint not null,
    gender_id bigint not null,
    primary key(id),
    foreign key(anime_id) references animes(id),
    foreign key(gender_id) references anime_genders(id)
);

create table if not exists anime_lists(
	id bigint auto_increment,
    user_id bigint not null,
    anime_id bigint not null,
    primary key(id),
    foreign key(user_id) references users(id),
    foreign key(anime_id) references animes(id)
); 


create table if not exists posts(
	id bigint auto_increment,
    user_id bigint not null,
    anime_id bigint null,
    content text not null,
    pub_date datetime default current_timestamp,
    score bigint,
    is_question bit not null default 0,
    post_type int not null,
    url_video varchar(250) null,
    img_path varchar(250) null,
    primary key(id),
    foreign key(user_id) references users(id),
    foreign key(anime_id) references animes(id)
);


create table if not exists comments(
	id bigint auto_increment,
    post_id bigint not null,
    user_id bigint not null,
    user_score int not null,
    content text not null,
    primary key(id),
    foreign key(post_id) references posts(id),
    foreign key(user_id) references users(id)
);


# sp


alter table users add column picture_path varchar(200) not null default 'default.jpg' after email;



use socialapp;

delimiter //
create or replace PROCEDURE getCommentsFor(in post BIGINT)
BEGIN
	
	select id, content, user_score, user_id, user_score, post_id 
	from comments
	where post_id = @post;
	
END //

delimiter ;


# deinition of getAnimesFor user procedure
delimiter //
create or replace procedure getAnimesFor(in usr BIGINT)
BEGIN
	select a.id, a.name, a.image_path from anime_lists as al
	join animes as a
	on al.anime_id = a.id
	where al.user_id = @usr;
END //

delimiter ;





