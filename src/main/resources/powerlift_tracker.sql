create table users
(
	user_id int not null auto_increment,
	username varchar(30) not null,
	password varchar(30) not null,
	primary key(user_id)
);

create table user_stats
(
	user_stat_id int not null auto_increment,
	user_id int not null,
	primary key(user_stat_id),
	foreign key(user_id) references users(user_id)
);

create table user_stats_details
(
	details_id int not null auto_increment,
	weight int,
	is_male boolean,
	is_female boolean,
	user_stat_id int not null,
	primary key(details_id),
	foreign key(user_stat_id) references user_stats(user_stat_id)
);

create table lifts
(
	lift_id int not null auto_increment,
	reps int,
	sets int,
	weight_lifted int,
	date_lifted date,
	is_bench boolean,
	is_squat boolean,
	is_deadlift boolean,
	user_stat_id int not null,
	primary key(lift_id),
	foreign key(user_stat_id) references user_stats(user_stat_id)
);