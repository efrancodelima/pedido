package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPedidoJpa;

/**
 * Classe StatusPedidoMapper.
 */
public final class StatusPedidoMapper {

  private StatusPedidoMapper() {}

  /**
   * Mapeia um objeto do tipo StatusPedido para o tipo StatusPedidoJpa.
   *
   * @param status O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static StatusPedidoJpa getStatusPedidoJpa(StatusPedido status) {
    return new StatusPedidoJpa(status.getStatus(), status.getDataHora());
  }

  /**
   * Mapeia um objeto do tipo StatusPedidoJpa para o tipo StatusPedido.
   *
   * @param status O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static StatusPedido getStatusPedido(StatusPedidoJpa status)
      throws BusinessRuleException {

    return new StatusPedido(status.getStatus(), status.getDataHora());
  }

}
