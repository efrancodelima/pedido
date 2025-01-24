package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Exceção customizada para recursos não encontrados na base de dados.
 */
public class BadRequestException extends Exception {

  /**
   * O construtor público.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public BadRequestException(BadRequestMessage msg) {
    super(msg.getMessage());
  }

}
