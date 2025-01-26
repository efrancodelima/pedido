package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções de regra de negócio.
 */
public enum BusinessRulesMessage {
    
  CLIENTE_JA_CADASTRADO("Já existe um cliente cadastrado com esse CPF.");

  private String mensagem;

  BusinessRulesMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
