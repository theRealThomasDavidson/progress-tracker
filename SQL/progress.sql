drop database if exists progress;
create database progress;
use progress;

create table `user`(
	user_id int unique not null primary key auto_increment,
    user_username varchar(255) not null,
    user_firstName varchar(255) not null,
    user_lastName varchar(255) not null,
    user_email varchar(255) not null, 
    user_password varchar(255) not null default "", 
    is_admin enum('y','n') default 'n'
);

create table movie(
	movie_id int unique not null primary key auto_increment,
    movie_name varchar(255) not null,
	movie_rating enum('G','PG','PG13','R'),
	movie_release_date date default null,
    movie_time_minutes int,
    movie_cost double default null
);

create table user_movie(
	mtm_id int unique not null primary key auto_increment,
    user_id int, 
	foreign key(user_id) references `user`(user_id) on delete cascade,
    movie_id int, 
	foreign key(movie_id) references movie(movie_id) on delete cascade,
    movie_progress_minutes int default 0,
    movie_starRating enum('1','2','3','4', '5', 'no rating yet')
		default 'no rating yet'
);

create table watchlist(
	watchlist_id int unique not null primary key auto_increment,
    watchlist_name varchar(255) not null,
    user_id int not null,
	foreign key(user_id) references `user`(user_id) on delete cascade,
    mtm_id int not null,
	foreign key(mtm_id) references user_movie(mtm_id) on delete cascade
);

#ADD USER
INSERT INTO `user`(user_username, user_firstName, user_lastName, user_email) VALUES ("user1","Alice","Anderson", "");
INSERT INTO `user`(user_username, user_firstName, user_lastName, user_email) VALUES ("user2","Bob","barker", "");
INSERT INTO `user`(user_username, user_firstName, user_lastName, user_email) VALUES ("user3","Carol","Cantrel", "");
INSERT INTO `user`(user_username, user_firstName, user_lastName, user_email) VALUES ("user4","Doug","Dennis", "");
INSERT INTO `user`(user_username, user_firstName, user_lastName, user_email) VALUES ("user5","Erin","Ekleson", "");

INSERT INTO `movie`(movie_name, movie_rating, movie_time_minutes) VALUES ("movie1","G",96);
INSERT INTO `movie`(movie_name, movie_rating, movie_time_minutes) VALUES ("movie2","PG",118);
INSERT INTO `movie`(movie_name, movie_rating, movie_time_minutes) VALUES ("movie3","PG13",143);
INSERT INTO `movie`(movie_name, movie_rating, movie_time_minutes) VALUES ("movie4","R",87);

insert into `user_movie`(user_id, movie_id) values(1,2);
insert into `user_movie`(user_id, movie_id) values(1,4);
insert into `user_movie`(user_id, movie_id) values(2,1);
insert into `user_movie`(user_id, movie_id) values(2,3);
insert into `user_movie`(user_id, movie_id) values(2,4);
insert into `user_movie`(user_id, movie_id) values(3,1);
insert into `user_movie`(user_id, movie_id) values(3,4);
insert into `user_movie`(user_id, movie_id) values(4,1);
insert into `user_movie`(user_id, movie_id) values(5,2);
insert into `user_movie`(user_id, movie_id) values(5,3);
insert into `user_movie`(user_id, movie_id) values(5,4);


insert into `watchlist`(watchlist_name, mtm_id, user_id) values("gregory", 1, (select user_id from user_movie where mtm_id=1));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("phyllis", 2, (select user_id from user_movie where mtm_id=2));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("eleanore", 3, (select user_id from user_movie where mtm_id=3));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("maybell", 4, (select user_id from user_movie where mtm_id=4));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("genovieve", 5, (select user_id from user_movie where mtm_id=5));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("bernadette", 2, (select user_id from user_movie where mtm_id=2));
insert into `watchlist`(watchlist_name, mtm_id, user_id) values("clarice", 3, (select user_id from user_movie where mtm_id=3));


select * from `user`;
select * from movie ;

#get All
select movie_name as title, 
			concat(`user`.user_firstName, " ", `user`.user_lastName) as 'name', 
			user_movie.movie_progress_minutes as watch_time, 
            movie.movie_time_minutes as total_time,
            user_movie.movie_starRating as rating from user_movie 
join `user` on `user`.user_id=user_movie.user_id
join movie on movie.movie_id=user_movie.movie_id
where `user`.user_id = 3;

#get one by id
select movie_name as title, 
			`user`.user_username as 'username', 
			concat(`user`.user_firstName, " ", `user`.user_lastName) as 'name', 
			user_movie.movie_progress_minutes as watch_time, 
            movie.movie_time_minutes as total_time,
            user_movie.movie_starRating as rating from user_movie 
join `user` on `user`.user_id=user_movie.user_id
join movie on movie.movie_id=user_movie.movie_id
where `user`.user_id = 3 and user_movie.mtm_id = 6;

#add user movie
insert into `user_movie`(user_id, movie_id, movie_progress_minutes, movie_starRating) values(5, 4, 0, 'no rating yet');
select * from user_movie;

#delete user movie
delete from `user_movie` where mtm_id = 2;

#update user movie
update `user_movie` set 
	user_id=1,
    movie_id=1,
    movie_progress_minutes = 12,
    movie_starRating = 'no rating yet'
where mtm_id = 12;
select * from user_movie;
describe user_movie;

select *, playlist_name from playlist_item
join movie_user on playlist_item.item_id=movie_user.id
join playlist on playlist_item.playlist_id=playlist.id
where playlist_item.playlist_id = 3 
and movie_user.user_id = 3;
