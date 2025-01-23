package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.StatusPagamentoExceptions;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o value object StatusPagamento.
 */
class StatusPagamentoTest {

  private final long codigoValido = 1L;
  private final StatusPagamentoEnum statusValido = StatusPagamentoEnum.APROVADO;
  private final LocalDateTime dataHoraValida = LocalDateTime.now();
  
  @Test
  void deveCriarStatusComSucesso() {
    assertDoesNotThrow(() -> {
      new StatusPagamento(codigoValido, statusValido, dataHoraValida);
    });
  }
  
  @Test
  void deveRetornarOsAtributosDoStatus() throws BusinessRuleException {
    var status = new StatusPagamento(codigoValido, statusValido, dataHoraValida);
  
    assertEquals(codigoValido, status.getCodigo());
    assertEquals(statusValido, status.getStatus());
    assertEquals(dataHoraValida, status.getDataHora());
  }

  @Test
  void deveAceitarCodigoNuloNoConstrutor() {
    assertDoesNotThrow(() -> {
      new StatusPagamento(null, statusValido, dataHoraValida);
    });
  }
  
  @Test
  void naoDeveAlterarCodigoParaNulo2() throws BusinessRuleException  {
    var status = new StatusPagamento(codigoValido, statusValido, dataHoraValida);
  
    assertThrows(BusinessRuleException.class, () -> {
      status.setCodigo(null);
    });
  }
  
  @Test
  void naoDeveAceitarStatusNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPagamento(codigoValido, null, dataHoraValida);
    });
    assertEquals(StatusPagamentoExceptions.STATUS_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarCodigoMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPagamento(0L, statusValido, dataHoraValida);
    });
    assertEquals(StatusPagamentoExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataHoraNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPagamento(codigoValido, statusValido, null);
    });
    assertEquals(StatusPagamentoExceptions.DATA_HORA_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataMenorQue01jan2020() {
    var dataHoraInvalida = LocalDateTime.of(2019, 12, 31, 0, 0);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPagamento(codigoValido, statusValido, dataHoraInvalida);
    });
    assertEquals(StatusPagamentoExceptions.DATA_HORA_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataMaiorQueDataAtual() {
    var tomorrow = LocalDateTime.now().plusDays(1);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPagamento(codigoValido, statusValido, tomorrow);
    });
    assertEquals(StatusPagamentoExceptions.DATA_HORA_MAX.getMensagem(), exception.getMessage());
  }

}
