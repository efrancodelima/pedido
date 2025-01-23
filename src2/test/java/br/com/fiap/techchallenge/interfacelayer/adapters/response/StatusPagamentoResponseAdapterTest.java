package br.com.fiap.techchallenge.interfacelayer.adapters.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.StatusPagamentoResponseAdapter;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

/**
 * Classe de testes para StatusPagamentoResponseAdapter.
 */
class StatusPagamentoResponseAdapterTest {

  AutoCloseable closeable;

  @Mock
  Pedido pedidoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAdaptarPedidoParaResponseEntityStatusPagamentoDto() throws BusinessRuleException {

    // Arrange
    var statusPagamento = new StatusPagamento(1L,
        StatusPagamentoEnum.APROVADO, LocalDateTime.now());

    doReturn(statusPagamento).when(pedidoMock).getStatusPagamento();

    var httpStatus = HttpStatus.OK;

    // Act and assert
    assertDoesNotThrow(() -> {
      var resposta = StatusPagamentoResponseAdapter.adaptar(pedidoMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(pedidoMock.getStatusPagamento().getStatus(), resposta.getBody().getStatus());
    });
  }

}
