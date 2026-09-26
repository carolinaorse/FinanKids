-- ============================================================
-- FinanKids - Trabajo Practico 2 - Seminario de Practica
-- Base de datos MySQL
-- Archivo: tp2_finankids.sql
-- Los datos incluidos son ficticios y se utilizan solo para pruebas.
-- ============================================================

CREATE DATABASE IF NOT EXISTS finankids_tp2
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE finankids_tp2;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS notificacion;
DROP TABLE IF EXISTS aporte_meta;
DROP TABLE IF EXISTS meta_ahorro;
DROP TABLE IF EXISTS tarea;
DROP TABLE IF EXISTS solicitud_dinero;
DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS usuario_grupo;
DROP TABLE IF EXISTS grupo_familiar;
DROP TABLE IF EXISTS usuario;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. USUARIO
CREATE TABLE usuario (
    id_usuario INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    estado ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 2. GRUPO_FAMILIAR
CREATE TABLE grupo_familiar (
    id_grupo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 3. USUARIO_GRUPO
CREATE TABLE usuario_grupo (
    id_usuario_grupo INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNSIGNED NOT NULL,
    id_grupo INT UNSIGNED NOT NULL,
    rol ENUM('TUTOR','MENOR') NOT NULL,
    fecha_vinculacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
    CONSTRAINT uq_usuario_grupo UNIQUE (id_usuario, id_grupo),
    CONSTRAINT fk_ug_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_ug_grupo
        FOREIGN KEY (id_grupo) REFERENCES grupo_familiar(id_grupo)
) ENGINE=InnoDB;

-- 4. CUENTA
CREATE TABLE cuenta (
    id_cuenta INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_usuario_grupo INT UNSIGNED NOT NULL UNIQUE,
    saldo DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('ACTIVA','INACTIVA') NOT NULL DEFAULT 'ACTIVA',
    CONSTRAINT chk_cuenta_saldo CHECK (saldo >= 0),
    CONSTRAINT fk_cuenta_usuario_grupo
        FOREIGN KEY (id_usuario_grupo)
        REFERENCES usuario_grupo(id_usuario_grupo)
) ENGINE=InnoDB;

-- 5. MOVIMIENTO
CREATE TABLE movimiento (
    id_movimiento INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT UNSIGNED NOT NULL,
    tipo ENUM('ASIGNACION','GASTO','RECOMPENSA','APORTE_META') NOT NULL,
    importe DECIMAL(12,2) NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(200),
    saldo_resultante DECIMAL(12,2) NOT NULL,
    CONSTRAINT chk_movimiento_importe CHECK (importe > 0),
    CONSTRAINT chk_movimiento_saldo CHECK (saldo_resultante >= 0),
    CONSTRAINT fk_movimiento_cuenta
        FOREIGN KEY (id_cuenta) REFERENCES cuenta(id_cuenta),
    INDEX idx_movimiento_cuenta_fecha (id_cuenta, fecha_hora)
) ENGINE=InnoDB;

-- 6. SOLICITUD_DINERO
CREATE TABLE solicitud_dinero (
    id_solicitud INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT UNSIGNED NOT NULL,
    importe DECIMAL(12,2) NOT NULL,
    motivo VARCHAR(200),
    fecha_solicitud DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE','APROBADA','RECHAZADA') NOT NULL DEFAULT 'PENDIENTE',
    fecha_resolucion DATETIME NULL,
    id_tutor_resuelve INT UNSIGNED NULL,
    CONSTRAINT chk_solicitud_importe CHECK (importe > 0),
    CONSTRAINT fk_solicitud_cuenta
        FOREIGN KEY (id_cuenta) REFERENCES cuenta(id_cuenta),
    CONSTRAINT fk_solicitud_tutor
        FOREIGN KEY (id_tutor_resuelve)
        REFERENCES usuario_grupo(id_usuario_grupo),
    INDEX idx_solicitud_estado (estado)
) ENGINE=InnoDB;

-- 7. TAREA
CREATE TABLE tarea (
    id_tarea INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_grupo INT UNSIGNED NOT NULL,
    id_menor INT UNSIGNED NOT NULL,
    id_tutor INT UNSIGNED NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    importe_recompensa DECIMAL(12,2) NULL,
    estado ENUM('PENDIENTE','INFORMADA','VALIDADA','RECHAZADA') NOT NULL DEFAULT 'PENDIENTE',
    fecha_asignacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_informada DATETIME NULL,
    fecha_validacion DATETIME NULL,
    CONSTRAINT chk_tarea_recompensa
        CHECK (importe_recompensa IS NULL OR importe_recompensa >= 0),
    CONSTRAINT fk_tarea_grupo
        FOREIGN KEY (id_grupo) REFERENCES grupo_familiar(id_grupo),
    CONSTRAINT fk_tarea_menor
        FOREIGN KEY (id_menor) REFERENCES usuario_grupo(id_usuario_grupo),
    CONSTRAINT fk_tarea_tutor
        FOREIGN KEY (id_tutor) REFERENCES usuario_grupo(id_usuario_grupo),
    INDEX idx_tarea_menor_estado (id_menor, estado)
) ENGINE=InnoDB;

-- 8. META_AHORRO
CREATE TABLE meta_ahorro (
    id_meta INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT UNSIGNED NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    importe_objetivo DECIMAL(12,2) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_objetivo DATE NULL,
    estado ENUM('ACTIVA','ALCANZADA','CANCELADA') NOT NULL DEFAULT 'ACTIVA',
    CONSTRAINT chk_meta_importe CHECK (importe_objetivo > 0),
    CONSTRAINT fk_meta_cuenta
        FOREIGN KEY (id_cuenta) REFERENCES cuenta(id_cuenta),
    INDEX idx_meta_cuenta_estado (id_cuenta, estado)
) ENGINE=InnoDB;

-- 9. APORTE_META
CREATE TABLE aporte_meta (
    id_aporte INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_meta INT UNSIGNED NOT NULL,
    importe DECIMAL(12,2) NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_aporte_importe CHECK (importe > 0),
    CONSTRAINT fk_aporte_meta
        FOREIGN KEY (id_meta) REFERENCES meta_ahorro(id_meta),
    INDEX idx_aporte_meta_fecha (id_meta, fecha_hora)
) ENGINE=InnoDB;

-- 10. NOTIFICACION
CREATE TABLE notificacion (
    id_notificacion INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNSIGNED NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    mensaje VARCHAR(200) NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    leida BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_notificacion_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    INDEX idx_notificacion_usuario_leida (id_usuario, leida)
) ENGINE=InnoDB;

-- ============================================================
-- DATOS FICTICIOS DE PRUEBA
-- ============================================================

INSERT INTO usuario
(nombre, apellido, email, password_hash, fecha_nacimiento)
VALUES
('Maria', 'Ejemplo', 'maria.tutora@finankids.test',
 '$2y$10$hash_ficticio_maria', '1985-05-12'),
('Juan', 'Ejemplo', 'juan.menor@finankids.test',
 '$2y$10$hash_ficticio_juan', '2013-08-20');

INSERT INTO grupo_familiar (nombre)
VALUES ('Familia de prueba');

INSERT INTO usuario_grupo (id_usuario, id_grupo, rol)
VALUES
(1, 1, 'TUTOR'),
(2, 1, 'MENOR');

INSERT INTO cuenta (id_usuario_grupo, saldo)
VALUES (2, 2500.00);

-- Trazabilidad: se conserva saldo actual en CUENTA y saldo historico
-- resultante en cada MOVIMIENTO.
INSERT INTO movimiento
(id_cuenta, tipo, importe, descripcion, saldo_resultante)
VALUES
(1, 'ASIGNACION', 2000.00, 'Asignacion semanal', 2000.00),
(1, 'RECOMPENSA', 500.00, 'Recompensa por tarea completada', 2500.00);

INSERT INTO solicitud_dinero
(id_cuenta, importe, motivo)
VALUES
(1, 1000.00, 'Salida con amigos');

INSERT INTO tarea
(id_grupo, id_menor, id_tutor, titulo, descripcion, importe_recompensa)
VALUES
(1, 2, 1, 'Ordenar mi habitacion',
 'Mantener la habitacion ordenada.', 500.00),
(1, 2, 1, 'Ayudar con la mesa',
 'Colaborar en poner y levantar la mesa.', 200.00);

INSERT INTO meta_ahorro
(id_cuenta, nombre, descripcion, importe_objetivo, fecha_objetivo)
VALUES
(1, 'Bicicleta',
 'Meta educativa de ahorro para una bicicleta.',
 15000.00, '2027-03-31');

INSERT INTO aporte_meta (id_meta, importe)
VALUES
(1, 3000.00),
(1, 3000.00);

INSERT INTO notificacion
(id_usuario, tipo, mensaje, leida)
VALUES
(1, 'SOLICITUD', 'Juan realizo una solicitud de dinero.', FALSE),
(2, 'META', 'Tu meta Bicicleta alcanzo un nuevo avance.', FALSE),
(2, 'TAREA', 'Tenes una nueva tarea asignada.', FALSE);

-- ============================================================
-- CONSULTAS DE DEMOSTRACION
-- ============================================================

-- A. Consultar saldo actual del menor.
SELECT
    u.nombre,
    u.apellido,
    c.saldo
FROM cuenta c
JOIN usuario_grupo ug ON ug.id_usuario_grupo = c.id_usuario_grupo
JOIN usuario u ON u.id_usuario = ug.id_usuario
WHERE c.id_cuenta = 1;

-- B. Consultar historial y trazabilidad de movimientos.
SELECT
    tipo,
    importe,
    fecha_hora,
    descripcion,
    saldo_resultante
FROM movimiento
WHERE id_cuenta = 1
ORDER BY fecha_hora DESC, id_movimiento DESC;

-- C. Consultar solicitudes pendientes.
SELECT
    id_solicitud,
    importe,
    motivo,
    fecha_solicitud,
    estado
FROM solicitud_dinero
WHERE id_cuenta = 1
  AND estado = 'PENDIENTE';

-- D. Consultar tareas pendientes del menor.
SELECT
    t.titulo,
    t.descripcion,
    t.importe_recompensa,
    t.estado
FROM tarea t
WHERE t.id_menor = 2
  AND t.estado = 'PENDIENTE';

-- E. Calcular progreso de una meta sin almacenar un porcentaje redundante.
SELECT
    m.nombre,
    m.importe_objetivo,
    COALESCE(SUM(a.importe), 0) AS importe_ahorrado,
    ROUND(
        COALESCE(SUM(a.importe), 0) / m.importe_objetivo * 100,
        2
    ) AS porcentaje_progreso
FROM meta_ahorro m
LEFT JOIN aporte_meta a ON a.id_meta = m.id_meta
WHERE m.id_meta = 1
GROUP BY m.id_meta, m.nombre, m.importe_objetivo;

-- F. INSERT adicional para demostrar insercion.
-- Se utiliza un mensaje de prueba identificable para que la demostracion
-- sea reproducible sin depender de un valor AUTO_INCREMENT concreto.
INSERT INTO notificacion
(id_usuario, tipo, mensaje, leida)
VALUES
(2, 'PRUEBA', 'Notificacion temporal para verificar INSERT y DELETE.', FALSE);

-- G. Verificar la insercion: debe devolver 1 registro.
SELECT
    id_notificacion,
    id_usuario,
    tipo,
    mensaje,
    fecha_hora,
    leida
FROM notificacion
WHERE tipo = 'PRUEBA'
  AND mensaje = 'Notificacion temporal para verificar INSERT y DELETE.';

-- H. DELETE controlado del registro temporal.
DELETE FROM notificacion
WHERE tipo = 'PRUEBA'
  AND mensaje = 'Notificacion temporal para verificar INSERT y DELETE.';

-- I. Verificar el borrado: debe devolver 0 registros.
SELECT
    id_notificacion,
    id_usuario,
    tipo,
    mensaje,
    fecha_hora,
    leida
FROM notificacion
WHERE tipo = 'PRUEBA'
  AND mensaje = 'Notificacion temporal para verificar INSERT y DELETE.';

-- J. Resumen de tablas creadas.
SHOW TABLES;
