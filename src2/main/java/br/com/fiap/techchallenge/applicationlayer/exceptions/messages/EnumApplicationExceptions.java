package br.com.fiap.techchallenge.applicationlayer.exceptions.messages;

/**
 * Lista de exceções da aplicação.
 */
public enum EnumApplicationExceptions {

  PRODUTO_NULO("O produto não pode ser nulo."),
  PRODUTO_CODIGO_NULO("Informe o código do produto."),
  PRODUTO_CODIGO_MIN("O código do produto deve ser maior que zero."),

  CATEGORIA_NULA("A categoria do produto não pode ser nula."),

  PEDIDO_NULO("O pedido não pode ser nulo."),
  PEDIDO_NUMERO_NULO("Informe o número do pedido."),
  PEDIDO_NUMERO_MIN("O número do pedido deve ser maior que zero."),

  PAGAMENTO_ERRO_CRIACAO("Um erro inesperado ocorreu! Não foi possível"
    + " gerar um pagamento para este pedido."),
  PAGAMENTO_STATUS_NULO("O status do pagamento não pode ser nulo."),

  CLIENTE_NULO("O cliente não pode ser nulo."),
  CLIENTE_JA_EXISTE("Já existe um cliente cadastrado com esse CPF."),

  CPF_NULO("O CPF não pode ser nulo.");

  private String mensagem;

  EnumApplicationExceptions(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
