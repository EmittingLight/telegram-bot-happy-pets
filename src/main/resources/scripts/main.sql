-- liquibase formatted sql

-- changeset sergei:1
CREATE TABLE cat
(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    catsOwnerName TEXT
);
CREATE TABLE dog
(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    dogsOwnerName TEXT
);
CREATE TABLE owner
(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    dogsOwnerName TEXT
);