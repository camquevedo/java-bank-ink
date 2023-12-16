CREATE TABLE IF NOT EXISTS statuses (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL,
    CONSTRAINT statuses_name_uniq UNIQUE (name)
);
INSERT INTO statuses (name) values ('Inactive'), ('Active'), ('Blocked'), ('Lost'), ('Pending'), ('Canceled');

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    number INTEGER NOT NULL,
    name VARCHAR(64) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL,
    CONSTRAINT products_number_uniq UNIQUE (number)
);
INSERT INTO products (number, name) values (1234, 'Visa'), (4321, 'Mastercard'), (1111, 'AMEX'), (2222, 'xv6');

CREATE TABLE IF NOT EXISTS cards (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status INTEGER NOT NULL,
    number INTEGER NOT NULL,
    balance INTEGER NOT NULL,
    type SMALLINT NOT NULL,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    expiration_date DATETIME NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL,
    CONSTRAINT products_number_uniq UNIQUE (number)
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status INTEGER NOT NULL,
    type SMALLINT NOT NULL,
    balance INTEGER NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL
);
