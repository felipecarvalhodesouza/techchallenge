-- Inserção de dados de exemplo
INSERT IGNORE INTO produto (ds_nome_produto, ds_tipo_produto, nm_valor) VALUES
    ('X-Bacon', 'LANCHE', 15.99),
    ('Cachorro-Quente', 'LANCHE', 8.99),
    ('Cheeseburger', 'LANCHE', 12.49),
    ('Frango Grelhado', 'LANCHE', 14.79),
    ('Batata Frita (Pequena)', 'ACOMPANHAMENTO', 5.99),
    ('Batata Frita (Média)', 'ACOMPANHAMENTO', 7.29),
    ('Coca-Cola (Pequena)', 'BEBIDA', 3.49),
    ('Coca-Cola (Média)', 'BEBIDA', 4.09),
    ('Suco de Laranja Natural', 'BEBIDA', 6.99),
    ('Salada Caesar', 'ACOMPANHAMENTO', 10.99),
    ('Sorvete de Chocolate', 'ACOMPANHAMENTO', 6.79),
    ('Milkshake de Morango', 'ACOMPANHAMENTO', 8.99),
    ('Sanduíche de Peito de Peru', 'LANCHE', 11.99),
    ('Água Mineral (500ml)', 'BEBIDA', 2.49),
    ('Cheese Fries', 'ACOMPANHAMENTO', 9.99),
    ('Torta de Maçã', 'ACOMPANHAMENTO', 4.49),
    ('Iced Tea (Grande)', 'BEBIDA', 3.79),
    ('Wrap de Frango', 'LANCHE', 13.49);
    
INSERT IGNORE INTO cliente (id, ds_cpf, ds_email, ds_nome) VALUES
    (1, 12345678909, 'email@provedor.com.br', 'Felipe Carvalho de Souza');