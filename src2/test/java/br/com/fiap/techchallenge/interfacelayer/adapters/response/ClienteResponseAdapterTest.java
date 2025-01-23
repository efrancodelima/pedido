package br.com.fiap.techchallenge.interfacelayer.adapters.response;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.ClienteResponseAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

/**
 * Classe de testes para ClienteResponseAdapter.
 */
class ClienteResponseAdapterTest {

  AutoCloseable closeable;

  @Mock
  Cliente clienteMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAdaptarClienteParaResponseEntityCliente() {

    var httpStatus = HttpStatus.OK;
    
    assertDoesNotThrow(() -> {
      var resposta = ClienteResponseAdapter.adaptar(clienteMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(clienteMock, resposta.getBody());
    });

  }
  
}
