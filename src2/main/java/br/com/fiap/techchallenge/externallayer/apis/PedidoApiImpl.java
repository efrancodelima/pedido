package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.PedidoApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.PedidoControllerImpl;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
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
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.fazerCheckout(pedidoDto);
  }

  @Override
  public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.atualizarStatusPedido(numeroPedido);
  }

  @Override
  public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.consultarStatusPagamento(numeroPedido);
  }

  @Override
  public ResponseEntity<List<Pedido>> listarPedidos()
      throws BusinessRuleException, ResourceNotFoundException {

    return controller.listarPedidos();
  }

  @Override
  public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.webhookMercadoPago(pagamentoDto);
  }

}