package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPedidoJpa;

/**
 * Classe de testes para StatusPedidoMapper.
 */
class StatusPedidoMapperTest {
  
  @Test
  void deveMapearStatusPedidoParaStatusPedidoJpa() throws BusinessRuleException {

    var statusPedido = new StatusPedido(StatusPedidoEnum.RECEBIDO, LocalDateTime.now());

    var statusPedidoJpa = StatusPedidoMapper.getStatusPedidoJpa(statusPedido);

    assertEquals(statusPedido.getStatus(), statusPedidoJpa.getStatus());
  }

  @Test
  void deveMapearStatusPedidoJpaParaStatusPedido() {

    var statusPedidoJpa = new StatusPedidoJpa(StatusPedidoEnum.RECEBIDO, LocalDateTime.now());

    assertDoesNotThrow(() -> {
      var statusPedido = StatusPedidoMapper.getStatusPedido(statusPedidoJpa);
      assertEquals(statusPedidoJpa.getStatus(), statusPedido.getStatus());
    });
  }
  
}
