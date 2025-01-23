package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.constants.Validacao;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.StatusPagamentoExceptions;
import java.time.LocalDateTime;

/**
 * Situação do pagamento.
 */
public class StatusPagamento {

  // Atributos
  private Long codigo;
  private StatusPagamentoEnum status;
  private LocalDateTime dataHora;

  /**
   * Construtor público.
   *
   * @param codigo Código do pagamento.
   * @param status Situação do pagamento.
   * @param dataHora Data e hora do status.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public StatusPagamento(Long codigo,
      StatusPagamentoEnum status, LocalDateTime dataHora) throws BusinessRuleException {

    validarCodigo(codigo);
    validarStatusPagamento(status, dataHora);
    this.codigo = codigo;
    this.status = status;
    this.dataHora = dataHora;
  }

  // Getters e setters
  public Long getCodigo() {
    return codigo;
  }

  /**
   * Define o código do pagamento.
   *
   * @param codigo Código do pagamento.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public void setCodigo(Long codigo) throws BusinessRuleException {
    if (this.codigo != null) {
      throw new BusinessRuleException(StatusPagamentoExceptions.CODIGO_ALTERADO.getMensagem());
    } else {
      validarCodigo(codigo);
      this.codigo = codigo;
    }
  }

  public StatusPagamentoEnum getStatus() {
    return status;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  // Métodos de validação
  private void validarCodigo(Long codigo) throws BusinessRuleException {
    if (codigo != null && codigo < 1) {
      throw new BusinessRuleException(StatusPagamentoExceptions.CODIGO_MIN.getMensagem());
    }
  }

  private void validarStatusPagamento(StatusPagamentoEnum status, LocalDateTime dataHora)
      throws BusinessRuleException {
    validarStatus(status);
    validarDataHora(dataHora);
  }

  private void validarStatus(StatusPagamentoEnum status) throws BusinessRuleException {
    if (status == null) {
      throw new BusinessRuleException(StatusPagamentoExceptions.STATUS_NULO.getMensagem());
    }
  }

  private void validarDataHora(LocalDateTime dataHora) throws BusinessRuleException {
    if (dataHora == null) {
      throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem());
    }

    if (dataHora.toLocalDate().isBefore(Validacao.DATA_MIN)) {
      throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem());
    }

    if (dataHora.isAfter(LocalDateTime.now())) {
      throw new BusinessRuleException(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem());
    }
  }
}
