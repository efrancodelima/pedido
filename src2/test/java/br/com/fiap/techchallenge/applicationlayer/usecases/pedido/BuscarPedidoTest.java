package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case BuscarPedido.
 */
class BuscarPedidoTest {

  AutoCloseable closeable;

  @Mock
  InPedidoGateway gatewayMock;

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
  void deveBuscarPedidoComSucesso() throws BusinessRuleException {

    doReturn(pedidoMock).when(gatewayMock).buscarPedido(Mockito.anyLong());
    
    assertDoesNotThrow(() -> {
      BuscarPedido.buscar(gatewayMock, 1L);
    });
  }

}
