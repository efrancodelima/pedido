package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.constants.Validacao;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.StatusPedidoExceptions;
import java.time.LocalDateTime;

/**
 * Situação do pedido.
 */
public class StatusPedido {

  // Atributos
  private final StatusPedidoEnum status;
  private final LocalDateTime dataHora;

  /**
   * Construtor público.
   *
   * @param status Situação do pedido.
   * @param dataHora Data e hora do status.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public StatusPedido(StatusPedidoEnum status,
      LocalDateTime dataHora) throws BusinessRuleException {
    
    validarStatusPedido(status, dataHora);
    this.status = status;
    this.dataHora = dataHora;
  }

  // Getters
  public StatusPedidoEnum getStatus() {
    return status;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  // Métodos de validação
  private void validarStatusPedido(StatusPedidoEnum status,
      LocalDateTime dataHora) throws BusinessRuleException {
    
    validarStatus(status);
    validarDataHora(dataHora);
  }

  private void validarStatus(StatusPedidoEnum status) throws BusinessRuleException {
    if (status == null) {
      throw new BusinessRuleException(StatusPedidoExceptions.STATUS_NULO.getMensagem());
    }
  }

  private void validarDataHora(LocalDateTime dataHora) throws BusinessRuleException {
    if (dataHora == null) {
      throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_NULO.getMensagem());
    }

    if (dataHora.toLocalDate().isBefore(Validacao.DATA_MIN)) {
      throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_MIN.getMensagem());
    }

    if (dataHora.isAfter(LocalDateTime.now())) {
      throw new BusinessRuleException(StatusPedidoExceptions.DATA_HORA_MAX.getMensagem());
    }
  }
}
