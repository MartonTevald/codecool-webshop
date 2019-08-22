DROP TABLE IF EXISTS product, supplier, prodCat;

CREATE TABLE supplier(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    department varchar(50),
    description varchar(200));

CREATE TABLE prodCat(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    department varchar(50),
    description varchar(500));

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name varchar(150) UNIQUE ,
    description varchar(500),
    price float,
    supplier_id int  REFERENCES supplier(id),
    prodCat_id int REFERENCES prodCat(id));

-- CREATE TABLE user_information(
--     id SERIAL PRIMARY KEY
-- );
--
-- CREATE TABLE invoice(
--     id SERIAL PRIMARY KEY,
--     order_id int REFERENCES order(id)
-- );
--
-- CREATE TABLE order(
--     id SERIAL PRIMARY KEY ,
--     product_id int REFERENCES product(id),
--     count int,
--     user_id int REFERENCES user_information(id),
--     ord_number int,
--     closed boolean
--
-- );
