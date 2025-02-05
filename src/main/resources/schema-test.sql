CREATE TABLE IF NOT EXISTS cliente (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf BIGINT NOT NULL UNIQUE,
    nome VARCHAR(50),
    email VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS produto (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    descricao VARCHAR(150),
    preco DECIMAL(5,2) NOT NULL,
    categoria VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido (
    numero BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_cliente BIGINT,
    timestamp_checkout TIMESTAMP,
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
);

CREATE TABLE IF NOT EXISTS itens_pedido (
    numero_pedido BIGINT,
    codigo_produto BIGINT,
    quantidade INT NOT NULL,
    PRIMARY KEY (numero_pedido, codigo_produto),
    FOREIGN KEY (numero_pedido) REFERENCES pedido(numero),
    FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
);
