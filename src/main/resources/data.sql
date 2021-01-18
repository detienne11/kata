-- create sequence hibernate_sequence start with 1 increment by 1;
/* create table accounts (
	id bigint not null, 
	active boolean not null, 
	primary key (id)
);
create table banking_transactions (
	id bigint not null,
	amount double not null,
	date timestamp not null,
	type varchar(255) not null,
	account_id bigint not null, 
	primary key (id)
);
alter table banking_transactions add constraint FK5c3tukr4s1avnw4jlm7gqgvfl foreign key (account_id) references accounts;*/

insert into accounts values (10011100099,1100.53,true);
insert into accounts values (20022200066,0,false);
insert into accounts values (40044400011,500.00,true);

insert into banking_transactions values (1,1100.00,{ts '2021-01-10 12:00:00.00'},'deposit',10011100099);
insert into banking_transactions values (2,40.53,{ts '2021-01-12 18:47:00.69'},'deposit',10011100099);
insert into banking_transactions values (3,40.00,{ts '2021-01-13 09:30:52.00'},'withdraw',10011100099);
insert into banking_transactions values (4,500.00,{ts '2021-01-01 13:00:00.00'},'deposit',40044400011);
