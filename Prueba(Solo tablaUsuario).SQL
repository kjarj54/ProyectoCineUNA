/*
Created: 18/10/2022
Modified: 18/10/2022
Model: PruebaPRoyecto
Database: Oracle 18c
*/




-- Create users section -------------------------------------------------

-- User cineuna has empty SQL definition.

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE PRO_CLIENTES_SEQ01
 INCREMENT BY 1
 START WITH 0
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

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
  cli_sapellido Varchar2(10 ),
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

-- Trigger for sequence PRO_CLIENTES_SEQ01 for column cli_id in table cineuna.PRO_CLIENTES ---------
CREATE OR REPLACE TRIGGER cineuna.ts_PRO_CLIENTES_PRO_CLIENTES_SEQ01 BEFORE INSERT
ON cineuna.PRO_CLIENTES FOR EACH ROW
BEGIN
  :new.cli_id := PRO_CLIENTES_SEQ01.nextval;
END;
;
CREATE OR REPLACE TRIGGER cineuna.tsu_PRO_CLIENTES_PRO_CLIENTES_SEQ01 AFTER UPDATE OF cli_id
ON cineuna.PRO_CLIENTES FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cli_id in table cineuna.PRO_CLIENTES as it uses sequence.');
END;
;




-- Grant permissions section -------------------------------------------------




