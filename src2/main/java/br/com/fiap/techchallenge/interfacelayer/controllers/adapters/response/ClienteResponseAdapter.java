package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe ClienteResponseAdapter.
 */
public final class ClienteResponseAdapter {

  private ClienteResponseAdapter() {}

  /**
   * Adapta um objeto do tipo Cliente para o tipo ResponseEntity-Cliente.
   *
   * @param cliente O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<Cliente> adaptar(Cliente cliente, HttpStatus httpStatus) {
    return new ResponseEntity<>(cliente, httpStatus);
  }

}
