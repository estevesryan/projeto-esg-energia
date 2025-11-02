-- Script para criar usuário e schema no Oracle
-- Execute este script como SYS após subir o container Oracle

-- Conectar como SYSDBA:
-- docker exec -it oracle-esg-db sqlplus sys/OraclePass123@XE as sysdba

-- Criar usuário
CREATE USER appuser IDENTIFIED BY appsecret;

-- Conceder privilégios
GRANT CONNECT, RESOURCE, DBA TO appuser;
GRANT CREATE SESSION TO appuser;
GRANT CREATE TABLE TO appuser;
GRANT CREATE VIEW TO appuser;
GRANT CREATE SEQUENCE TO appuser;
GRANT UNLIMITED TABLESPACE TO appuser;

-- Definir como schema padrão
ALTER USER appuser DEFAULT TABLESPACE USERS;
ALTER USER appuser QUOTA UNLIMITED ON USERS;

-- Verificar
SELECT username, account_status FROM dba_users WHERE username = 'APPUSER';

EXIT;
