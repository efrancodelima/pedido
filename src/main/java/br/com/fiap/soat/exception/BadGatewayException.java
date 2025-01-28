package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BadGatewayMessage;

/**
 * Exceção customizada para erros de comunicação com outros microsserviços.
 */
public class BadGatewayException extends Exception {

  public BadGatewayException(String msg) {
    super(msg);
  }

  public BadGatewayException(BadGatewayMessage msg) {
    super(msg.getMessage());
  }
}
