package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe PedidoResponseAdapter.
 */
public class PedidoResponseAdapter {

  private PedidoResponseAdapter() {}

  /**
   * Adapta um objeto do tipo Pedido para o tipo ResponseEntity.
   *
   * @param pedido O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<Pedido> adaptar(Pedido pedido, HttpStatus httpStatus) {
    return new ResponseEntity<>(pedido, httpStatus);
  }

  /**
   * Adapta um objeto do tipo List-Pedido para um objeto do tipo ResponseEntity-List-Pedido.
   *
   * @param pedidos O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<List<Pedido>> adaptar(List<Pedido> pedidos, HttpStatus httpStatus) {
    return new ResponseEntity<>(pedidos, httpStatus);
  }
}
