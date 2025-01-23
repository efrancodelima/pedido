package br.com.fiap.techchallenge.businesslayer.exceptions.messages;

/**
 * Lista de exceções lançadas pela classe cliente.
 */
public enum ClienteExceptions {
  CODIGO_MIN("O código do cliente deve ser maior que 0."),
  NOME_MAX_CHAR("O nome do cliente não pode ter mais de 50 caracteres."),
  NOME_INVALIDO("O nome do cliente deve conter, no mínimo, "
    + "uma palavra com três ou mais caracteres."),
  CPF_NULO("Informe o CPF do cliente."),
  EMAIL_MAX_CHAR("O e-mail não pode ter mais de 40 caracteres."),
  EMAIL_INVALIDO("O e-mail informado é inválido."),
  NOME_EMAIL_NULOS("Informe o nome ou o email do cliente (um dos dois ou ambos).");

  private String mensagem;

  ClienteExceptions(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }
}
