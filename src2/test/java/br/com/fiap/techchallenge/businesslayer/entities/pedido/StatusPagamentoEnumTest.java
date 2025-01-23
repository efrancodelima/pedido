package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.EnumExceptions;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o Enum StatusPagamentoEnum.
 */
class StatusPagamentoEnumTest {

  @Test
  void deveRetornarEnumComSucesso() {
    assertDoesNotThrow(() -> {
      var status = StatusPagamentoEnum.fromString("aprovaDO");
      assertEquals(StatusPagamentoEnum.APROVADO, status);
    });
  }

  @Test
  void deveLancarExcecaoQuandoStatusNaoExistir() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      StatusPagamentoEnum.fromString("um status qualquer");
    });
    assertEquals(EnumExceptions.STATUS_PAGAMENTO.getMensagem(), exception.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoStringForNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      StatusPagamentoEnum.fromString(null);
    });
    assertEquals(EnumExceptions.STATUS_PAGAMENTO.getMensagem(), exception.getMessage());
  }

}
