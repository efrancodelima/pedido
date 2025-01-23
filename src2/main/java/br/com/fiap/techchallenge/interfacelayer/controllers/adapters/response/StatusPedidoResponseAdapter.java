package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe StatusPedidoResponseAdapter.
 */
public final class StatusPedidoResponseAdapter {

  private StatusPedidoResponseAdapter() {}

  /**
   * Adapta um objeto do tipo Pedido para o tipo ResponseEntity-StatusPedidoDto.
   *
   * @param pedido O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<StatusPedidoDto> adaptar(Pedido pedido, HttpStatus httpStatus) {
    StatusPedidoDto response = adaptarParaStatusPedido(pedido);
    return new ResponseEntity<>(response, httpStatus);
  }

  /**
   * Adapta um objeto do tipo List-Pedido para ResponseEntity-List-StatusPedidoDto.
   *
   * @param pedidos O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<List<StatusPedidoDto>> adaptar(List<Pedido> pedidos,
      HttpStatus httpStatus) {
    List<StatusPedidoDto> response = adaptarParaListaPedidos(pedidos);
    return new ResponseEntity<>(response, httpStatus);
  }

  // Métodos privados
  private static StatusPedidoDto adaptarParaStatusPedido(Pedido pedido) {
    var status = pedido.getStatusPedido().getStatus();

    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String dataHora = pedido.getStatusPedido().getDataHora().format(formatter);

    var response = new StatusPedidoDto();
    response.setNumeroPedido(pedido.getNumero());
    response.setStatus(status);
    response.setDataHora(dataHora);

    return response;
  }

  private static List<StatusPedidoDto> adaptarParaListaPedidos(List<Pedido> pedidos) {
    List<StatusPedidoDto> response = new ArrayList<>();

    for (Pedido pedido : pedidos) {
      StatusPedidoDto status = adaptarParaStatusPedido(pedido);
      response.add(status);
    }
    return response;
  }

}
