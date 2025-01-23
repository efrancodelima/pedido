package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.EnumExceptions;

/**
 * Lista de estados do pedido.
 */
public enum StatusPedidoEnum {

  RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO;

  /**
   * Retorna o enum correspondente a partir de uma string.
   *
   * @param statusPedidoStr Estado do pedido (string).
   * @return Estado do pedido (enum).
   * @throws BusinessRuleException Exceção de regra de negócio lançada pela operação.
   */
  public static StatusPedidoEnum fromString(String statusPedidoStr) throws BusinessRuleException {

    statusPedidoStr = statusPedidoStr == null ? null : statusPedidoStr.toUpperCase().trim();

    try {
      return StatusPedidoEnum.valueOf(statusPedidoStr);

    } catch (Exception e) {
      throw new BusinessRuleException(EnumExceptions.STATUS_PEDIDO.getMensagem());
    }
  }
}
