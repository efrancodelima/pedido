package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.StatusPedidoExceptions;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o value object StatusPedido.
 */
class StatusPedidoTest {

  private final StatusPedidoEnum statusValido = StatusPedidoEnum.EM_PREPARACAO;
  private final LocalDateTime dataHoraValida = LocalDateTime.now();
  
  @Test
  void deveCriarStatusComSucesso() {
    assertDoesNotThrow(() -> {
      new StatusPedido(statusValido, dataHoraValida);
    });
  }
  
  @Test
  void deveRetornarOsAtributosDoStatus() throws BusinessRuleException {
    var status = new StatusPedido(statusValido, dataHoraValida);
    
    assertEquals(statusValido, status.getStatus());
    assertEquals(dataHoraValida, status.getDataHora());
  }

  @Test
  void naoDeveAceitarStatusNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPedido(null, dataHoraValida);
    });

    assertEquals(StatusPedidoExceptions.STATUS_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataHoraNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPedido(statusValido, null);
    });

    assertEquals(StatusPedidoExceptions.DATA_HORA_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataMenorQue01jan2020() {
    var dataHoraInvalida = LocalDateTime.of(2019, 12, 31, 0, 0);
    
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPedido(statusValido, dataHoraInvalida);
    });

    assertEquals(StatusPedidoExceptions.DATA_HORA_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAceitarDataMaiorQueDataAtual() {
    var tomorrow = LocalDateTime.now().plusDays(1);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new StatusPedido(statusValido, tomorrow);
    });

    assertEquals(StatusPedidoExceptions.DATA_HORA_MAX.getMensagem(), exception.getMessage());
  }

}
