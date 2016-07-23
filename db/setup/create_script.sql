create table categories (
	category_id integer not null,
	category_name varchar(250) not null,
	constraint categories_pk primary key(category_id)
)

create table questions (
	category_id integer not null,
	question varchar(1000) not null,
	question_type varchar(250) not null,
	answer varchar(1000) not null,
	difficulty integer not null,
	constraint questions_categories_fk foreign key(category_id)
	references categories(category_id),
	constraint difficulty_chk check(difficulty > 0 and difficulty < 6)
)
