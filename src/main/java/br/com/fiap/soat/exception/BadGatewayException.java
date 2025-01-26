package br.com.fiap.soat.exception;

/**
 * Exceção customizada para erros de comunicação com outros microsserviços.
 */
public class BadGatewayException extends Exception {

  /**
   * Construtor.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public BadGatewayException(String msg) {
    super(msg);
  }
}
