CREATE TABLE authorities (
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique,
);

CREATE TABLE users (
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$8KtefkPSqiodMhZVh9myfeDaEQJ5IgfELfvFn4zMlxC5yjnM9Gi2W',
        (SELECT id FROM authorities where authority = 'ROLE_ADMIN'));