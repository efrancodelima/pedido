package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.EnumExceptions;

/**
 * Lista de estados do pagamento.
 */
public enum StatusPagamentoEnum {

  AGUARDANDO_PAGAMENTO, APROVADO, REPROVADO;

  /**
   * Retorna o enum correspondente a partir de uma string.
   *
   * @param statusPagamentoStr Estado do pagamento (string).
   * @return Estado do pagamento (enum).
   * @throws BusinessRuleException Exceção de regra de negócio lançada pela operação.
   */
  public static StatusPagamentoEnum fromString(String statusPagamentoStr) 
      throws BusinessRuleException {

    statusPagamentoStr = statusPagamentoStr == null
      ? null : statusPagamentoStr.toUpperCase().trim();

    try {
      return StatusPagamentoEnum.valueOf(statusPagamentoStr);

    } catch (Exception e) {
      throw new BusinessRuleException(EnumExceptions.STATUS_PAGAMENTO.getMensagem());
    }
  }
}
