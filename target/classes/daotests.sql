insert into users values(1, 'joanna', false, true, 'pass');

insert into users values(2, 'joakim', true, false, 'pass');

insert into bench_lifts(reps, sets, weight_lifted, date_lifted, is_bench, user_id) values(1, 1, 140, null, true, 1);

insert into bench_lifts(reps, sets, weight_lifted, date_lifted, is_bench, user_id) values(1, 1, 110, null, true, 2);

insert into squat_lifts(reps, sets, weight_lifted, date_lifted, is_squat, user_id) values(1, 1, 100, null, true, 2);

insert into deadlift_lifts(reps, sets, weight_lifted, date_lifted, is_deadlift, user_id) values(1, 1, 180, null, true, 2);