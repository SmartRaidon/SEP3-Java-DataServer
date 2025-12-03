CREATE SCHEMA IF NOT EXISTS sep3;
SET SCHEMA 'sep3';

DROP SCHEMA sep3 CASCADE;

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY ,
    username VARCHAR (30),
    password VARCHAR (50),
    email TEXT,
    score DOUBLE PRECISION
    );

CREATE TABLE game_results (
    id SERIAL PRIMARY KEY,
    game_id INTEGER NOT NULL UNIQUE,
    winner_id INTEGER NOT NULL,
    looser_id INTEGER NOT NULL,
    is_draw BOOLEAN NOT NULL
);

drop table game_results;

INSERT INTO users(username, password, email) VALUES ('example','example123','email@example.com');


