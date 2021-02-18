create or replace schema cms collate utf8mb4_general_ci;

create or replace table bin_range
(
	id int auto_increment
		primary key,
	starting_bin varchar(6) null,
	end_bin varchar(6) null,
	description varchar(125) null
);

create or replace table bin
(
	id int auto_increment
		primary key,
	bin varchar(6) null,
	description varchar(250) null,
	bin_range int null,
	constraint bin_bin_range_id_fk
		foreign key (bin_range) references bin_range (id)
);

create or replace table card
(
	id int auto_increment
		primary key,
	pan varchar(48) null,
	card_holder_name varchar(32) null,
	pin_block varchar(16) null,
	cvv varchar(16) null,
	expiry_date date null,
	card_status tinyint null,
	bin_id int null,
	constraint card_bin_id_fk
		foreign key (bin_id) references bin (id)
);

create or replace table symmetric_key
(
	id int auto_increment
		primary key,
	key_name varchar(32) null,
	key_value varchar(48) null,
	key_type int null,
	key_usage int null,
	bin_id int null,
	constraint symmetric_key_ibfk_1
		foreign key (bin_id) references bin (id)
);

create or replace index key_ibfk_1
	on symmetric_key (bin_id);

create or replace table user
(
	id int auto_increment
		primary key,
	user_name varchar(32) null,
	password varchar(125) null,
	role int null,
	account_status int null
);

create or replace table request
(
	id int auto_increment
		primary key,
	issuer int null,
	request_type int null,
	request_status int null,
	reason varchar(250) null,
	request_subject int null,
	constraint request_card_id_fk
		foreign key (request_subject) references card (id),
	constraint request_user_id_fk
		foreign key (issuer) references user (id)
);

create or replace table user_change_request
(
	id int auto_increment
		primary key,
	user_id int null,
	requested_role int null,
	status int null,
	constraint user_change_request_user_id_fk
		foreign key (user_id) references user (id)
);

