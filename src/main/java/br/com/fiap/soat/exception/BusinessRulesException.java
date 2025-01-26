package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BusinessRulesMessage;

/**
 * Exceção customizada para recursos não encontrados na base de dados.
 */
public class BusinessRulesException extends Exception {

  /**
   * O construtor público.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public BusinessRulesException(BusinessRulesMessage msg) {
    super(msg.getMessage());
  }

}
