/*
Created: 19/9/2022
Modified: 9/11/2022
Model: ProyectoCinesUna
Database: Oracle 18c
*/




-- Create users section -------------------------------------------------

-- User cineuna has empty SQL definition.

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE PRO_CLIENTES_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_PELICULAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_SALAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_REPORTES_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_RESERVACION_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_TANDAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_ASIENTOS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_COMIDAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE PRO_FACTURAS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table PRO_CLIENTES

CREATE TABLE PRO_CLIENTES(
  cli_id Number NOT NULL,
  cli_usuario Varchar2(20 ) NOT NULL,
  cli_clave Varchar2(20 ) NOT NULL,
  cli_claverestaurada Varchar2(20 ),
  cli_nombre Varchar2(50 ) NOT NULL,
  cli_papellido Varchar2(30 ) NOT NULL,
  cli_correo Varchar2(50 ) NOT NULL,
  cli_idioma Varchar2(1 ) DEFAULT ON NULL 'E' NOT NULL,
  cli_estado Varchar2(1 ) DEFAULT 'I' NOT NULL,
  cli_admin Varchar2(1 ) DEFAULT 'N' NOT NULL,
  cli_sapellido Varchar2(10 ),
  cli_version Number DEFAULT ON NULL 1 NOT NULL,
  CONSTRAINT PRO_CLIENTES_CK01 CHECK (cli_admin in ('S','N')),
  CONSTRAINT PRO_CLIENTES_CK02 CHECK (cli_estado in ('A', 'I')),
  CONSTRAINT PRO_CLIENTES_CK03 CHECK (cli_idioma in ('E','I'))
)
;

-- Create indexes for table PRO_CLIENTES

CREATE UNIQUE INDEX PRO_CLIENTES_UNQ01 ON PRO_CLIENTES (cli_usuario)
;

CREATE UNIQUE INDEX PRO_CLIENTES_UNQ02 ON PRO_CLIENTES (cli_correo)
;

-- Add keys for table PRO_CLIENTES

