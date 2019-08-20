DROP TABLE IF EXISTS product, supplier, prodCat;

CREATE TABLE supplier(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    description varchar(200));

CREATE TABLE prodCat(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    department varchar(50),
    description varchar(500));

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name varchar(150) ,
    description varchar(500),
    price float8,
    supplier_id int  REFERENCES supplier(id),
    prodCat_id int REFERENCES prodCat(id)


                    );
