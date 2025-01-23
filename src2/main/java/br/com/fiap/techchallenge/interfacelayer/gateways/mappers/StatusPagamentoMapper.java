package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPagamentoJpa;

/**
 * Classe StatusPagamentoMapper.
 */
public final class StatusPagamentoMapper {

  private StatusPagamentoMapper() {}

  /**
   * Mapeia um objeto do tipo StatusPagamento para o tipo StatusPagamentoJpa.
   *
   * @param status O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static StatusPagamentoJpa getStatusPagamentoJpa(StatusPagamento status) {
    return new StatusPagamentoJpa(status.getCodigo(), status.getStatus(), status.getDataHora());
  }

  /**
   * Mapeia um objeto do tipo StatusPagamentoJpa para o tipo StatusPagamento.
   *
   * @param status O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static StatusPagamento getStatusPagamento(StatusPagamentoJpa status)
      throws BusinessRuleException {
    
    return new StatusPagamento(status.getCodigo(), status.getStatus(), status.getDataHora());
  }

}
