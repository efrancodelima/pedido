package br.com.fiap.soat.exception;

/**
 * Exceção de regra de negócio.
 */
public class BusinessRuleException extends Exception {

  /**
   * O construtor da exceção.
   *
   * @param msg A mensagem da exceção.
   */
  public BusinessRuleException(String msg) {
    super(msg);
  }

}
