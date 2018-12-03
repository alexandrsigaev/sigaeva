-- 1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT * FROM product INNER JOIN type ON product.type_id = type.id
WHERE type.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

SELECT * FROM product
WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

SELECT * FROM product
WHERE expired_date BETWEEN CURRENT_TIMESTAMP AND CURRENT_TIMESTAMP + INTERVAL '1 mons';

-- 4. Написать запрос, который выводит самый дорогой продукт.

SELECT * FROM product
ORDER BY price DESC
LIMIT 1;

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT type.name AS type_name, COUNT(product.name) AS amount
FROM product INNER JOIN type ON product.type_id = type.id
GROUP BY type.name;


-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

SELECT * FROM product INNER JOIN type ON product.type_id = type.id
WHERE type.name = 'СЫР' OR type.name = 'МОЛОКО';


-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 

SELECT type.name AS type_name
FROM type INNER JOIN product ON product.type_id = type.id
GROUP BY type.name
HAVING COUNT(product.name) < 10;

-- 8. Вывести все продукты и их тип.

SELECT DISTINCT product.name AS name_product, expired_date, price, type.name AS name_type 
FROM product INNER JOIN type ON product.type_id = type.id
ORDER BY name_type;
