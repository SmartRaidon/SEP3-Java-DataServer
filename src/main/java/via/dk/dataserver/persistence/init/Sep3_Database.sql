Create SCHEMA IF NOT EXISTS sep3;
set SCHEMA 'sep3';
drop schema sep3 cascade;
Create table if not exists "user"(
    id serial PRIMARY KEY ,
    username varchar (30),
    password varchar (30),
    email text
    );
drop table "user";

INSERT INTO "user"(username, password, email) VALUES ('example','example123','email@example.com');


