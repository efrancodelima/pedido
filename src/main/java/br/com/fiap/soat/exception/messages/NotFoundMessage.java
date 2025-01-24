package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  CLIENTE("Cliente não encontrado."),
  COD_CLIENTE("Não foi encontrado nenhum cliente para o código informado."),
  COD_PRODUTO("Não foi encontrado nenhum produto para o código informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
