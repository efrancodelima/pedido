package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case AtualizarStatusPagamento.
 */
class AtualizarStatusPagamentoTest {

  AutoCloseable closeable;

  @Mock
  InPedidoGateway gatewayMock;

  @Mock
  StatusPagamento statusPagamentoMock;

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
  void deveAtualizarStatusPagamentoComSucesso() throws BusinessRuleException {

    when(gatewayMock.buscarPedidoPeloCodigoPagamento(Mockito.anyLong())).thenReturn(pedidoMock);
    doNothing().when(pedidoMock).setStatusPagamento(Mockito.any());
    doNothing().when(gatewayMock).atualizarPedido(Mockito.any());
    
    assertDoesNotThrow(() -> {
      AtualizarStatusPagamento.atualizar(gatewayMock, statusPagamentoMock);
    });
  }

  @Test
  void deveLancarExcecaoSeStatusPagamentoForNulo() {

    var exception = assertThrows(ApplicationException.class, () -> {
      AtualizarStatusPagamento.atualizar(gatewayMock, null);
    });

    assertEquals(
        EnumApplicationExceptions.PAGAMENTO_STATUS_NULO.getMensagem(),
        exception.getMessage());
  }

  @Test
  void deveLancarExcecaoSeGatewayLancarExcecao() throws BusinessRuleException {
    
    var excecaoDoGateway = new BusinessRuleException("");
    
    when(gatewayMock.buscarPedidoPeloCodigoPagamento(Mockito.anyLong()))
        .thenThrow(excecaoDoGateway);
    
    var excecaoDoUseCase = assertThrows(BusinessRuleException.class, () -> {
      AtualizarStatusPagamento.atualizar(gatewayMock, statusPagamentoMock);
    });

    assertEquals(excecaoDoGateway.getMessage(), excecaoDoUseCase.getMessage());
  }

  @Test
  void deveLancarExcecaoSeGatewayRetornarNulo() throws BusinessRuleException {

    when(gatewayMock.buscarPedidoPeloCodigoPagamento(Mockito.anyLong())).thenReturn(null);

    var excecaoDoUseCase = assertThrows(ResourceNotFoundException.class, () -> {
      AtualizarStatusPagamento.atualizar(gatewayMock, statusPagamentoMock);
    });

    assertEquals(
        EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO.getMensagem(),
        excecaoDoUseCase.getMessage());
  }
}
