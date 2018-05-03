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


