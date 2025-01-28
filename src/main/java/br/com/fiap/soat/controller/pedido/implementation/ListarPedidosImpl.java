package br.com.fiap.soat.controller.pedido.implementation;

import br.com.fiap.soat.controller.pedido.contract.ListarPedidos;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.controller.response.PedidoEmProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.service.provider.pedido.ListarPedidosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para exibir a fila de pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class ListarPedidosImpl implements ListarPedidos {

  private final ListarPedidosService service;

  @Autowired
  public ListarPedidosImpl(ListarPedidosService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<PedidoEmProducaoDto>>> listarPedidos() {

    try {
      var lista = service.execute();
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(lista));

    } catch (BadGatewayException e) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    }
  }
}
