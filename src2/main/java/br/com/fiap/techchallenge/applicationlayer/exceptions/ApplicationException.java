package br.com.fiap.techchallenge.applicationlayer.exceptions;

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
