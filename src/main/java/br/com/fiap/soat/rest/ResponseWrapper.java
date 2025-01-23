package br.com.fiap.soat.rest;

/**
 * Classe para encapsular o objeto de retorno da API,
 * no caso da requisição ser bem sucedida, ou o erro, no caso constrário.
 */
public class ResponseWrapper<T> {

  private T data;
  private String error;

  /**
   * O construtor da resposta bem sucedida.
   *
   * @param data O objeto a ser inserido no corpo da resposta.
   */
  public ResponseWrapper(T data) {
    this.data = data;
  }

  /**
   * O construtor da resposta bem sucedida.
   *
   * @param error A mensagem de erro a ser inserida no corpo da resposta.
   */
  public ResponseWrapper(String error) {
    this.error = error;
  }

  // Getters e setters
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
