DROP TABLE IF EXISTS Product, Supplier, prodCat;

CREATE TABLE Supplier(
    supplier_id int PRIMARY KEY ,
    name VARCHAR(50),
    description varchar(200));

CREATE TABLE prodCat(
    prodCat_id int PRIMARY KEY ,
    name VARCHAR(50),
    department varchar(50),
    description varchar(200));

CREATE TABLE Product(
    id VARCHAR(4) PRIMARY KEY,
    name varchar(50),
    description varchar(200),
    price float8,
    supplier_id int REFERENCES Supplier(supplier_id),
    prodCat_id int REFERENCES prodCat(prodCat_id));
