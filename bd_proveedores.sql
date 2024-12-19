-- Active: 1734154124621@@localhost@3306@sise
CREATE DATABASE sise;
USE sise;

CREATE TABLE Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(255) NOT NULL,
    numero_celular VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

SELECT * FROM Empleado;

DROP TABLE Empleado



