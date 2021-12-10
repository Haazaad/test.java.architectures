create table users
(
    id          bigserial,
    username    varchar(30) not null unique,
    password    varchar(30) not null,
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp,
    primary key (id)
);

create table orders
(
    id         bigserial,
    user_id    bigint  not null references users (id),
    order_date timestamp default current_timestamp,
    price      numeric not null,
    primary key (id)
);

create table products
(
    id          bigserial,
    title       varchar(256) not null,
    price       numeric      not null,
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp,
    primary key (id)
);

create table order_items
(
    id                 bigserial,
    order_order_id     bigint  not null references orders (id),
    product_product_id bigint  not null references products (id),
    quantity           int     not null,
    product_price      numeric not null,
    total_price        numeric not null,
    primary key (id)
);


insert into users(username, password)
values ('user', '1111'),
       ('admin', '1111');

insert into products (title, price)
values ('First', 256),
       ('Second', 43.22),
       ('Third', 23),
       ('Bread', 12.21),
       ('Milk', 23.02),
       ('Beer', 56.89),
       ('Onion', 32.12),
       ('Potato', 34.12),
       ('Watermelon', 25),
       ('Apple', 12),
       ('Spread', 123.12),
       ('Monitor', 4321.85),
       ('Notebook', 24856.32),
       ('TV', 123412.2),
       ('Mouse', 321),
       ('Phone', 12386),
       ('Keyboard', 453),
       ('Water', 18.32),
       ('Meet', 319),
       ('Oil', 654),
       ('Pasta', 89);

insert into orders(user_id, price)
values (1, 146.78);

insert into order_items(order_order_id, product_product_id, quantity, product_price, total_price)
values (1, 2, 3, 13.33, 43),
       (1, 4, 2, 72, 103.78);