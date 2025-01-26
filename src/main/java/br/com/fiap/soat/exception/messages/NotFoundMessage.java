package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Not Found.
 */
public enum NotFoundMessage {

  CLIENTE("Cliente não encontrado."),
  COD_CLIENTE("Nenhum cliente encontrado para o código informado."),
  COD_PRODUTO("Nenhum produto encontrado para o código informado.");

  private String mensagem;

  NotFoundMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
