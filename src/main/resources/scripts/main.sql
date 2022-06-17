

 -- liquibase formatted sql

-- changeset sergei:1

CREATE TABLE userCat

(
    id            SERIAL PRIMARY KEY,
    user_cat_id     INT,
    catsOwnerName TEXT,
    pet TEXT,
    date timestamp
);

CREATE TABLE userDog

(
    id            SERIAL PRIMARY KEY,
    chatId        INT,
    dogsOwnerName TEXT,
    pet TEXT,
    date timestamp
);

CREATE TABLE picture

(
    id            SERIAL PRIMARY KEY,
    chatId    INT,
    filePath       text,
    fileSize       INT,
    mediaType      text
);
