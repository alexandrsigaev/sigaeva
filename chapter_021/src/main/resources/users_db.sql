CREATE_TABLE_USERS=CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY, name VARCHAR (200), login VARCHAR(200), password VARCHAR(200), email VARCHAR(200), role VARCHAR(20), creatDate TIMESTAMP);
ADD_USER=INSERT INTO users (name, login, password, email, role, creatDate) VALUES (?, ?, ?, ?, ?);
SELECT_ALL=SELECT * FROM users;
FIND_USER_BY_LOGIN=SELECT id FROM users WHERE login = ?;
FIND_USER_BY_ID=SELECT * FROM users WHERE id = ?;
UPDATE_USER=UPDATE users SET name = ?, login = ?, password = ?, email = ? , role = ? WHERE id = ?;
DELETE_USER=DELETE FROM users WHERE id = ?;
