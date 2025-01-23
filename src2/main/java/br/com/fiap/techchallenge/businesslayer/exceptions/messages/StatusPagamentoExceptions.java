package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

import br.com.fiap.techchallenge.businesslayer.constants.Validacao;

public enum StatusPagamentoExceptions {
    CODIGO_MIN("O código do pagamento deve ser maior que zero."),
    CODIGO_ALTERADO("O código do pagamento não pode ser alterado."),
    STATUS_NULO("O status do pagamento não pode ser nulo."),
    DATA_HORA_NULO("A data/hora do pagamento não pode ser nula."),
    DATA_HORA_MIN("A data do pagamento não pode ser anterior a " + Validacao.DATA_MIN_STR + "."),
    DATA_HORA_MAX("A data/hora do pagamento não pode ser maior que a data/hora atual.");

    private String mensagem;

    StatusPagamentoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
