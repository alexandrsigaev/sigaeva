CREATE TABLE IF NOT EXISTS item
(id serial PRIMARY KEY,name VARCHAR (200),
description TEXT,created TIMESTAMP
);
CREATE TABLE IF NOT EXISTS comments
(id serial PRIMARY KEY,massege TEXT,
date_of_comment TIMESTAMP,
id_item INTEGER REFERENCES item(id)
);