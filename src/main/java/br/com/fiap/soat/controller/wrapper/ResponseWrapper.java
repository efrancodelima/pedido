package br.com.fiap.soat.controller.wrapper;

/**
 * Classe para encapsular o objeto de retorno da API,
 * no caso da requisição ser bem sucedida, ou o erro, no caso constrário.
 */
public class ResponseWrapper<T> {

  private T data;
  private String errorMsg;

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
   * @param msgError A mensagem de erro a ser inserida no corpo da resposta.
   */
  public ResponseWrapper(String msgError) {
    this.errorMsg = msgError;
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
