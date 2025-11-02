-- V2__seed_users.sql
-- Inserção de dados iniciais

-- Usuário Admin (senha: admin123)
-- Hash BCrypt gerado para 'admin123': $2a$10$XQKEno.bETRK26Zarsp8ieXSbb8dbAbQD/mxT9w8RhxGrMYrcNWWq
INSERT INTO USUARIO (USERNAME, PASSWORD, NOME, EMAIL, ROLE, ATIVO) 
VALUES ('admin', '$2a$10$XQKEno.bETRK26Zarsp8ieXSbb8dbAbQD/mxT9w8RhxGrMYrcNWWq', 'Administrador', 'admin@esg.com', 'ROLE_ADMIN', 1);

-- Usuário comum (senha: user123)
-- Hash BCrypt gerado para 'user123': $2a$10$LiMMl.BBIs8qYXjTZTyoVeFY71vUUQu.NYvp34TJ0C7IKF1sw3R/6
INSERT INTO USUARIO (USERNAME, PASSWORD, NOME, EMAIL, ROLE, ATIVO) 
VALUES ('user', '$2a$10$LiMMl.BBIs8qYXjTZTyoVeFY71vUUQu.NYvp34TJ0C7IKF1sw3R/6', 'Usuário Comum', 'user@esg.com', 'ROLE_USER', 1);

-- Unidades de exemplo
INSERT INTO UNIDADE (NOME, LOCALIZACAO, LIMITE_CONSUMO_DIARIO_KWH, ATIVO) 
VALUES ('Unidade Central', 'Av. Paulista, 1000 - São Paulo/SP', 500.00, 1);

INSERT INTO UNIDADE (NOME, LOCALIZACAO, LIMITE_CONSUMO_DIARIO_KWH, ATIVO) 
VALUES ('Filial Sul', 'Rua das Flores, 250 - Curitiba/PR', 300.00, 1);

INSERT INTO UNIDADE (NOME, LOCALIZACAO, LIMITE_CONSUMO_DIARIO_KWH, ATIVO) 
VALUES ('Filial Norte', 'Av. Amazonas, 500 - Manaus/AM', 400.00, 1);

-- Leituras de exemplo para a Unidade Central
INSERT INTO LEITURA (UNIDADE_ID, TIMESTAMP, CONSUMO_KWH, SENSOR_ID) 
VALUES (1, TIMESTAMP '2025-11-01 08:00:00', 45.5, 'SENSOR-001');

INSERT INTO LEITURA (UNIDADE_ID, TIMESTAMP, CONSUMO_KWH, SENSOR_ID) 
VALUES (1, TIMESTAMP '2025-11-01 12:00:00', 78.3, 'SENSOR-001');

INSERT INTO LEITURA (UNIDADE_ID, TIMESTAMP, CONSUMO_KWH, SENSOR_ID) 
VALUES (1, TIMESTAMP '2025-11-01 18:00:00', 112.7, 'SENSOR-001');

-- Alerta de exemplo
INSERT INTO ALERTA (UNIDADE_ID, TIPO, MENSAGEM, CONSUMO_REGISTRADO_KWH, LIMITE_KWH, LIDO) 
VALUES (1, 'CONSUMO_EXCEDIDO', 'Consumo diário ultrapassou o limite estabelecido', 550.00, 500.00, 0);

-- Agendamento de exemplo
INSERT INTO AGENDAMENTO (UNIDADE_ID, DESCRICAO, TIPO_ACAO, DATA_HORA_AGENDADA, STATUS, OBSERVACAO) 
VALUES (1, 'Manutenção preventiva do sistema de climatização', 'MANUTENCAO', TIMESTAMP '2025-11-15 14:00:00', 'PENDENTE', 'Verificar eficiência dos compressores');

COMMIT;
