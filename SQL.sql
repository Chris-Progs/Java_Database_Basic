DROP DATABASE IF EXISTS DetailsDB;

CREATE DATABASE DetailsDB;

USE DetailsDB;

CREATE TABLE Details(DetailsID INTEGER NOT NULL AUTO_INCREMENT, ID VARCHAR(32), Name VARCHAR(32), PRIMARY KEY(DetailsID));

INSERT INTO Details(ID, Name) VALUES(1, 'Lilly'),(2, 'Holly'),(3, 'Candice'),(4, 'Hannah'),(5, 'Kelly'),(6, 'Mariah'),(7, 'Sofia'),(8, 'Carla'),(9, 'Justine'),(10, 'Liz');