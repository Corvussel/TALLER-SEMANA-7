CREATE DATABASE sise;
USE sise;

CREATE TABLE Restaurante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    fechaReserva DATE NOT NULL,
    nroPersonas INT NOT NULL,
    sede INT NOT NULL
);

