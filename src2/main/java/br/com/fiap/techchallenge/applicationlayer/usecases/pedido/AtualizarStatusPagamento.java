package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe AtualizarStatusPagamento.
 */
public final class AtualizarStatusPagamento {

  private AtualizarStatusPagamento() {}

  /**
   * Atualizar o status do pagamento do pedido.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param statusPagamento O novo status do pagamento.
   * @throws ApplicationException Exceção de aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static void atualizar(InPedidoGateway gateway, StatusPagamento statusPagamento)
        throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Validar.notNull(statusPagamento, EnumApplicationExceptions.PAGAMENTO_STATUS_NULO);

    Pedido pedido = gateway.buscarPedidoPeloCodigoPagamento(statusPagamento.getCodigo());
    Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

    pedido.setStatusPagamento(statusPagamento);
    gateway.atualizarPedido(pedido);
  }

}
