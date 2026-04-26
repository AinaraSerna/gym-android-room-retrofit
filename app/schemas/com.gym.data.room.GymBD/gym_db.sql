PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE `sesiones` (`id` INTEGER PRIMARY KEY AUTO_INCREMENT, `nombre` TEXT NOT NULL, `descripcion` TEXT NOT NULL);
CREATE TABLE `ejercicios` (`id` INTEGER PRIMARY KEY AUTO_INCREMENT, `orden` INTEGER NOT NULL)
CREATE TABLE `historial` (`id` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, `cod_sesion` INTEGER, `fecha` INTEGER NOT NULL, FOREIGN KEY(`cod_sesion`) REFERENCES `sesiones`(`id`) ON UPDATE CASCADE ON DELETE SET NULL );
CREATE TABLE `registros` (`id` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, `cod_historial` INTEGER NOT NULL, `cod_ejercicio` INTEGER, `nombre_ejercicio` TEXT NOT NULL, `serie` INTEGER NOT NULL, `peso` REAL NOT NULL, `repeticiones` TEXT NOT NULL, FOREIGN KEY(`cod_historial`) REFERENCES `historial`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`cod_ejercicio`) REFERENCES `ejercicios`(`id`) ON UPDATE CASCADE ON DELETE SET NULL);
INSERT INTO sqlite_sequence VALUES('sesiones',0);
INSERT INTO sqlite_sequence VALUES('ejercicios',0);
INSERT INTO sqlite_sequence VALUES('historial',0);
INSERT INTO sqlite_sequence VALUES('registros',0);
CREATE UNIQUE INDEX `index_sesiones_nombre` ON `sesiones` (`nombre`);
CREATE INDEX `index_ejercicios_cod_sesion` ON `ejercicios` (`cod_sesion`);
CREATE INDEX `index_registros_cod_historial` ON `registros` (`cod_historial`);
CREATE INDEX `index_registros_cod_ejercicio` ON `registros` (`cod_ejercicio`);
COMMIT;