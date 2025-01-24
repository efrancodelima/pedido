package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.NotFoundMessage;

/**
 * Exceção customizada da aplicação.
 */
public class NotFoundException extends Exception {

  /**
   * Construtor.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public NotFoundException(NotFoundMessage msg) {
    super(msg.getMessage());
  }

}
