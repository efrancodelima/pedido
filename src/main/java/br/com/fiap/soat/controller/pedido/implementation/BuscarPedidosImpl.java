package br.com.fiap.soat.controller.pedido.implementation;

import br.com.fiap.soat.controller.pedido.contract.BuscarPedidos;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.pedido.BuscarPedidosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para fazer checkout do pedido.
 */
@RestController
@RequestMapping("/pedidos")
public class BuscarPedidosImpl implements BuscarPedidos {

  private final BuscarPedidosService service;

  @Autowired
  public BuscarPedidosImpl(BuscarPedidosService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<PedidoJpa>>> buscarPedidos(List<Long> numerosPedidos) {
    
    try {
      var statusPedidoDto = service.execute(numerosPedidos);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(statusPedidoDto));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}
