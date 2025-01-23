package br.com.fiap.techchallenge.applicationlayer.exceptions.messages;

/**
 * Lista de exceções do tipo "Not Found".
 */
public enum EnumNotFoundExceptions {

  PRODUTO_NAO_ENCONTRADO("Produto não encontrado."),
  PRODUTO_LISTA_VAZIA("Nenhum produto encontrado."),

  PEDIDO_NAO_ENCONTRADO("Pedido não encontrado."),
  PEDIDO_LISTA_VAZIA("Nenhum pedido encontrado."),

  CLIENTE_NAO_ENCONTRADO("Cliente não encontrado.");

  private String mensagem;

  EnumNotFoundExceptions(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }
}
