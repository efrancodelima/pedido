package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

import br.com.fiap.techchallenge.businesslayer.constants.Validacao;

public enum PedidoExceptions {
    NUMERO_MIN("O número do pedido deve ser maior que 0."),
    NUMERO_NULO("O número do pedido não pode ser nulo."),

    ITEM_NULO("O item do pedido não pode ser nulo."),
    
    ITENS_NULO("A lista de itens do pedido não pode ser nula."),
    ITENS_VAZIO("O pedido precisa conter, pelo menos, um item."),
    ITENS_APOS_CHECKOUT("Não é possível alterar os itens do pedido após a realização do chekout."),
    
    DATA_CHECKOUT_NULA("A data do checkout não pode ser nula se o checkout já foi feito."),
    DATA_CHECKOUT_MIN("A data do checkout não pode ser menor que " + Validacao.DATA_MIN_STR + "."),
    DATA_CHECKOUT_MAX("A data/hora do checkout não pode ser maior que a data/hora atual."),
    
    STATUS_NULO("O status do pedido não pode ser nulo."),
    
    PAGAMENTO_NULO("O status do pagamento não pode ser nulo."),
    PAGAMENTO_NAO_APROVADO("O pedido ainda não teve o pagamento aprovado."),
    PAGAMENTO_JA_APROVADO("Não é permitido alterar o pagamento do pedido, pois o pagamento "
      + "já foi aprovado."),

    CHECKOUT_REALIZADO("O checkout do pedido já foi realizado."),
    PEDIDO_FINALIZADO("O pedido já foi finalizado.");

    private String mensagem;

    PedidoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
