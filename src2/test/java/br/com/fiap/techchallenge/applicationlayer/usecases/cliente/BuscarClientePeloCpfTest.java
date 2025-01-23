package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case BuscarClientePeloCpf.
 */
class BuscarClientePeloCpfTest {

  AutoCloseable closeable;

  @Mock
  InClienteGateway gatewayMock;

  @Mock
  Cliente clienteMock;

  @Mock
  Cpf cpfMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception  {
    closeable.close();
  }
    
  @Test
  void deveBuscarClienteComSucesso() {
    assertDoesNotThrow(() -> {

      when(gatewayMock.buscarClientePorCpf(Mockito.any())).thenReturn(clienteMock);

      var cliente = BuscarClientePeloCpf.buscar(gatewayMock, cpfMock);
      
      assertEquals(clienteMock, cliente);
    });
  }

  @Test
  void deveLancarExcecaoQuandoCpfForNulo() {

    var exception = assertThrows(ApplicationException.class, () -> {
      BuscarClientePeloCpf.buscar(gatewayMock, null);
    });

    assertEquals(EnumApplicationExceptions.CPF_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoGatewayRetornarNulo() {

    assertDoesNotThrow(() -> {
      when(gatewayMock.buscarClientePorCpf(Mockito.any())).thenReturn(null);
    });

    var exception = assertThrows(ResourceNotFoundException.class, () -> {
      BuscarClientePeloCpf.buscar(gatewayMock, cpfMock);
    });

    assertEquals(EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO.getMensagem(),
        exception.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoGatewayLancarExcecao() {

    var excecaoDoGateway = new BusinessRuleException("Mensagem da exceção.");

    assertDoesNotThrow(() -> {
      when(gatewayMock.buscarClientePorCpf(Mockito.any())).thenThrow(excecaoDoGateway);
    });

    var excecaoDoUseCase = assertThrows(BusinessRuleException.class, () -> {
      BuscarClientePeloCpf.buscar(gatewayMock, cpfMock);
    });

    assertEquals(excecaoDoGateway.getMessage(), excecaoDoUseCase.getMessage());
  }

}
