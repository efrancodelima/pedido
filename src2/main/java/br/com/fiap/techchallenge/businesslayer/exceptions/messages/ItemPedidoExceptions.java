package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

public enum ItemPedidoExceptions {
    PRODUTO_NULO("Informe um produto para o item."),
    QTDE_NULA("Informe a quantidade do item."),
    QTDE_MIN("A quantidade do item deve ser maior que zero."),
    QTDE_MAX("A quantidade máxima permitida para o item é 50.");

    private String mensagem;

    ItemPedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
