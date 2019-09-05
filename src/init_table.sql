DROP TABLE IF EXISTS product, supplier, prodCat,supplier,user_details,"order",order_details;

CREATE TABLE supplier
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE,
    department  varchar(50),
    description varchar(200)
);

CREATE TABLE prodCat
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE,
    department  varchar(50),
    description varchar(500)
);

CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        varchar(150) UNIQUE,
    description varchar(500),
    price       float,
    supplier_id int REFERENCES supplier (id),
    prodCat_id  int REFERENCES prodCat (id)
);

CREATE TABLE user_details
(
    id          SERIAL PRIMARY KEY,
    fullname    varchar,
    username    varchar,
    email       varchar,
    password    varchar,
    phonenumber varchar,
    address     varchar,
    city        varchar,
    state       varchar,
    zip         varchar
);

CREATE TABLE "order"
(
    id      serial PRIMARY KEY,
    user_id int REFERENCES user_details (id),
    status  varchar
);

CREATE TABLE order_details
(
    id       SERIAL PRIMARY KEY,
    prod_id  INT REFERENCES product (id),
    quantity INT,
    order_id INT REFERENCES "order" (id)
);

INSERT INTO supplier(name, department, description)
VALUES ('Amazon', 'Digital content and services', 'Amazon.com Inc.');

INSERT INTO supplier(name, department, description)
VALUES ('Lenovo', 'Computers', 'Lenovo Inc.');

INSERT INTO supplier(name, department, description)
VALUES ('Apple', 'Computers and Digital content', 'Apple Inc.');

INSERT INTO supplier(name, department, description)
VALUES ('Mustang', 'Motorbikes', 'Mustang Inc.');


INSERT INTO prodCat (name, department, description)
VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly ' ||
                              'shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO prodCat (name, department, description)
VALUES ('Laptop', 'Hardware', 'A laptop computer, sometimes ' ||
                              'called a notebook computer by manufacturers, is a battery- or AC-powered personal ' ||
                              'computer generally smaller than a briefcase that can easily be transported and ' ||
                              'conveniently used in temporary spaces such as on airplanes, in libraries, temporary ' ||
                              'offices, and at meetings.');
INSERT INTO prodCat (name, department, description)
VALUES ('Accessories', 'Hardware', 'Other products, accessories.');

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',
        '49', 1, 1);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Lenovo IdeaPad Miix 700',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', '479', 2,
        1);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', '89', 1, 1);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Apple - MacBook Air 13.3"',
        'The latest MacBook Air features a stunning Retina display with True Tone technology, Touch ID, the ' ||
        'latest Apple-designed keyboard, and a Force Touch trackpad - all housed in a thin and light iconic wedge' ||
        ' design made from 100 percent recycled aluminum. And with 12-hour battery life, it''s a do-it-all notebook' ||
        ' that goes all day long.',
        '999', 3, 2);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Lenovo ThinkPad Yoga 370 Touch Laptop',
        'Combining power, performance, and portability, the stylish yet durable ThinkPad Yoga 370 gives you the ' ||
        'freedom to work/play anywhere. Enjoy using it in different ways-from laptop to tablet and anything in ' ||
        'between. Save time by writing, highlighting, or drawing directly on screen. Take delight in seeing your ' ||
        'visuals, presentations, and movies come to life on the vibrant 13.3" display. And then relax knowing the ' ||
        'Yoga 370 has an all-day battery life and comes with a worldwide warranty.',
        '750', 2, 2);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Apple iPad Pro (12.9-inch, Wi-Fi, 256GB) ',
        'Immensely powerful, portable, and capable, the 12.9-inch iPad Pro features a redesigned Retina display' ||
        ' that is the most advanced on the planet.',
        '989', 3, 1);

INSERT INTO product(name, description, price, supplier_id, prodCat_id)
VALUES ('Java 50', 'The ultimate tool if it comes to change geoposition and you are a developer.', '9999', 4, 3);