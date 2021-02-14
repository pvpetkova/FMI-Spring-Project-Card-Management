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
