CREATE TABLE alumnos (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre CHAR(50) NOT NULL,
fecha_nacimiento DATE NOT NULL
);

CREATE TABLE carreras (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre CHAR(50) NOT NULL,
descripcion CHAR(100)
);

ALTER TABLE alumnos
ADD COLUMN carrera_id INT, 
ADD FOREIGN KEY(carrera_id) REFERENCES carreras(id)
;