package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BusinessRulesMessage;

/**
 * Exceção customizada para regras de negócio.
 */
public class BusinessRulesException extends Exception {

  public BusinessRulesException(BusinessRulesMessage msg) {
    super(msg.getMessage());
  }
}
