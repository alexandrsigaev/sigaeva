CREATE DATABASE item_db;

CREATE TABLE role
(
	id serial PRIMARY KEY,
	name VARCHAR (2000)
);
CREATE TABLE rules
(
	id serial PRIMARY KEY,
	name VARCHAR (2000)
);
CREATE TABLE role_rules
(
	id serial PRIMARY KEY,
	role_id INT REFERENCES role(id),
	rules_id INT REFERENCES rules(id)
);
CREATE TABLE user
(
	id serial primary key,
	login VARCHAR (2000),
	password VARCHAR (2000),
	role_id IS NOT NULL INT REFERENCES role(id)
);
CREATE TABLE category
(
	id serial PRIMARY KEY,
	name VARCHAR (2000)
);
CREATE TABLE state
(
	id serial PRIMARY KEY,
	name VARCHAR(2000)
);
CREATE TABLE item
(
	id serial PRIMARY KEY,
	name VARCHAR(2000),
	description VARCHAR (2000),
	created TIMESTAMP,
	user_id int REFERENCES user(id),
	category_id INT REFERENCES category(id),
	state_id INT REFERENCES state(id)
);
CREATE TABLE comments
(
	id serial PRIMARY KEY,
	data MESSAGE_TEXT,
	item_id IS NOT NULL INT REFERENCES item(id)
);
CREATE TABLE attachs
(
	id serial PRIMARY KEY,
	path VARCHAR(2000),
	item_id INT REFERENCES item(id)
);

INSERT INTO role 
VALUES 
('autor');
INSERT INTO rules 
VALUES 
('write'),
('reed');
INSERT INTO user 
VALUES 
('admin', 'admin', '1'),
('aleksandr', 'qwerty', '2');
INSERT INTO category 
VALUES 
('');
INSERT INTO state
VALUES 
('open'),
('pause'),
('done');