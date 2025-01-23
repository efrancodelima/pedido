package br.com.fiap.soat.exception.messages;

/**
 * Lista de exceções do tipo "Not Found".
 */
public enum NotFoundMessages {

  PRODUTO_NAO_ENCONTRADO("Produto não encontrado."),
  PRODUTO_LISTA_VAZIA("Nenhum produto encontrado."),

  PEDIDO_NAO_ENCONTRADO("Pedido não encontrado."),
  PEDIDO_LISTA_VAZIA("Nenhum pedido encontrado."),

  CLIENTE_NAO_ENCONTRADO("Cliente não encontrado.");

  private String mensagem;

  NotFoundMessages(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }
}
