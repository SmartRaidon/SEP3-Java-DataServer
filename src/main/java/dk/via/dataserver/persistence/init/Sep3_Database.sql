CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP SCHEMA sep3 CASCADE;

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY ,
    username VARCHAR (30),
    password VARCHAR (30),
    email TEXT,
    score DOUBLE PRECISION
    );

CREATE TABLE game_results (
    id SERIAL PRIMARY KEY,
    gameID INTEGER NOT NULL UNIQUE,
    winnerID INTEGER NOT NULL,
    looserID INTEGER NOT NULL,
    isDraw BOOLEAN NOT NULL
);

drop table users;

INSERT INTO users(username, password, email) VALUES ('example','example123','email@example.com');


