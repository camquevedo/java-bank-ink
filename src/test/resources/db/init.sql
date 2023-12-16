CREATE TABLE IF NOT EXISTS statuses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    CONSTRAINT statuses_name_uniq UNIQUE (name)
);
INSERT INTO statuses (name) values ('Inactive'), ('Active'), ('Blocked'), ('Lost'), ('Pending');

CREATE TABLE IF NOT EXISTS products (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     number INTEGER NOT NULL,
     name VARCHAR(64) NOT NULL,
     CONSTRAINT products_number_uniq UNIQUE (number)
 );
 insert into products (number, name) values (1234, 'Visa'), (4321, 'Mastercard'), (1111, 'AMEX'), (2222, 'xv6');