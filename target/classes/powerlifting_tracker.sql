create table users
(
	user_id int not null auto_increment,
	username varchar(30) not null,
	is_male boolean,
	is_female boolean,
	pw varchar(30) not null,
	primary key(user_id)
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
	user_id int not null,
	primary key(lift_id),
	foreign key(user_id) references users(user_id)
);