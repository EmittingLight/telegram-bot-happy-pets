-- liquibase formatted sql

-- changeset sergei:1
CREATE TABLE userCat
(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    catsOwnerName TEXT
);
CREATE TABLE userDog
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