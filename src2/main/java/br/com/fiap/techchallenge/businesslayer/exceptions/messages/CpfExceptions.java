package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

public enum CpfExceptions {
    CPF_NULO("Informe o CPF do cliente."),
    NUMERO_NULO("Informe o número do CPF (além dos dígitos verificadores)."),
    NUMERO_MIN("O número do CPF deve ser maior que 0 (sem considerar os dígitos verificadores)."),
    NUMERO_MAX("O número do CPF não pode ter mais de 9 dígitos."),
    DIGITO_NULO("Informe o dígito verificador do CPF."),
    DIGITO_INVALIDO("O dígito verificador do CPF é inválido.");

    private String mensagem;

    CpfExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
