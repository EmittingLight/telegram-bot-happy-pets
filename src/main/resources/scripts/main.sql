
 -- liquibase formatted sql

-- changeset sergei:1
CREATE TABLE cats_owner(
                           id SERIAL PRIMARY KEY ,
                           chatId INT,
                           catsOwnerName TEXT
);
CREATE TABLE dogs_owner(
                           id SERIAL PRIMARY KEY ,
                           chatId INT,
                           dogsOwnerName TEXT
)