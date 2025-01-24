package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Gateway.
 */
public enum BadGatewayMessage {

  PAGAMENTO("Erro na comunicação com o sistema de pagamentos."),
  PRODUCAO("Erro na comunicação com o sistema de produção.");

  private String mensagem;

  BadGatewayMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
