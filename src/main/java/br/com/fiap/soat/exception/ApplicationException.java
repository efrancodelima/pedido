package br.com.fiap.soat.exception;

/**
 * Exceção customizada da aplicação.
 */
public class ApplicationException extends Exception {

  /**
   * Construtor.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public ApplicationException(String msg) {
    super(msg);
  }

}