ALTER TABLE PRO_CLIENTES ADD CONSTRAINT PRO_CLIENTES_PK PRIMARY KEY (cli_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_CLIENTES.cli_id IS 'Id de los usuarios'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_usuario IS 'usuario de las personas'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_clave IS 'Clave de los usuarios'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_claverestaurada IS 'Clave que se otorga caundo el usuario olvido la clave'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_nombre IS 'Nombre de los usuarios'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_papellido IS 'Primer apellido'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_correo IS 'Correo de los usuarios'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_idioma IS 'Idioma de los usuarios (E=Espanol; I=Ingles)'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_estado IS 'Estado del usuario(A:activo, I:inactivo)'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_admin IS 'Estado Administrador(S:si, N:no)'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_sapellido IS 'Segundo apellido'
;
COMMENT ON COLUMN PRO_CLIENTES.cli_version IS 'Version del registro de usuarios'
;

-- Table PRO_PELICULAS

CREATE TABLE PRO_PELICULAS(
  pel_id Number NOT NULL,
  pel_idioma Varchar2(1 ) DEFAULT 'E',
  pel_nombre Varchar2(30 ) NOT NULL,
  pel_synopsis Varchar2(500 ),
  pel_link Varchar2(200 ) NOT NULL,
  pel_imagen Blob NOT NULL,
  pel_fechaestreno Date NOT NULL,
  pel_estado Varchar2(1 ) DEFAULT 'S' NOT NULL,
  pel_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT PRO_PELICULAS_CK01 CHECK (pel_estado in ('S','I','P')),
  CONSTRAINT PRO_PELICULAS_CK02 CHECK (pel_idioma in ('E','I'))
)
;

-- Add keys for table PRO_PELICULAS

ALTER TABLE PRO_PELICULAS ADD CONSTRAINT PRO_PELICULAS_PK PRIMARY KEY (pel_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_PELICULAS.pel_id IS 'Id de las peliculas'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_idioma IS 'Idioma de la pelicula(E: espanol, I:ingles)'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_nombre IS 'Nombre de la pelicula'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_synopsis IS 'Reseña de la pelicula'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_link IS 'Link del trailer de la pelicula'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_imagen IS 'Imagen de la pelicula'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_fechaestreno IS 'Fecha de estreno de la pelicula'
;
COMMENT ON COLUMN PRO_PELICULAS.pel_estado IS 'Estado de la pelicula(S:en sala, I:inactiva, P:proximamente)'
;

-- Table PRO_SALAS

CREATE TABLE PRO_SALAS(
  sal_id Number NOT NULL,
  sal_nombre Varchar2(30 ) NOT NULL,
  sal_estado Varchar2(1 ) DEFAULT 'I' NOT NULL,
  sal_imgfondo Blob NOT NULL,
  sal_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT PRO_SALAS_CK01 CHECK (sal_estado in ('A','I'))
)
;

-- Add keys for table PRO_SALAS

ALTER TABLE PRO_SALAS ADD CONSTRAINT PRO_SALAS_PK PRIMARY KEY (sal_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_SALAS.sal_id IS 'Id de la sala'
;
COMMENT ON COLUMN PRO_SALAS.sal_nombre IS 'Nombre o numero de la sala'
;
COMMENT ON COLUMN PRO_SALAS.sal_estado IS 'Estado de la sala (Activa=A; Inactiva=I)'
;
COMMENT ON COLUMN PRO_SALAS.sal_imgfondo IS 'Imagen del fondo de la sala'
;
COMMENT ON COLUMN PRO_SALAS.sal_version IS 'Version del registro de las salas'
;

-- Table PRO_ASIENTOS

CREATE TABLE PRO_ASIENTOS(
  asi_id Number NOT NULL,
  asi_img Blob NOT NULL,
  asi_nombre Varchar2(80) NOT NULL,
  asi_cantidad Number NOT NULL,
  asi_version Number DEFAULT 1 NOT NULL,
  sal_id Number
)
;

-- Create indexes for table PRO_ASIENTOS

CREATE INDEX PRO_SALASASIENTOS_FK01 ON PRO_ASIENTOS (sal_id)
;

-- Add keys for table PRO_ASIENTOS

ALTER TABLE PRO_ASIENTOS ADD CONSTRAINT PRO_ASIENTOS_PK PRIMARY KEY (asi_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_ASIENTOS.asi_id IS 'identificador de los asientos por sala'
;
COMMENT ON COLUMN PRO_ASIENTOS.asi_img IS 'Imagen del asiento'
;
COMMENT ON COLUMN PRO_ASIENTOS.asi_nombre IS 'nombre asientos (e.g. A1, B5, C10)'
;
COMMENT ON COLUMN PRO_ASIENTOS.asi_cantidad IS 'cantidad de asientos'
;
COMMENT ON COLUMN PRO_ASIENTOS.asi_version IS 'Version del registro de asientos'
;

-- Table PRO_RESERVACION

CREATE TABLE PRO_RESERVACION(
  res_id Number NOT NULL,
  res_fecha Date NOT NULL,
  res_total Number NOT NULL,
  res_version Number DEFAULT 1 NOT NULL,
  cli_id Number
)
;

-- Create indexes for table PRO_RESERVACION

CREATE INDEX PRO_CLIENTESRESERVACION_FK01 ON PRO_RESERVACION (cli_id)
;

-- Add keys for table PRO_RESERVACION

ALTER TABLE PRO_RESERVACION ADD CONSTRAINT PRO_RESERVACION_PK PRIMARY KEY (res_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_RESERVACION.res_id IS 'identificador'
;
COMMENT ON COLUMN PRO_RESERVACION.res_fecha IS 'fecha de la reservacion'
;
COMMENT ON COLUMN PRO_RESERVACION.res_version IS 'Version del registro de reservacion'
;

-- Table PRO_TANDAS

CREATE TABLE PRO_TANDAS(
  tan_id Number NOT NULL,
  tan_nombre Varchar2(50 ) NOT NULL,
  tan_horainicio Varchar2(6 ) NOT NULL,
  tan_horafinal Varchar2(6 ) NOT NULL,
  tan_fecha Date NOT NULL,
  tan_precio Number NOT NULL,
  tan_version Number DEFAULT ON NULL 1 NOT NULL,
  pel_id Number,
  res_id Number
)
;

-- Create indexes for table PRO_TANDAS

CREATE INDEX PRO_RESERVACIONTANDAS_FK01 ON PRO_TANDAS (res_id)
;

CREATE INDEX PRO_PELICULASTANDAS_FK01 ON PRO_TANDAS (pel_id)
;

-- Add keys for table PRO_TANDAS

ALTER TABLE PRO_TANDAS ADD CONSTRAINT PRO_TANDAS_PK PRIMARY KEY (tan_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_TANDAS.tan_id IS 'Identificador de tabla Tandas'
;
COMMENT ON COLUMN PRO_TANDAS.tan_nombre IS 'Nombre de la tanda con sala y pelicula'
;
COMMENT ON COLUMN PRO_TANDAS.tan_horainicio IS 'Hora de inicio de la tanda'
;
COMMENT ON COLUMN PRO_TANDAS.tan_horafinal IS 'Hora fin de la tanda'
;
COMMENT ON COLUMN PRO_TANDAS.tan_fecha IS 'Fecha de la tanda'
;
COMMENT ON COLUMN PRO_TANDAS.tan_precio IS 'Precio de la tanda'
;
COMMENT ON COLUMN PRO_TANDAS.tan_version IS 'Version de la tabla'
;

-- Table PRO_SALASPELICULAS

CREATE TABLE PRO_SALASPELICULAS(
  pel_id Number NOT NULL,
  sal_id Number NOT NULL
)
;

-- Add keys for table PRO_SALASPELICULAS

ALTER TABLE PRO_SALASPELICULAS ADD CONSTRAINT PRO_SALASPELICULAS_PK PRIMARY KEY (pel_id,sal_id)
;

-- Table PRO_TANDASASIENTOS

CREATE TABLE PRO_TANDASASIENTOS(
  asi_id Number NOT NULL,
  tan_id Number NOT NULL
)
;

-- Add keys for table PRO_TANDASASIENTOS

ALTER TABLE PRO_TANDASASIENTOS ADD CONSTRAINT PRO_TANDASASIENTOS_PK PRIMARY KEY (asi_id,tan_id)
;

-- Table PRO_COMIDAS

CREATE TABLE PRO_COMIDAS(
  com_id Number NOT NULL,
  com_nombre Varchar2(30 ) NOT NULL,
  com_precio Number NOT NULL,
  com_descripcion Varchar2(30 ),
  com_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table PRO_COMIDAS

ALTER TABLE PRO_COMIDAS ADD CONSTRAINT PRO_COMIDAS_PK PRIMARY KEY (com_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN PRO_COMIDAS.com_id IS 'Id de las comidas'
;
COMMENT ON COLUMN PRO_COMIDAS.com_nombre IS 'Nombre de la comida o bebida'
;
COMMENT ON COLUMN PRO_COMIDAS.com_precio IS 'Precio de la comida o bebida'
;
COMMENT ON COLUMN PRO_COMIDAS.com_descripcion IS 'Descripcion de la comida o la bebida'
;
COMMENT ON COLUMN PRO_COMIDAS.com_version IS 'Version del registro'
;

-- Table PRO_FACTURAS

CREATE TABLE PRO_FACTURAS(
  fac_id Number NOT NULL,
  fac_total Number NOT NULL,
  fac_version Number DEFAULT 1 NOT NULL,
  fac_fecha Date NOT NULL,
  cli_id Number
)
;

-- Create indexes for table PRO_FACTURAS

CREATE INDEX PRO_CLIENTESFACTURAS ON PRO_FACTURAS (cli_id)
;

-- Add keys for table PRO_FACTURAS

ALTER TABLE PRO_FACTURAS ADD CONSTRAINT PRO_FACTURAS_PK PRIMARY KEY (fac_id)
;

-- Table PRO_FACTURASCOMIDAS

CREATE TABLE PRO_FACTURASCOMIDAS(
  fac_id Number NOT NULL,
  com_id Number NOT NULL
)
;

-- Add keys for table PRO_FACTURASCOMIDAS

ALTER TABLE PRO_FACTURASCOMIDAS ADD CONSTRAINT PRO_FACTURASCOMIDAS_PK PRIMARY KEY (fac_id,com_id)
;

-- Trigger for sequence PRO_CLIENTES_SEQ01 for column cli_id in table PRO_CLIENTES ---------
CREATE OR REPLACE TRIGGER PRO_CLIENTES_TGR01 BEFORE INSERT
ON PRO_CLIENTES FOR EACH ROW
BEGIN
  if:new.cli_id is null or :new.cli_id <=0 then 
	:new.cli_id := PRO_CLIENTES_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_CLIENTES_TGR02 AFTER UPDATE OF cli_id
ON PRO_CLIENTES FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cli_id in table PRO_CLIENTES as it uses sequence.');
END;
/

-- Trigger for sequence PRO_PELICULAS_SEQ01 for column pel_id in table PRO_PELICULAS ---------
CREATE OR REPLACE TRIGGER PRO_PELICULAS_TGR01 BEFORE INSERT
ON PRO_PELICULAS FOR EACH ROW
BEGIN
  if:new.pel_id is null or :new.pel_id <=0 then 
	:new.pel_id := PRO_PELICULAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_PELICULAS_TGR02 AFTER UPDATE OF pel_id
ON PRO_PELICULAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pel_id in table PRO_PELICULAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_SALAS_SEQ01 for column sal_id in table PRO_SALAS ---------
CREATE OR REPLACE TRIGGER PRO_SALAS_TGR01 BEFORE INSERT
ON PRO_SALAS FOR EACH ROW
BEGIN
  if:new.sal_id is null or :new.sal_id <=0 then 
	:new.sal_id := PRO_SALAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_SALAS_TGR02 AFTER UPDATE OF sal_id
ON PRO_SALAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column sal_id in table PRO_SALAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_ASIENTOS_SEQ01 for column asi_id in table PRO_ASIENTOS ---------
CREATE OR REPLACE TRIGGER PRO_ASIENTOS_TGR01 BEFORE INSERT
ON PRO_ASIENTOS FOR EACH ROW
BEGIN
  if:new.asi_id is null or :new.asi_id <=0 then 
	:new.asi_id := PRO_ASIENTOS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_ASIENTOS_TGR02 AFTER UPDATE OF asi_id
ON PRO_ASIENTOS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column asi_id in table PRO_ASIENTOS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_RESERVACION_SEQ01 for column res_id in table PRO_RESERVACION ---------
CREATE OR REPLACE TRIGGER PRO_RESERVACION_TGR01 BEFORE INSERT
ON PRO_RESERVACION FOR EACH ROW
BEGIN
  if:new.res_id is null or :new.res_id <=0 then 
	:new.res_id := PRO_RESERVACION_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_RESERVACION_TGR02 AFTER UPDATE OF res_id
ON PRO_RESERVACION FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column res_id in table PRO_RESERVACION as it uses sequence.');
END;
/

-- Trigger for sequence PRO_TANDAS_SEQ01 for column tan_id in table PRO_TANDAS ---------
CREATE OR REPLACE TRIGGER PRO_TANDAS_TGR01 BEFORE INSERT
ON PRO_TANDAS FOR EACH ROW
BEGIN
  if:new.tan_id is null or :new.tan_id <=0 then 
	:new.tan_id := PRO_TANDAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_TANDAS_TGR02 AFTER UPDATE OF tan_id
ON PRO_TANDAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column tan_id in table PRO_TANDAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_COMIDAS_SEQ01 for column com_id in table PRO_COMIDAS ---------
CREATE OR REPLACE TRIGGER PRO_COMIDAS_TGR01 BEFORE INSERT
ON PRO_COMIDAS FOR EACH ROW
BEGIN
  if:new.com_id is null or :new.com_id <=0 then 
	:new.com_id := PRO_COMIDAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_COMIDAS_TGR02 AFTER UPDATE OF com_id
ON PRO_COMIDAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column com_id in table PRO_COMIDAS as it uses sequence.');
END;
/

-- Trigger for sequence PRO_FACTURAS_SEQ01 for column fac_id in table PRO_FACTURAS ---------
CREATE OR REPLACE TRIGGER PRO_FACTURAS_TGR01 BEFORE INSERT
ON PRO_FACTURAS FOR EACH ROW
BEGIN
  if:new.fac_id is null or :new.fac_id <= 0 then 
  :new.fac_id := PRO_FACTURAS_SEQ01.nextval;
  end if;
END;
/
CREATE OR REPLACE TRIGGER PRO_FACTURAS_TGR02 AFTER UPDATE OF fac_id
ON PRO_FACTURAS FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column fac_id in table PRO_FACTURAS as it uses sequence.');
END;
/




-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE PRO_SALASPELICULAS ADD CONSTRAINT PRO_SALASPELICULAS_FK01 FOREIGN KEY (pel_id) REFERENCES PRO_PELICULAS (pel_id)
;


ALTER TABLE PRO_SALASPELICULAS ADD CONSTRAINT PRO_SALASPELICULAS_FK02 FOREIGN KEY (sal_id) REFERENCES PRO_SALAS (sal_id)
;


ALTER TABLE PRO_TANDAS ADD CONSTRAINT PRO_PELICULASTANDAS_FK01 FOREIGN KEY (pel_id) REFERENCES PRO_PELICULAS (pel_id)
;


ALTER TABLE PRO_ASIENTOS ADD CONSTRAINT PRO_SALASASIENTOS_FK01 FOREIGN KEY (sal_id) REFERENCES PRO_SALAS (sal_id)
;


ALTER TABLE PRO_TANDASASIENTOS ADD CONSTRAINT PRO_ASIENTOSTANDAS_FK01 FOREIGN KEY (asi_id) REFERENCES PRO_ASIENTOS (asi_id)
;


ALTER TABLE PRO_TANDASASIENTOS ADD CONSTRAINT PRO_ASIENTOSTANDAS_FK02 FOREIGN KEY (tan_id) REFERENCES PRO_TANDAS (tan_id)
;


ALTER TABLE PRO_FACTURASCOMIDAS ADD CONSTRAINT PRO_FACTURASCOMIDAS_FK01 FOREIGN KEY (fac_id) REFERENCES PRO_FACTURAS (fac_id)
;


ALTER TABLE PRO_FACTURASCOMIDAS ADD CONSTRAINT PRO_FACTURASCOMIDAS_FK02 FOREIGN KEY (com_id) REFERENCES PRO_COMIDAS (com_id)
;


ALTER TABLE PRO_FACTURAS ADD CONSTRAINT PRO_CLIENTESFACTURAS_FK01 FOREIGN KEY (cli_id) REFERENCES PRO_CLIENTES (cli_id)
;


ALTER TABLE PRO_TANDAS ADD CONSTRAINT PRO_RESERVACIONTANDAS_FK01 FOREIGN KEY (res_id) REFERENCES PRO_RESERVACION (res_id)
;


ALTER TABLE PRO_RESERVACION ADD CONSTRAINT PRO_CLIENTESRESERVACION_FK01 FOREIGN KEY (cli_id) REFERENCES PRO_CLIENTES (cli_id)
;




-- Grant permissions section -------------------------------------------------




