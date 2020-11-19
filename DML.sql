create schema if not exists public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

CREATE TABLE customer_info
(
    id           serial,
    first_name   VARCHAR(100) NOT NULL,
    last_name    VARCHAR(100),
    age          int CHECK (age > 0),
    birth_day    DATE,
    register_day DATE,
    PRIMARY KEY (id)
);


CREATE TABLE orders
(
    id           serial,
    product_id   int     NOT NULL,
    product_name varchar NOT NULL,
    number       int     NOT NULL,
    price        int     NOT NULL,
    total_cost   int GENERATED ALWAYS AS ( number * price) STORED,
    PRIMARY KEY (id)
);
CREATE TABLE customer_orders
(
    id          serial,
    customer_id int not null,
    order_id    int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                  serial,
    product_name        varchar NOT NULL UNIQUE,
    product_category_id int     NOT NULL,
    total_number        int     NOT NULL,
    price               int     NOT NULL UNIQUE,
    arrival_date        DATE    NOT NULL,
    expiry_date         DATE    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id            serial,
    category_name varchar NOT NULL,
    product_id    int     NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE product_category
    ADD FOREIGN KEY (product_id)
        REFERENCES products (id);


ALTER TABLE customer_orders
    ADD FOREIGN KEY (customer_id)
        REFERENCES customer_info (id),
    ADD FOREIGN KEY (order_id)
        REFERENCES orders (id);

ALTER TABLE products
    ADD FOREIGN KEY (product_category_id)
        REFERENCES product_category (id);


ALTER TABLE orders
    ADD FOREIGN KEY (product_id)
        REFERENCES products (id),
    ADD FOREIGN KEY (product_name)
        REFERENCES products (product_name),
    ADD FOREIGN KEY (price)
        REFERENCES products (price);

INSERT INTO product_category (id,category_name,product_id)
VALUES (1,'Green',1);

INSERT INTO products (id,product_name,product_category_id,total_number,price,arrival_date,expiry_date)
VALUES (1,'Onion',1,5,20,date '2020-10-10',date '2020-10-20');

INSERT INTO orders (product_id,product_name,number,price)
VALUES (1,'Onion',5,20);
