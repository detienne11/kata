--create sequence hibernate_sequence start with 1 increment by 1
--create table accounts (id bigint not null, active boolean not null, primary key (id))
--create table banking_transactions (id bigint not null, amount double not null, date timestamp not null, type varchar(255) not null, account_id bigint not null, primary key (id))
--alter table banking_transactions add constraint FK5c3tukr4s1avnw4jlm7gqgvfl foreign key (account_id) references accounts
