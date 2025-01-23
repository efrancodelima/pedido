package br.com.fiap.techchallenge.interfacelayer.adapters.response;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.PedidoResponseAdapter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

/**
 * Classe de testes para PedidoResponseAdapter.
 */
class PedidoResponseAdapterTest {

  AutoCloseable closeable;

  @Mock
  Pedido pedidoMock;

  @Mock
  List<Pedido> listaPedidosMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAdaptarPedidoParaResponseEntityPedido() {

    var httpStatus = HttpStatus.OK;

    assertDoesNotThrow(() -> {
      var resposta = PedidoResponseAdapter.adaptar(pedidoMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(pedidoMock, resposta.getBody());
    });
  }

  @Test
  void deveAdaptarListPedidoParaResponseEntityListPedido() {

    var httpStatus = HttpStatus.OK;

    assertDoesNotThrow(() -> {
      var resposta = PedidoResponseAdapter.adaptar(listaPedidosMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(listaPedidosMock, resposta.getBody());
    });
  }
  
}
