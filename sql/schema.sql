drop table if exists ProductCharacteristics;
drop table if exists Products;
drop table if exists Categories;
drop table if exists Characteristics;

create  table Categories
(
    category_id serial8 not null,
    category_name varchar not null,
    primary key(category_id)
);

create table Characteristics
(
    characteristic_id serial8 not null,
    category_id int8 references Categories(category_id),
    characteristic_name varchar not null,
    primary key (characteristic_id)
);

create table Products
(
    product_id serial8 not null,
    category_id int8 not null references Categories(category_id),
    product_name varchar not null,
    primary key (product_id)
);

create table ProductCharacteristics
(
    product_characteristic_id serial8 not null,
    product_id int8 not null references Products(product_id),
    characteristic_id int8 not null references Characteristics(characteristic_id),
    value varchar,
    primary key (product_characteristic_id)
);

insert into Categories (category_name) values ('Процессоры');
insert into Categories (category_name) values ('Мониторы');

insert into Characteristics (category_id, characteristic_name)
values  (1, 'Производитель'),
        (1, 'Количество ядер'),
        (1, 'Сокет'),
        (1, 'Стоимость');
insert into Characteristics (category_id, characteristic_name)
values (2, 'Производитель'),
       (2, 'Диагональ'),
       (2, 'Матрица'),
       (2, 'Разрешение'),
       (2, 'Стоимость');

insert into Products (category_id, product_name) values (1, 'Intel Core i7');
insert into Products (category_id, product_name) values (1, 'AMD Ryzen R7 7700');
insert into Products (category_id, product_name) values (2, 'Samsung SU556270');
insert into Products (category_id, product_name) values (2, 'AOC Z215S659');

insert into ProductCharacteristics (product_id, characteristic_id, value)
values  (1, (select 1 from Characteristics where category_id = category_id and characteristic_name = 'Производитель' limit 1), 'Intel'),
        (1, (select 2 from Characteristics where category_id = category_id and characteristic_name = 'Количество ядер' limit 1), '8'),
        (1, (select 3 from Characteristics where category_id = category_id and characteristic_name = 'Сокет' limit 1), 'LGA1200'),
        (1, (select 4 from Characteristics where category_id = category_id and characteristic_name = 'Стоимость' limit 1), '70000');

insert into ProductCharacteristics (product_id, characteristic_id, value)
values (2, (select 1 from Characteristics where category_id = category_id and characteristic_name = 'Производитель' limit 1), 'AMD'),
       (2, (select 2 from Characteristics where category_id = category_id and characteristic_name = 'Количество ядер' limit 1), '12'),
       (2, (select 3 from Characteristics where category_id = category_id and characteristic_name = 'Сокет' limit 1), 'AM4'),
       (2, (select 4 from Characteristics where category_id = category_id and characteristic_name = 'Стоимость' limit 1), '80000');

insert into ProductCharacteristics (product_id, characteristic_id, value)
values (3, (select 5 from Characteristics where category_id = category_id and characteristic_name = 'Производитель' limit 1), 'Samsung'),
       (3, (select 6 from Characteristics where category_id = category_id and characteristic_name = 'Диагональ' limit 1), '27'),
       (3, (select 7 from Characteristics where category_id = category_id and characteristic_name = 'Матрица' limit 1), 'TN'),
       (3, (select 8 from Characteristics where category_id = category_id and characteristic_name = 'Разрешение' limit 1), '2560*1440'),
       (3, (select 9 from Characteristics where category_id = category_id and characteristic_name = 'Стоимость' limit 1), '150000');

insert into ProductCharacteristics (product_id, characteristic_id, value)
values (4, (select 5 from Characteristics where category_id = category_id and characteristic_name = 'Производитель' limit 1), 'AOC'),
       (4, (select 6 from Characteristics where category_id = category_id and characteristic_name = 'Диагональ' limit 1), '21.5'),
       (4, (select 7 from Characteristics where category_id = category_id and characteristic_name = 'Матрица' limit 1), 'AH-IPS'),
       (4, (select 8 from Characteristics where category_id = category_id and characteristic_name = 'Разрешение' limit 1), '1920*1080'),
       (4, (select 9 from Characteristics where category_id = category_id and characteristic_name = 'Стоимость' limit 1), '120000');
