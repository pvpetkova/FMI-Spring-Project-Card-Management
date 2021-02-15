alter table cms.key
    change column kry_name
        key_name
            varchar(32);

alter table cms.`key`
change column key_value
    key_value
varchar(48);

alter table cms.`key` add column bin_id integer;
alter table cms.`key` add foreign key (bin_id) references bin(id);

insert into bin_range(id, starting_bin, end_bin, description)
VALUES (1, '550000', '559999', 'mastercard default');
insert into bin_range(id, starting_bin, end_bin, description)
VALUES (2, '440000', '449999', 'visa default');
commit;

insert into bin (id, bin, description, bin_range)
values (1, '555555', 'mastercardTest', 1);
insert into bin (id, bin, description, bin_range)
values (2, '444444', 'visaTest', 2);
commit ;

insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
 values (id,'555555:enc', '112233445566778811223344556677881122334455667788', 0,0,1);
insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
values (1,'555555:dec', '112233445566778811223344556677881122334455667788', 0,1,1);
insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
values (2,'555555:pin', '112233445566778811223344556677881122334455667788', 0,2,1);

insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
values (3,'444444:enc', '112233445566778811223344556677881122334455667788', 0,0,2);
insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
values (4,'444444:dec', '112233445566778811223344556677881122334455667788', 0,1,2);
insert into cms.`key` (id, key_name, key_value, key_type, key_usage, bin_id)
values (5,'444444:pin', '112233445566778811223344556677881122334455667788', 0,2,2);

commit;

alter table bin
    drop foreign key bin_bin_range_id_fk;
alter table card
    drop foreign key card_bin_id_fk;
alter table cms.`key`
    drop foreign key key_ibfk_1;
alter table request
    drop foreign key request_card_id_fk;
alter table request
    drop foreign key request_user_id_fk;
alter table bin
    modify id int auto_increment;
alter table bin_range
    modify id int auto_increment;
alter table card
    modify id int auto_increment;
alter table cms.key
    modify id int auto_increment;
alter table request
    modify id int auto_increment;
alter table user
    modify id int auto_increment;

alter table bin
    add constraint bin_bin_range_id_fk foreign key (bin_range) references bin_range (id);
alter table card
    add constraint card_bin_id_fk foreign key (bin_id) references bin (id);
alter table cms.`key`
    add constraint key_ibfk_1 foreign key (bin_id) references bin (id);
alter table request
    add constraint request_card_id_fk foreign key (request_subject) references card (id);
alter table request
    add constraint request_user_id_fk foreign key (issuer) references user (id);



create table user_change_request
(
	id int null,
	user_id int null,
	requested_role int null,
	status int null,
	constraint user_change_request_pk
		primary key (id)
);

alter table user_change_request
    add constraint user_change_request_user_id_fk
        foreign key (user_id) references user (id);

commit;