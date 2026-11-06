-- Laboratorio 09 de Gestión de mantenimiento de equipos.
-- Importar desde la página inicial de phpMyAdmin.
-- Se puede volver a ejecutar y conserva las tablas y los datos existentes.

CREATE DATABASE IF NOT EXISTS bd_cliente
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bd_cliente;

CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(80) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    telefono VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE DATABASE IF NOT EXISTS bd_equipo
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bd_equipo;

CREATE TABLE IF NOT EXISTS equipos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(30) NOT NULL,
    tipo VARCHAR(60) NOT NULL,
    marca VARCHAR(60) NOT NULL,
    modelo VARCHAR(80) NOT NULL,
    id_cliente BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE DATABASE IF NOT EXISTS bd_orden_servicio
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bd_orden_servicio;

CREATE TABLE IF NOT EXISTS ordenes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_equipo BIGINT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    costo_estimado DECIMAL(12, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;
