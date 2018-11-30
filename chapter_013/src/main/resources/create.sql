CREATE DATABASE item_db
CREATE TABLE role
(
	id serial primary key,
	name varchar(2000)
)
CREATE TABLE rules
(
	id serial primary key,
	name varchar(2000)
)
CREATE TABLE role_rules
(
	id serial primary key,
	role_id int reference role(id),
	rules_id int reference rules(id)
)
CREATE TABLE user
(
	id serial primary key,
	login varchar(2000),
	password varchar(2000),
	role_id int reference role(id)
)
CREATE TABLE category
(
	id serial primary key,
	name varchar(2000)
)
CREATE TABLE state
(
	id serial primary key,
	name varchar(2000)
)
CREATE TABLE item
(
	id serial primary key,
	name varchar(2000),
	user_id int reference user(id),
	category_id int reference category(id),
	state_id int reference state(id)
)
CREATE TABLE comments
(
	id serial primary key,
	data text,
	item_id int reference item(id)
)
CREATE TABLE attachs
(
	id serial primary key,
	path varchar(2000),
	item_id int reference item(id)
)

INSERT INTO role 
VALUES 
("autor")
INSERT INTO rules 
VALUES 
("write"),
("reed")
INSERT INTO user 
VALUES 
("root", "root", "1"),
("user124", "qwerty", "2")
INSERT INTO category 
VALUES 
("")
INSERT INTO state
VALUES 
("open"),
("pause"),
("done");