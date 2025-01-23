package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

public enum EnumExceptions {

    CATEGORIA_PRODUTO("A categoria do produto é inválida."),
    STATUS_PAGAMENTO("O status do pagamento é inválido."),
    STATUS_PEDIDO("O status do pedido é inválido.");

    private String mensagem;

    EnumExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

}
