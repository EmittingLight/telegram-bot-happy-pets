
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
CREATE TABLE catOwner
(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    dogsOwnerName TEXT
);
 CREATE TABLE dogOwner
 (
     id            SERIAL PRIMARY KEY,
     chatId        INT,
     dogsOwnerName TEXT
 );