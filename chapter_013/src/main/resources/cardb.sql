CREATE DATABASE car_db;

CREATE TABLE carcase
(
	id serial primary key,
	number varchar(20),
	name varchar(200),
	type varchar(200),
	date_of_manufacture DATE
);
CREATE TABLE engine
(
	id serial primary key,
	number varchar(20),
	name varchar(200),
	capacity DECIMAL(2,1),
	power int,
	date_of_manufacture DATE
);

CREATE TABLE transmission
(
	id serial primary key,
	number varchar(20),
	name varchar(200),
	type char(1),
	date_of_manufacture DATE
);

CREATE TABLE car
(
	id serial primary key,
	carcase_id int references carcase(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id),
	name varchar(200),
	date_of_manufacture DATE
);

INSERT INTO carcase (number, name, type, date_of_manufacture)
VALUES 
('asde43534556', 'ptry', 'hatchback', '2018-10-15'),
('asde43534556', 'ghghj', 'pickup', '2018-10-05'),
('aser45758656', 'hgmfhj,m', 'station wagon', '2018-11-09'),
('asrt43434365', 'ghdtyd', 'minivan', '2018-08-02'),
('asde45635654', 'yfhmfhm', 'sedan', '2017-01-20'),
('asde35364486', 'gbgnfhm', 'crossover', '2018-06-04');

INSERT INTO engine (number, name, capacity, power, date_of_manufacture)
VALUES 
('bsde76786785', 'kklkkjy', 1.6,  120,'2018-08-10'),
('bsde96645353', 'fdzdffhs', 2.0,  170,'2018-07-15'),
('bser55676843', 'dfvbgndgn', 2.5,  200,'2018-11-09'),
('bsrt68567334', 'gndghmdg', 3.0,  300,'2018-08-04'),
('bsde54746787', 'jdytjdty', 5.0,  450,'2017-02-02');

INSERT INTO transmission (number, name, type, date_of_manufacture)
VALUES 
('csde43534556', 'dfbfng', 'M', '2018-10-15'),
('csde43534556', 'hjkgj,k', 'A', '2018-10-05'),
('cser45758656', 'hdtydhyt,m', 'A', '2018-11-09'),
('csrt43434365', 'dyjjdyjj', 'M', '2018-08-02');

INSERT INTO car (carcase_id, engine_id, transmission_id, name, date_of_manufacture)
VALUES 
(1, 2, 2, 'kklkkjy','2018-08-10'),
(2, 4, 3, 'fdzdffhs', '2018-07-15'),
(3, 2, 1, 'dfvbgndgn', '2018-11-09'),
(4, 5, 3, 'gndghmdg', '2018-08-04'),
(6, 2, 4, 'jdytjdty', '2017-02-02');


--DROP TABLE car;
--DROP TABLE transmission;
--DROP TABLE engine;
--DROP TABLE carcase;


SELECT car.name AS car_name, carcase.name AS carcase_name, engine.name AS engine_name,  transmission.name FROM car 
LEFT OUTER JOIN carcase ON car.carcase_id = carcase.id
LEFT OUTER JOIN engine ON car.engine_id = engine.id
LEFT OUTER JOIN transmission ON car.transmission_id = transmission.id;


SELECT carcase.name AS name FROM carcase 
LEFT OUTER JOIN car ON car.carcase_id = carcase.id
WHERE car.name IS NULL
UNION
SELECT engine.name AS name FROM engine
LEFT OUTER JOIN car ON car.engine_id = engine.id
WHERE car.name IS NULL
UNION
SELECT transmission.name AS name FROM transmission
LEFT OUTER JOIN car ON car.transmission_id = transmission.id
WHERE car.name IS NULL;

