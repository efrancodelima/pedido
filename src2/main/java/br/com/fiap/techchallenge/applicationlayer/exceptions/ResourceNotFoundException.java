package br.com.fiap.techchallenge.applicationlayer.exceptions;

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
