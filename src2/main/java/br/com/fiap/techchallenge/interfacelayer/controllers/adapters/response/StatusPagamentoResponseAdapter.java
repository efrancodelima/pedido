package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Classe StatusPagamentoResponseAdapter.
 */
public final class StatusPagamentoResponseAdapter {

  private StatusPagamentoResponseAdapter() {}

  /**
   * Adapta um objeto do tipo Pedido para o tipo ResponseEntity-StatusPagamentoDto.
   *
   * @param pedido O objeto a ser adaptado.
   * @param httpStatus O status HTTP do ResponseEntity.
   * @return O objeto adaptado.
   */
  public static ResponseEntity<StatusPagamentoDto> adaptar(Pedido pedido, HttpStatus httpStatus) {

    var status = pedido.getStatusPagamento().getStatus();

    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String dataHora = pedido.getStatusPagamento().getDataHora().format(formatter);

    var response = new StatusPagamentoDto();
    response.setNumeroPedido(pedido.getNumero());
    response.setStatus(status);
    response.setDataHora(dataHora);

    return new ResponseEntity<>(response, httpStatus);
  }

}
