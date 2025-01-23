package br.com.fiap.soat.exception.messages2;

public enum ProdutoExceptions {
    CODIGO_MIN("O código do produto deve ser maior que 0."),
    NOME_VAZIO("Informe o nome do produto!"),
    NOME_MIN_CHAR("O nome do produto precisa ter, no mínimo, 5 caracteres."),
    NOME_MAX_CHAR("O nome do produto não pode ter mais de 20 caracteres."),
    DESCRICAO_MIN("A descrição do produto precisa ter, no mínimo, 20 caracteres."),
    DESCRICAO_MAX("A descrição do produto não pode ter mais de 150 caracteres."),
    PRECO_NULO("Informe o preço do produto."),
    PRECO_MIN("O preço deve ser maior que zero."),
    PRECO_MAX("O preço não pode ser maior que R$ 300,00."),
    CATEGORIA_NULA("Informe a categoria do produto!");

    private String mensagem;

    ProdutoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}