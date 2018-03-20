create table users
(
	user_id int not null,
	username varchar(30) not null,
	is_male boolean,
	is_female boolean,
	pw varchar(30) not null,
	primary key(user_id)
);

create table bench_lifts
(
	bench_id int not null auto_increment,
	reps int,
	sets int,
	weight_lifted int,
	date_lifted date,
	is_bench boolean,
	user_id int not null,
	primary key(bench_id),
	foreign key(user_id) references users(user_id)
);

create table squat_lifts
(
	squat_id int not null auto_increment,
	reps int,
	sets int,
	weight_lifted int,
	date_lifted date,
	is_squat boolean,
	user_id int not null,
	primary key(squat_id),
	foreign key(user_id) references users(user_id)
);

create table deadlift_lifts
(
	deadlift_id int not null auto_increment,
	reps int,
	sets int,
	weight_lifted int,
	date_lifted date,
	is_deadlift boolean,
	user_id int not null,
	primary key(deadlift_id),
	foreign key(user_id) references users(user_id)
);