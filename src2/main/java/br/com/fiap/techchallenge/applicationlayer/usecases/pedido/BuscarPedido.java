package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe BuscarPedido.
 */
public final class BuscarPedido {

  private BuscarPedido() {}

  /**
   * Busca o pedido pelo número.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param numeroPedido o número do pedido quer será buscado.
   * @return O pedido encontrado.
   * @throws ApplicationException Exceção de aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static Pedido buscar(InPedidoGateway gateway, Long numeroPedido)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);

    Pedido pedido = gateway.buscarPedido(numeroPedido);
    Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

    return pedido;
  }

}
