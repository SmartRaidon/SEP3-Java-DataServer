CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP SCHEMA sep3 CASCADE;

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    username VARCHAR (30) NOT NULL,
    password VARCHAR (50) NOT NULL,
    email TEXT NOT NULL UNIQUE,
    points INTEGER
    );

drop table users;

INSERT INTO users(username, password, email) VALUES ('example','example123','email@example.com');


