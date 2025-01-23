package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.EnumExceptions;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o Enum StatusPedidoEnum.
 */
class StatusPedidoEnumTest {
  
  @Test
  void deveRetornarEnumComSucesso() {
    assertDoesNotThrow(() -> {
      var status = StatusPedidoEnum.fromString("recebiDO");
      assertEquals(StatusPedidoEnum.RECEBIDO, status);
    });
  }

  @Test
  void deveLancarExcecaoQuandoStatusNaoExistir() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      StatusPedidoEnum.fromString("um status qualquer");
    });
    assertEquals(EnumExceptions.STATUS_PEDIDO.getMensagem(), exception.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoStringForNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      StatusPedidoEnum.fromString(null);
    });
    assertEquals(EnumExceptions.STATUS_PEDIDO.getMensagem(), exception.getMessage());
  }
  
}
