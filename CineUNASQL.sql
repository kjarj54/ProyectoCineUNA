/*
Created: 19/9/2022
Modified: 22/10/2022
Model: ProyectoCinesUna
Database: Oracle 18c
*/




-- Create tables section -------------------------------------------------

-- Table cineuna.PRO_CLIENTES

CREATE TABLE cineuna.PRO_CLIENTES(
  cli_id Number NOT NULL,
  cli_usuario Varchar2(20 ) NOT NULL,
  cli_clave Varchar2(20 ) NOT NULL,
  cli_nombre Varchar2(50 ) NOT NULL,
  cli_papellido Varchar2(30 ) NOT NULL,
  cli_correo Varchar2(50 ) NOT NULL,
  cli_idioma Varchar2(1 ) DEFAULT ON NULL 'E' NOT NULL,
  cli_estado Varchar2(1 ) DEFAULT 'I' NOT NULL,
  cli_admin Varchar2(1 ) DEFAULT 'N' NOT NULL,
  cli_sapellido Varchar2(10 ) DEFAULT ON NULL No Tiene,
  cli_version Number DEFAULT ON NULL 1 NOT NULL,
  CONSTRAINT PRO_CLIENTES_CK01 CHECK (cli_admin in ('S','N')),
  CONSTRAINT PRO_CLIENTES_CK02 CHECK (cli_estado in ('A', 'I'))
)
;

-- Create indexes for table cineuna.PRO_CLIENTES

CREATE UNIQUE INDEX PRO_CLIENTES_UNQ01 ON cineuna.PRO_CLIENTES (cli_usuario)
;

-- Add keys for table cineuna.PRO_CLIENTES

