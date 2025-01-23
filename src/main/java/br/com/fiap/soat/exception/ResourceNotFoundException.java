package br.com.fiap.soat.exception;

/**
 * Exceção customizada da aplicação.
 */
public class ResourceNotFoundException extends Exception {

  /**
   * Construtor.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public ResourceNotFoundException(String msg) {
    super(msg);
  }

}
