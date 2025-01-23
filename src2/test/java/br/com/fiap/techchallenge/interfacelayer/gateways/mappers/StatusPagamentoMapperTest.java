package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPagamentoJpa;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para StatusPagamentoMapper.
 */
class StatusPagamentoMapperTest {

  @Test
  void deveMapearStatusPagamentoJpaParaStatusPagamento() {

    var statusPagJpa = new StatusPagamentoJpa(1L,
        StatusPagamentoEnum.APROVADO, LocalDateTime.now());

    assertDoesNotThrow(() -> {
      var statusPagamento = StatusPagamentoMapper.getStatusPagamento(statusPagJpa);

      assertEquals(statusPagJpa.getCodigo(), statusPagamento.getCodigo());
      assertEquals(statusPagJpa.getStatus(), statusPagamento.getStatus());
    });
  }
  
}
