DROP TABLE IF EXISTS product, supplier, prodCat,"Order",user_information,OrderItem;

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

CREATE TABLE user_information
(
    id       SERIAL PRIMARY KEY,
    fullName varchar NOT NULL,
    username varchar NOT NULL,
    email    varchar NOT NULL,
    password varchar NOT NULL
);


-- CREATE TABLE "Order"
-- (
--     id            SERIAL PRIMARY KEY,
--     date          timestamp,
--     orderSubtotal DECIMAL,
--     user_id       Int REFERENCES user_information (id),
--     payed         BOOLEAN
-- );
--
-- CREATE TABLE OrderItem
-- (
--     ItemID       SERIAL PRIMARY KEY,
--     OrderID      Int REFERENCES postgres.public."Order" (id),
--     ProductID    Int,
--     ProductPrice Decimal,
--     Qty          Int
-- );

INSERT INTO supplier (name, description)
VALUES ('Amazon', 'Digital content and services');
INSERT INTO supplier (name, description)
VALUES ('Lenovo', 'Computers');
INSERT INTO supplier (name, description)
VALUES ('Apple', 'Computers and Digital content');
INSERT INTO supplier (name, description)
VALUES ('Mustang', 'Accessories');

INSERT INTO prodcat (name, department, description)
VALUES ('Tablet', 'Hardware',
        'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO prodcat (name, department, description)
VALUES ('Laptop', 'Hardware',
        'A laptop computer, sometimes called a notebook computer by manufacturers, is a battery- or AC-powered personal computer generally smaller than a briefcase that can easily be transported and conveniently used in temporary spaces such as on airplanes, in libraries, temporary offices, and at meetings.');
INSERT INTO prodcat (name, department, description)
VALUES ('Accessories', 'Hardware', 'Other products, accessories.');

INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',
        49, 1, 1);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Lenovo IdeaPad Miix 700',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479, 2, 1);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 89, 1, 1);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Apple - MacBook Air 13.3',
        'The latest MacBook Air features a stunning Retina display with True Tone technology, Touch ID, the latest Apple-designed keyboard, and a Force Touch trackpad - all housed in a thin and light iconic wedge design made from 100 percent recycled aluminum. And with 12-hour battery life, it''s a do-it-all notebook that goes all day long.',
        999, 3, 2);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Lenovo ThinkPad Yoga 370 Touch Laptop',
        'Combining power, performance, and portability, the stylish yet durable ThinkPad Yoga 370 gives you the freedom to work/play anywhere. Enjoy using it in different ways-from laptop to tablet and anything in between. Save time by writing, highlighting, or drawing directly on screen. Take delight in seeing your visuals, presentations, and movies come to life on the vibrant 13.3 display. And then relax knowing the Yoga 370 has an all-day battery life and comes with a worldwide warranty.',
        750, 2, 2);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Apple iPad Pro (12.9-inch, Wi-Fi, 256GB)',
        'Immensely powerful, portable, and capable, the 12.9-inch iPad Pro features a redesigned Retina display that is the most advanced on the planet.',
        989, 3, 1);
INSERT INTO product (name, description, price, supplier_id, prodcat_id)
VALUES ('Java 50', 'The ultimate tool if it comes to change geoposition and you are a developer.', 9999, 4, 3);
