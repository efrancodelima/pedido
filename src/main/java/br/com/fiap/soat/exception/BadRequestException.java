package br.com.fiap.soat.exception;

/**
 * Exceção customizada para requisições inválidas ao microsserviço.
 */
public class BadRequestException extends Exception {

  /**
   * O construtor público.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public BadRequestException(String msg) {
    super(msg);
  }

}
