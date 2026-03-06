
/*creacion de tablas*/
CREATE TABLE topico(
    id_topico INT NOT NULL AUTO_INCREMENT UNIQUE,
    titulo_topico varchar(200) NOT NULL,
    mensaje_topico varchar(300) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado_topico TINYINT NOT NULL DEFAULT 1,

    fk_idusuario INT NOT NULL,
    fk_idcurso INT NOT NULL
);

CREATE TABLE respuesta(
    id_respuesta INT NOT NULL AUTO_INCREMENT UNIQUE,
    mensaje_respuesta VARCHAR(300) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solucion VARCHAR(300) NOT NULL DEFAULT 'sin resolver',

    fk_idautor INT NOT NULL,
    fk_idtopico INT NOT NULL
);

CREATE TABLE curso(
    id_curso INT NOT NULL AUTO_INCREMENT UNIQUE,
    nombre_curso VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE usuario(
    id_usuario INT NOT NULL AUTO_INCREMENT UNIQUE,
    nombre_usuario VARCHAR(100) NOT NULL,
    email_usuario VARCHAR(250) NOT NULL UNIQUE,
    contrasenia VARCHAR(250) NOT NULL
);

CREATE TABLE perfil(
    id_perfil INT NOT NULL AUTO_INCREMENT UNIQUE,
    nombre_perfil VARCHAR(100) NOT NULL,

    fk_idusuario INT NOT NULL
);

/*agregar llaves primarias*/

ALTER TABLE topico
ADD CONSTRAINT pk_topico
PRIMARY KEY(id_topico);

ALTER TABLE respuesta
ADD CONSTRAINT pk_respuesta
PRIMARY KEY(id_respuesta);

ALTER TABLE curso
ADD CONSTRAINT pk_curso
PRIMARY KEY(id_curso);

ALTER TABLE usuario
ADD CONSTRAINT pk_usuario
PRIMARY KEY(id_usuario);

ALTER TABLE perfil
ADD CONSTRAINT pf_perfil
PRIMARY KEY(id_perfil);


/*agregar llaves foraneas*/

ALTER TABLE topico
ADD CONSTRAINT fk_usuario
FOREIGN KEY(fk_idusuario)
REFERENCES usuario(id_usuario);

ALTER TABLE topico
ADD CONSTRAINT fk_curso
FOREIGN KEY(fk_idcurso)
REFERENCES curso(id_curso);

ALTER TABLE respuesta
ADD CONSTRAINT fk_autor
FOREIGN KEY(fk_idautor)
REFERENCES usuario(id_usuario);

ALTER TABLE respuesta
ADD CONSTRAINT fk_topico
FOREIGN KEY(fk_idtopico)
REFERENCES topico(id_topico);

ALTER TABLE perfil
ADD CONSTRAINT fk_usuario_perfil
FOREIGN KEY(fk_idusuario)
REFERENCES usuario(id_usuario);



