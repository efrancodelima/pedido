package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
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
 * Classe de testes para o use case CadastrarCliente.
 */
class CadastrarClienteTest {

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
  void tearDown() throws Exception {
    closeable.close();
  }
    
  @Test
  void deveCadastrarClienteComSucesso() {
    assertDoesNotThrow(() -> {

      when(gatewayMock.gravarCliente(Mockito.any())).thenReturn(clienteMock);

      var cliente = CadastrarCliente.cadastrar(gatewayMock, clienteMock);
    
      assertEquals(clienteMock, cliente);
    });
  }

  @Test
  void deveLancarExcecaoQuandoClienteForNulo() {

    var exception = assertThrows(ApplicationException.class, () -> {
      CadastrarCliente.cadastrar(gatewayMock, null);
    });

    assertEquals(EnumApplicationExceptions.CLIENTE_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void deveLancarExcecaoSeClienteJaExiste() {

    when(gatewayMock.clienteJaExiste(Mockito.any())).thenReturn(true);

    var exception = assertThrows(ApplicationException.class, () -> {
      CadastrarCliente.cadastrar(gatewayMock, clienteMock);
    });

    assertEquals(EnumApplicationExceptions.CLIENTE_JA_EXISTE.getMensagem(), exception.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoGatewayLancarexcecao() {

    var excecaoDoGateway = new BusinessRuleException("Mensagem da exceção.");

    when(gatewayMock.clienteJaExiste(Mockito.any())).thenReturn(false);
    
    assertDoesNotThrow(() -> {
      when(gatewayMock.gravarCliente(Mockito.any())).thenThrow(excecaoDoGateway);
    });

    var excecaoDoUseCase = assertThrows(BusinessRuleException.class, () -> {
      CadastrarCliente.cadastrar(gatewayMock, clienteMock);
    });

    assertEquals(excecaoDoGateway.getMessage(), excecaoDoUseCase.getMessage());
  }
  
}
