package br.com.fiap.soat.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Classe para encapsular o retorno dos controllers,
 * no caso da requisição ser bem sucedida, ou o erro, no caso constrário.
 */
public class ResponseWrapper<T> {

  private T data;
  private String errorMsg;

  /**
   * Construtor público da classe.
   *
   * @param data O objeto a ser inserido no corpo da resposta.
   */
  @JsonCreator
  public ResponseWrapper(T data, String errorMsg) {
    this.data = data;
    this.errorMsg = errorMsg;
  }

  public ResponseWrapper(T data) {
    this.data = data;
    this.errorMsg = null;
  }

  public ResponseWrapper(String errorMsg) {
    this.data = null;
    this.errorMsg = errorMsg;
  }

  // Getters e setters
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String error) {
    this.errorMsg = error;
  }
}
