-- Inserindo os tipos de usuário
INSERT INTO tb_tipo_usuario (id, nome) VALUES (1, 'ROLE_ADMINISTRADOR');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (2, 'ROLE_COMUM');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (3, 'ROLE_MODERADOR');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (4, 'ROLE_CONVIDADO');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (5, 'ROLE_VISITANTE');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (6, 'ROLE_PROFESSOR');

-- Inserindo os usuários
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (1, 'João Silva', 'joao.silva@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 1);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (2, 'Guilherme', 'Guilherme@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 6);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (3, 'Gustavo', 'Gustavo@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 6);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (4, 'Jane Smith', 'jane.smith@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 2);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (5, 'Alice Johnson', 'alice.johnson@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 3);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (6, 'Bob Brown', 'bob.brown@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 4);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (7, 'Eva Wilson', 'eva.wilson@example.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu', 5);

-- Inserindo os históricos dos tipos de usuário
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (1, 'Histórico Administrador', '2023-07-13T16:07:50', 1);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (2, 'Histórico Comum', '2023-07-13T16:07:50', 2);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (3, 'Histórico Moderador', '2023-07-13T16:07:50', 3);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (4, 'Histórico Convidado', '2023-07-13T16:07:50', 4);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (5, 'Histórico Visitante', '2023-07-13T16:07:50', 5);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (6, 'Histórico Professor', '2023-07-13T16:07:50', 6);
