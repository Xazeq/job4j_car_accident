CREATE TABLE IF NOT EXISTS accident_types (
    id serial primary key,
    name varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS rules (
    id serial primary key,
    name varchar(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS accidents (
    id serial primary key,
    name varchar(250) NOT NULL,
    text text NOT NULL,
    address varchar(250) NOT NULL,
    accident_type_id int references accident_types(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS accidents_rules (
    accident_id int references accidents(id) NOT NULL,
    rule_id int references rules(id) NOT NULL
);

INSERT INTO accident_types(name)
VALUES ('Две машины'), ('Машина и человек'), ('Машина и велосипед');

INSERT INTO rules(name)
VALUES ('Статья. 1'), ('Статья. 2'), ('Статья. 3');

INSERT INTO accidents(name, text, address, accident_type_id)
VALUES ('Столкнулись два автомобиля',
        'В 12:35 на перекрестке произошло ДТП',
        'Площадь Пушкина', 1),
       ('Автомобиль сбил пешехода',
        'Водитель Lada Vesta не заметил пешехода на зебре',
        'улица Гагарина, около дома №12', 2),
       ('ДТП с выездом на встречную полосу',
        'Водитель не справился с управление, допустил выезд па полосу встречного движения и врезался в автомобиль Renault Logan',
        'проспект Победы, возле дома №45', 1);

INSERT INTO accidents_rules (accident_id, rule_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (3, 1),
       (3, 2);