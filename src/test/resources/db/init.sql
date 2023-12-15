create database bank_inc;

\connect bank_inc;
create table statuses (
  id bigint primary key generated always as identity,
  name character varying(255) not null,

  CONSTRAINT statuses_name_uniq UNIQUE (name)
);
insert into statuses (name) values ('Inactive'), ('Active'), ('Blocked'), ('Lost'), ('Pending');

\connect bank_inc;
 create table products (
   id bigint primary key generated always as identity,
   number integer not null,
   name character varying(255) not null,

   CONSTRAINT products_number_uniq UNIQUE (number)
 );
 insert into products (number, name) values (1234, 'Visa'), (4321, 'Mastercard'), (1111, 'AMEX'), (2222, 'xv6');

\connect bank_inc;
\GRANT CONNECT ON database bank_inc TO walrus;