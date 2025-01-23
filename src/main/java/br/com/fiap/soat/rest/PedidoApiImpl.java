package br.com.fiap.soat.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para a API pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoApiImpl implements PedidoApi {

  // Atributos
  private final PedidoControllerImpl controller;

  /**
   * O construtor público da classe.
   *
   * @param controller O controller de Cliente.
   */
  @Autowired
  public PedidoApiImpl(PedidoControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.fazerCheckout(pedidoDto);
  }

  @Override
  public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(long numeroPedido)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.atualizarStatusPedido(numeroPedido);
  }

  @Override
  public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(long numeroPedido)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.consultarStatusPagamento(numeroPedido);
  }

  @Override
  public ResponseEntity<List<Pedido>> listarPedidos()
      throws BusinessRuleException, ResourceNotFoundException {

    return controller.listarPedidos();
  }

  @Override
  public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.webhookMercadoPago(pagamentoDto);
  }

}