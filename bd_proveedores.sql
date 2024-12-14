-- Active: 1734154124621@@localhost@3306@sise
CREATE DATABASE sise;
USE sise;

CREATE TABLE Proveedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(20) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL 
);

SELECT *FROM proveedor

 
 
