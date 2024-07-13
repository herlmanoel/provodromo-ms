INSERT INTO tb_tipo_usuario (id, nome) VALUES (1, 'Admin');
INSERT INTO tb_tipo_usuario (id, nome) VALUES (2, 'User');


INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (1, 'João Silva', 'joao.silva@example.com', 'senha123', 1);
INSERT INTO tb_usuario (id, nome, email, senha, tipo_usuario_id) VALUES (2, 'Maria Oliveira', 'maria.oliveira@example.com', 'senha456', 2);


INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (1, 'Histórico Admin', '2023-07-13T16:07:50', 1);
INSERT INTO tb_hist_tipo_usuario (id, nome, data_alteracao, tipo_usuario_id) VALUES (2, 'Histórico User', '2023-07-13T16:08:00', 2);
