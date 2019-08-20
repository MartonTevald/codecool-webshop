DROP TABLE IF EXISTS Product, Supplier, prodCat;

CREATE TABLE Supplier(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    description varchar(200));

CREATE TABLE prodCat(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(50) UNIQUE ,
    department varchar(50),
    description varchar(200));

CREATE TABLE Product(
    id SERIAL PRIMARY KEY,
    name varchar(50) UNIQUE ,
    description varchar(200),
    price float8,
    supplier_id int REFERENCES Supplier(id),
    prodCat_id int REFERENCES prodCat(id));
