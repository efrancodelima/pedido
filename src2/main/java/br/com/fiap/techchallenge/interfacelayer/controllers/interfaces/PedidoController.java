package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Interface PedidoController.
 */
public interface PedidoController {

  /**
   * Faz o checkout do pedido.
   *
   * @param pedidoDto O pedido para o qual será feito o checkout.
   * @return O status do pedido.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Atualiza o status do pedido.
   *
   * @param numeroPedido O número do pedido a ser atualizado.
   * @return O status do pedido atualizado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Consulta o status do pagamento do pedido.
   *
   * @param numeroPedido O número do pedido cujo status do pagamento será consultado.
   * @return O status do pagamento do pedido.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(Long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Lista todos os pedidos não finalizados.
   *
   * @return Lista com todos os pedidos não finalizados.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<List<Pedido>> listarPedidos()
      throws BusinessRuleException, ResourceNotFoundException;

  /**
   * Recebe as notificações de pagamento do Mercado Pago.
   *
   * @param pagamentoDto A notificação do Mercado Pago.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

}
