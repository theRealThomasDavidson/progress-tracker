drop database if exists progress;
create database progress;
use progress;

create table progress_user(
	id int unique not null primary key auto_increment ,
    username varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    pass_hash char(255), 
    is_admin enum('y','n') default 'n'
);

create table movie(
	id int unique not null primary key auto_increment,
    title varchar(255) not null,
	rating enum('G','PG','PG13','R'),
    length_in_minutes int
);

create table movie_user(
	id int unique not null primary key auto_increment,
    user_id int, 
	foreign key(user_id) references progress_user(id),
    movie_id int, 
	foreign key(movie_id) references movie(id),
    watch_time_in_minutes int default 0,
    rating enum('1','2','3','4', '5', 'no rating yet')
		default 'no rating yet'
);

create table playlist(
	id int unique not null primary key auto_increment,
    playlist_name varchar(255) not null,
    user_id int,
	foreign key(user_id) references progress_user(id)
);

create table playlist_item(
	id int unique not null primary key auto_increment,
    playlist_id int, 
	foreign key (playlist_id) references playlist(id),
    item_id int,
	foreign key(item_id) references movie_user(id)
);

INSERT INTO `progress_user`(username, first_name, last_name) VALUES ("user1","Alice","Anderson");
INSERT INTO `progress_user`(username, first_name, last_name) VALUES ("user2","Bob","barker");
INSERT INTO `progress_user`(username, first_name, last_name) VALUES ("user3","Carol","Cantrel");
INSERT INTO `progress_user`(username, first_name, last_name) VALUES ("user4","Doug","Dennis");
INSERT INTO `progress_user`(username, first_name, last_name) VALUES ("user5","Erin","Ekleson");

INSERT INTO `movie`(title, rating, length_in_minutes) VALUES ("movie1","G",96);
INSERT INTO `movie`(title, rating, length_in_minutes) VALUES ("movie2","PG",118);
INSERT INTO `movie`(title, rating, length_in_minutes) VALUES ("movie3","PG13",143);
INSERT INTO `movie`(title, rating, length_in_minutes) VALUES ("movie4","R",87);

insert into `movie_user`(user_id, movie_id) values(1,2);
insert into `movie_user`(user_id, movie_id) values(1,4);
insert into `movie_user`(user_id, movie_id) values(2,1);
insert into `movie_user`(user_id, movie_id) values(2,3);
insert into `movie_user`(user_id, movie_id) values(2,4);
insert into `movie_user`(user_id, movie_id) values(3,1);
insert into `movie_user`(user_id, movie_id) values(3,4);
insert into `movie_user`(user_id, movie_id) values(4,1);
insert into `movie_user`(user_id, movie_id) values(5,2);
insert into `movie_user`(user_id, movie_id) values(5,3);
insert into `movie_user`(user_id, movie_id) values(5,4);


insert into `playlist`(playlist_name, user_id) values("gregory", 1);
insert into `playlist`(playlist_name, user_id) values("phyllis", 2);
insert into `playlist`(playlist_name, user_id) values("eleanore", 3);
insert into `playlist`(playlist_name, user_id) values("maybell", 4);
insert into `playlist`(playlist_name, user_id) values("genovieve", 5);
insert into `playlist`(playlist_name, user_id) values("bernadette", 2);
insert into `playlist`(playlist_name, user_id) values("clarice", 3);


insert into playlist_item(playlist_id, item_id) values (1,1);
insert into playlist_item(playlist_id, item_id) values (1,2);
insert into playlist_item(playlist_id, item_id) values (2,3);
insert into playlist_item(playlist_id, item_id) values (2,4);
insert into playlist_item(playlist_id, item_id) values (6,5);
insert into playlist_item(playlist_id, item_id) values (3,6);
insert into playlist_item(playlist_id, item_id) values (3,7);
insert into playlist_item(playlist_id, item_id) values (7,6);
insert into playlist_item(playlist_id, item_id) values (4,8);
insert into playlist_item(playlist_id, item_id) values (5,9);
insert into playlist_item(playlist_id, item_id) values (5,10);
insert into playlist_item(playlist_id, item_id) values (5,11);

select * from progress_user;
select * from movie;
select * from movie_user 
join progress_user on progress_user.id=movie_user.user_id
join movie on movie.id=movie_user.movie_id;

select *, playlist_name from playlist_item
join movie_user on playlist_item.item_id=movie_user.id
join playlist on playlist_item.playlist_id=playlist.id
where playlist_item.playlist_id = 3 
and movie_user.user_id = 3;