ALTER TABLE cineuna.PRO_CLIENTES ADD CONSTRAINT PRO_CLIENTES_PK PRIMARY KEY (cli_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_id IS 'Id de los usuarios'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_usuario IS 'usuario de las personas'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_clave IS 'Clave de los usuarios'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_nombre IS 'Nombre de los usuarios'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_papellido IS 'Primer apellido'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_correo IS 'Correo de los usuarios'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_idioma IS 'Idioma de los usuarios (E=Espanol; I=Ingles)'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_estado IS 'Estado del usuario(A:activo, I:inactivo)'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_admin IS 'Estado Administrador(S:si, N:no)'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_sapellido IS 'Segundo apellido'
;
COMMENT ON COLUMN cineuna.PRO_CLIENTES.cli_version IS 'Version del registro de usuarios'
;

-- Table cineuna.PRO_PELICULAS

CREATE TABLE cineuna.PRO_PELICULAS(
  pel_id Number NOT NULL,
  pel_nombre Varchar2(30 ) NOT NULL,
  pel_synopsis Varchar2(30 ),
  pel_link Varchar2(200 ) NOT NULL,
  pel_imagen Blob NOT NULL,
  pel_fechaestreno Date NOT NULL,
  pel_estado Varchar2(1 ) DEFAULT 'S' NOT NULL,
  pel_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT PRO_PELICULAS_CK01 CHECK (pel_estado in ('S','I','P'))
)
;

-- Add keys for table cineuna.PRO_PELICULAS

ALTER TABLE cineuna.PRO_PELICULAS ADD CONSTRAINT PRO_PELICULAS_PK PRIMARY KEY (pel_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_id IS 'Id de las peliculas'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_nombre IS 'Nombre de la pelicula'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_synopsis IS 'Reseña de la pelicula'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_link IS 'Link del trailer de la pelicula'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_imagen IS 'Imagen de la pelicula'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_fechaestreno IS 'Fecha de estreno de la pelicula'
;
COMMENT ON COLUMN cineuna.PRO_PELICULAS.pel_estado IS 'Estado de la pelicula(S:en sala, I:inactiva, P:proximamente)'
;

-- Table cineuna.PRO_SALAS

CREATE TABLE cineuna.PRO_SALAS(
  sal_id Number NOT NULL,
  sal_nombre Varchar2(30 ) NOT NULL,
  sal_estado Varchar2(1 ) DEFAULT 'I' NOT NULL,
  sal_imgfondo Blob NOT NULL,
  sal_version Number DEFAULT 1 NOT NULL,
  cli_id Number,
  CONSTRAINT PRO_SALAS_CK01 CHECK (pel_estado in ('A','I'))
)
;

-- Create indexes for table cineuna.PRO_SALAS

CREATE INDEX PRO_CLIENTESALAS_FK01 ON cineuna.PRO_SALAS (cli_id)
;

-- Add keys for table cineuna.PRO_SALAS

ALTER TABLE cineuna.PRO_SALAS ADD CONSTRAINT PRO_SALAS_PK PRIMARY KEY (sal_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_SALAS.sal_id IS 'Id de la sala'
;
COMMENT ON COLUMN cineuna.PRO_SALAS.sal_nombre IS 'Nombre o numero de la sala'
;
COMMENT ON COLUMN cineuna.PRO_SALAS.sal_estado IS 'Estado de la sala (Activa=A; Inactiva=I)'
;
COMMENT ON COLUMN cineuna.PRO_SALAS.sal_imgfondo IS 'Imagen del fondo de la sala'
;
COMMENT ON COLUMN cineuna.PRO_SALAS.sal_version IS 'Version del registro de las salas'
;

-- Table cineuna.PRO_ASIENTOS

CREATE TABLE cineuna.PRO_ASIENTOS(
  asi_id Number NOT NULL,
  asi_img Blob NOT NULL,
  asi_nombre Varchar2(4 ) NOT NULL,
  asi_cantidad Number NOT NULL,
  asi_version Number DEFAULT 1 NOT NULL,
  sal_id Number
)
;

-- Create indexes for table cineuna.PRO_ASIENTOS

CREATE INDEX PRO_SALASASIENTOS_FK01 ON cineuna.PRO_ASIENTOS (sal_id)
;

-- Add keys for table cineuna.PRO_ASIENTOS

ALTER TABLE cineuna.PRO_ASIENTOS ADD CONSTRAINT PRO_ASIENTOS_PK PRIMARY KEY (asi_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_ASIENTOS.asi_id IS 'identificador de los asientos por sala'
;
COMMENT ON COLUMN cineuna.PRO_ASIENTOS.asi_img IS 'Imagen del asiento'
;
COMMENT ON COLUMN cineuna.PRO_ASIENTOS.asi_nombre IS 'nombre asientos (e.g. A1, B5, C10)'
;
COMMENT ON COLUMN cineuna.PRO_ASIENTOS.asi_cantidad IS 'cantidad de asientos'
;
COMMENT ON COLUMN cineuna.PRO_ASIENTOS.asi_version IS 'Version del registro de asientos'
;

-- Table cineuna.PRO_RESERVACION

CREATE TABLE cineuna.PRO_RESERVACION(
  res_id Number NOT NULL,
  res_fecha Date NOT NULL,
  res_total Number NOT NULL,
  res_version Number DEFAULT 1 NOT NULL,
  cli_id Number
)
;

-- Create indexes for table cineuna.PRO_RESERVACION

CREATE INDEX PRO_CLIENTESRESERVACION_FK01 ON cineuna.PRO_RESERVACION (cli_id)
;

-- Add keys for table cineuna.PRO_RESERVACION

ALTER TABLE cineuna.PRO_RESERVACION ADD CONSTRAINT PRO_RESERVACION_PK PRIMARY KEY (res_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_RESERVACION.res_id IS 'identificador'
;
COMMENT ON COLUMN cineuna.PRO_RESERVACION.res_fecha IS 'fecha de la reservacion'
;
COMMENT ON COLUMN cineuna.PRO_RESERVACION.res_version IS 'Version del registro de reservacion'
;

-- Table cineuna.PRO_TANDAS

CREATE TABLE cineuna.PRO_TANDAS(
  tan_id Number NOT NULL,
  tan_nombre Varchar2(50 ) NOT NULL,
  tan_horainicio Timestamp(6) NOT NULL,
  tan_horafinal Timestamp(6) NOT NULL,
  tan_fecha Date NOT NULL,
  tan_precio Number NOT NULL,
  tan_version Number DEFAULT ON NULL 1 NOT NULL,
  pel_id Number,
  res_id Number
)
;

-- Create indexes for table cineuna.PRO_TANDAS

CREATE INDEX PRO_RESERVACIONTANDAS_FK01 ON cineuna.PRO_TANDAS (res_id)
;

CREATE INDEX PRO_PELICULASTANDAS_FK01 ON cineuna.PRO_TANDAS (pel_id)
;

-- Add keys for table cineuna.PRO_TANDAS

ALTER TABLE cineuna.PRO_TANDAS ADD CONSTRAINT PRO_TANDAS_PK PRIMARY KEY (tan_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_id IS 'Identificador de tabla Tandas'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_nombre IS 'Nombre de la tanda con sala y pelicula'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_horainicio IS 'Hora de inicio de la tanda'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_horafinal IS 'Hora fin de la tanda'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_fecha IS 'Fecha de la tanda'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_precio IS 'Precio de la tanda'
;
COMMENT ON COLUMN cineuna.PRO_TANDAS.tan_version IS 'Version de la tabla'
;

-- Table cineuna.PRO_COMIDAS

CREATE TABLE cineuna.PRO_COMIDAS(
  com_id Number NOT NULL,
  com_nombre Varchar2(30 ) NOT NULL,
  com_precio Varchar2(30 ) NOT NULL,
  com_descripcion Varchar2(30 ),
  com_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table cineuna.PRO_COMIDAS

ALTER TABLE cineuna.PRO_COMIDAS ADD CONSTRAINT PRO_COMIDAS_PK PRIMARY KEY (com_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN cineuna.PRO_COMIDAS.com_id IS 'Id de las comidas'
;
COMMENT ON COLUMN cineuna.PRO_COMIDAS.com_nombre IS 'Nombre de la comida o bebida'
;
COMMENT ON COLUMN cineuna.PRO_COMIDAS.com_precio IS 'Precio de la comida o bebida'
;
COMMENT ON COLUMN cineuna.PRO_COMIDAS.com_descripcion IS 'Descripcion de la comida o la bebida'
;
COMMENT ON COLUMN cineuna.PRO_COMIDAS.com_version IS 'Version del registro'
;

-- Table cineuna.PRO_FACTURAS

CREATE TABLE cineuna.PRO_FACTURAS(
  fac_id Number NOT NULL,
  fac_total Number NOT NULL,
  fac_version Number DEFAULT 1 NOT NULL,
  fac_fecha Date NOT NULL,
  cli_id Number
)
;

-- Create indexes for table cineuna.PRO_FACTURAS

CREATE INDEX PRO_CLIENTESFACTURAS ON cineuna.PRO_FACTURAS (cli_id)
;

-- Add keys for table cineuna.PRO_FACTURAS

ALTER TABLE cineuna.PRO_FACTURAS ADD CONSTRAINT PRO_FACTURAS_PK PRIMARY KEY (fac_id)
;

-- Trigger for sequence PRO_CLIENTES_SEQ01 for column cli_id in table cineuna.PRO_CLIENTES ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_CLIENTES_TGR01 BEFORE INSERT
ON cineuna.PRO_CLIENTES FOR EACH ROW
BEGIN
  if:new.cli_id is null or :new.cli_id <=0 then 
	:new.cli_id := PRO_CLIENTES_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_CLIENTES_TGR02 AFTER UPDATE OF cli_id
ON cineuna.PRO_CLIENTES FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cli_id in table cineuna.PRO_CLIENTES as it uses sequence.');
END;
/

-- Trigger for sequence PRO_PELICULAS_SEQ01 for column pel_id in table cineuna.PRO_PELICULAS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_PELICULAS_TGR01 BEFORE INSERT
ON cineuna.PRO_PELICULAS FOR EACH ROW
BEGIN
  if:new.pel_id is null or :new.pel_id <=0 then 
	:new.pel_id := PRO_PELICULAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_PELICULAS_TGR02 AFTER UPDATE OF pel_id
ON cineuna.PRO_PELICULAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pel_id in table cineuna.PRO_PELICULAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_SALAS_SEQ01 for column sal_id in table cineuna.PRO_SALAS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_SALAS_TGR01 BEFORE INSERT
ON cineuna.PRO_SALAS FOR EACH ROW
BEGIN
  if:new.sal_id is null or :new.sal_id <=0 then 
	:new.sal_id := PRO_SALAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_SALAS_TGR02 AFTER UPDATE OF sal_id
ON cineuna.PRO_SALAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column sal_id in table cineuna.PRO_SALAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_ASIENTOS_SEQ01 for column asi_id in table cineuna.PRO_ASIENTOS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_ASIENTOS_TGR01 BEFORE INSERT
ON cineuna.PRO_ASIENTOS FOR EACH ROW
BEGIN
  if:new.asi_id is null or :new.asi_id <=0 then 
	:new.asi_id := PRO_ASIENTOS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_ASIENTOS_TGR02 AFTER UPDATE OF asi_id
ON cineuna.PRO_ASIENTOS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column asi_id in table cineuna.PRO_ASIENTOS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_RESERVACION_SEQ01 for column res_id in table cineuna.PRO_RESERVACION ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_RESERVACION_TGR01 BEFORE INSERT
ON cineuna.PRO_RESERVACION FOR EACH ROW
BEGIN
  if:new.res_id is null or :new.res_id <=0 then 
	:new.res_id := PRO_RESERVACION_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_RESERVACION_TGR02 AFTER UPDATE OF res_id
ON cineuna.PRO_RESERVACION FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column res_id in table cineuna.PRO_RESERVACION as it uses sequence.');
END;
/

-- Trigger for sequence PRO_TANDAS_SEQ01 for column tan_id in table cineuna.PRO_TANDAS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_TANDAS_TGR01 BEFORE INSERT
ON cineuna.PRO_TANDAS FOR EACH ROW
BEGIN
  if:new.tan_id is null or :new.tan_id <=0 then 
	:new.tan_id := PRO_TANDAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_TANDAS_TGR02 AFTER UPDATE OF tan_id
ON cineuna.PRO_TANDAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column tan_id in table cineuna.PRO_TANDAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_COMIDAS_SEQ01 for column com_id in table cineuna.PRO_COMIDAS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_COMIDAS_TGR01 BEFORE INSERT
ON cineuna.PRO_COMIDAS FOR EACH ROW
BEGIN
  :if:new.com_id is null or :new.com_id <=0 then 
	:new.com_id := PRO_COMIDAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_COMIDAS_TGR02 AFTER UPDATE OF com_id
ON cineuna.PRO_COMIDAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column com_id in table cineuna.PRO_COMIDAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_FACTURAS_SEQ01 for column fac_id in table cineuna.PRO_FACTURAS ---------
CREATE OR REPLACE TRIGGER cineuna.PRO_FACTURAS_TGR01 BEFORE INSERT
ON cineuna.PRO_FACTURAS FOR EACH ROW
BEGIN
  :if:new.fac_id is null or :new.fac_id <= 0 then 
  :new.fac_id := PRO_FACTURAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER cineuna.PRO_FACTURAS_TGR02 AFTER UPDATE OF fac_id
ON cineuna.PRO_FACTURAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column fac_id in table cineuna.PRO_FACTURAS as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE PRO_SALASPELICULAS ADD CONSTRAINT PRO_SALASPELICULAS_FK01 FOREIGN KEY (pel_id) REFERENCES cineuna.PRO_PELICULAS (pel_id)
;


ALTER TABLE PRO_SALASPELICULAS ADD CONSTRAINT PRO_SALASPELICULAS_FK02 FOREIGN KEY (sal_id) REFERENCES cineuna.PRO_SALAS (sal_id)
;


ALTER TABLE cineuna.PRO_TANDAS ADD CONSTRAINT PRO_PELICULASTANDAS_FK01 FOREIGN KEY (pel_id) REFERENCES cineuna.PRO_PELICULAS (pel_id)
;


ALTER TABLE cineuna.PRO_ASIENTOS ADD CONSTRAINT PRO_SALASASIENTOS_FK01 FOREIGN KEY (sal_id) REFERENCES cineuna.PRO_SALAS (sal_id)
;


ALTER TABLE PRO_TANDASASIENTOS ADD CONSTRAINT PRO_ASIENTOSTANDAS_FK01 FOREIGN KEY (asi_id) REFERENCES cineuna.PRO_ASIENTOS (asi_id)
;


ALTER TABLE PRO_TANDASASIENTOS ADD CONSTRAINT PRO_ASIENTOSTANDAS_FK02 FOREIGN KEY (tan_id) REFERENCES cineuna.PRO_TANDAS (tan_id)
;


ALTER TABLE PRO_FATURASCOMIDAS ADD CONSTRAINT PRO_FATURASCOMIDAS_FK01 FOREIGN KEY (fac_id) REFERENCES cineuna.PRO_FACTURAS (fac_id)
;


ALTER TABLE PRO_FATURASCOMIDAS ADD CONSTRAINT PRO_FATURASCOMIDAS_FK02 FOREIGN KEY (com_id) REFERENCES cineuna.PRO_COMIDAS (com_id)
;


ALTER TABLE cineuna.PRO_FACTURAS ADD CONSTRAINT PRO_CLIENTESFACTURAS_FK01 FOREIGN KEY (cli_id) REFERENCES cineuna.PRO_CLIENTES (cli_id)
;


ALTER TABLE cineuna.PRO_TANDAS ADD CONSTRAINT PRO_RESERVACIONTANDAS_FK01 FOREIGN KEY (res_id) REFERENCES cineuna.PRO_RESERVACION (res_id)
;


ALTER TABLE cineuna.PRO_RESERVACION ADD CONSTRAINT PRO_CLIENTESRESERVACION_FK01 FOREIGN KEY (cli_id) REFERENCES cineuna.PRO_CLIENTES (cli_id)
;


ALTER TABLE cineuna.PRO_SALAS ADD CONSTRAINT PRO_CLIENTESALAS_FK01 FOREIGN KEY (cli_id) REFERENCES cineuna.PRO_CLIENTES (cli_id)
;




-- Grant permissions section -------------------------------------------------




