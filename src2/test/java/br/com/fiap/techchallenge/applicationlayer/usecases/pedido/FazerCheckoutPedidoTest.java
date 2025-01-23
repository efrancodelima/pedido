package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case FazerCheckoutPedido.
 */
class FazerCheckoutPedidoTest {

  AutoCloseable closeable;

  @Mock
  InPedidoGateway gatewayMock;

  @Mock
  Cliente clienteMock;

  @Mock
  Pedido pedidoMock;

  @Mock
  ItemPedido itemPedidoMock;

  @Mock
  StatusPedido statusPedidoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveFazerCheckoutComSucesso() throws BusinessRuleException {

    Pedido pedidoGravado = getPedido();
    doReturn(pedidoGravado).when(gatewayMock).gravarPedido(Mockito.any());
    
    assertDoesNotThrow(() -> {
      FazerCheckoutPedido.fazerCheckout(gatewayMock, pedidoMock);
    });
  }

  // Métodos auxiliares dos testes
  Pedido getPedido() throws BusinessRuleException {
    List<ItemPedido> listaItens = new ArrayList<>();
    listaItens.add(itemPedidoMock);

    var dataHora = LocalDateTime.now();

    var statusPagamento = new StatusPagamento(1L,
        StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, dataHora);
    
    return new Pedido(1L, clienteMock, listaItens, dataHora,
        statusPagamento, statusPedidoMock);
  }

}
