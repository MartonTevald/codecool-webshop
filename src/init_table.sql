DROP TABLE IF EXISTS product, supplier, prodCat;

CREATE TABLE supplier(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    description varchar(200));

CREATE TABLE prodCat(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    department varchar(50),
    description varchar(200));

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name varchar(50) UNIQUE ,
    description varchar(200),
    price float8,
    supplier_id serial REFERENCES supplier(id),
    prodCat_id serial REFERENCES prodCat(id)


                    );
