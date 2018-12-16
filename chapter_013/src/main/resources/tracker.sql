CREATE_TABLE_ITEM=CREATE TABLE IF NOT EXISTS item(id serial PRIMARY KEY,name VARCHAR (200),description TEXT,created TIMESTAMP);
CREATE_TABLE_COMMENTS=CREATE TABLE IF NOT EXISTS comments(id serial PRIMARY KEY,massege TEXT,date_of_comment TIMESTAMP,id_item INTEGER REFERENCES item(id));
ADD_ITEM=INSERT INTO item (name, description, created) VALUES (?, ?, ?);
REPLACE_ITEM=UPDATE item SET name = ?, description = ?, created = ? WHERE id = ?;
DELETE_ITEM=DELETE FROM item WHERE id = ?;
DELETE_ALL_COMMENTS_FOR_ITEM=DELETE FROM comments WHERE item_id = ?;
FIND_ALL_ITEMS=SELECT * FROM item;
FIND_ITEM_BY_NAME=SELECT * FROM item WHERE name LIKE ?;
FIND_ITEM_BY_ID=SELECT * FROM item WHERE id = ?;