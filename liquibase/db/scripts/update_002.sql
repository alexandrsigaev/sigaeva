CREATE TABLE IF NOT EXISTS users (
id serial PRIMARY KEY, name VARCHAR (200),
login VARCHAR(200),
password VARCHAR(200),
email VARCHAR(200),
creatDate TIMESTAMP
);
