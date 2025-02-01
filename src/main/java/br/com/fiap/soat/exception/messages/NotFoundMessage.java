package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  CPF_CLIENTE("Nenhum cliente foi encontrado para o CPF informado."),
  COD_CLIENTE("Nenhum cliente foi encontrado para o código informado."),
  COD_PRODUTO("Nenhum produto foi encontrado para o código informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
