-- Tabela cliente
INSERT INTO
    cliente (cpf, nome, email)
VALUES 
    (23456789092, 'Maria Clara de Oliveira', 'maria_oliveira@gmail.com'),
    (34567890175, 'Carlos Eduardo de Souza', 'carlos_souza@gmail.com'),
    (45678901249, 'Ana Paula Pereira', 'ana_pereira@gmail.com'),
    (56789012303, 'Lucas Henrique Fernandes', 'lucas_fernandes@gmail.com'),
    (12345678909, 'João Pedro da Silva', 'joao_silva@gmail.com');

-- Tabela produto
INSERT INTO
    produto (nome, descricao, preco, categoria)
VALUES
    ('Batata Frita', 'Batata frita crocante', 5.50, 'ACOMPANHAMENTO'),
    ('Anéis de Cebola', 'Anéis de cebola empanados', 6.00, 'ACOMPANHAMENTO'),
    ('Nuggets de Frango', 'Porção de nuggets de frango', 7.00, 'ACOMPANHAMENTO'),
    ('Mandioca Frita', 'Porção de mandioca frita', 5.00, 'ACOMPANHAMENTO'),

    ('Refrigerante Cola', 'Refrigerante de cola 350ml', 3.50, 'BEBIDA'),
    ('Suco de Laranja', 'Suco de laranja natural 300ml', 4.00, 'BEBIDA'),
    ('Água Mineral', 'Água mineral sem gás 500ml', 2.50, 'BEBIDA'),
    ('Chá Gelado', 'Chá gelado de limão 300ml', 3.00, 'BEBIDA'),

    ('Cheeseburger Bacon', 'Hambúrguer com queijo e bacon', 12.00, 'LANCHE'),
    ('Sanduíche de Frango', 'Sanduíche de frango grelhado', 10.00, 'LANCHE'),
    ('Wrap Vegetariano', 'Wrap vegetariano com legumes', 9.00, 'LANCHE'),
    ('Hot Dog', 'Hot dog com molho especial', 8.00, 'LANCHE'),

    ('Sorvete de Chocolate', 'Sorvete de chocolate 2 bolas', 6.50, 'SOBREMESA'),
    ('Torta de Maçã', 'Torta de maçã com canela', 5.50, 'SOBREMESA'),
    ('Brownie', 'Brownie de chocolate com nozes', 7.00, 'SOBREMESA'),
    ('Pudim', 'Pudim de leite condensado', 4.50, 'SOBREMESA');

-- Tabela pedido
INSERT INTO 
    pedido (codigo_cliente, timestamp_checkout)
VALUES
    (1, '2025-01-22 13:00:00'),
    (2, '2025-01-22 13:01:00'),
    (3, '2025-01-22 13:02:00'),
    (4, '2025-01-22 13:03:00'),
    (5, '2025-01-22 13:04:00');

-- Tabela itens_pedido
INSERT INTO
    itens_pedido (numero_pedido, codigo_produto, quantidade)
VALUES
    (1, 1, 2),
    (1, 2, 1),
    (2, 3, 1),
    (2, 4, 2),
    (3, 5, 1),
    (3, 6, 3),
    (4, 7, 2),
    (4, 8, 1),
    (5, 9, 2),
    (5, 10, 1);