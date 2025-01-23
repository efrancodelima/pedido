package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case CadastrarProduto.
 */
class CadastrarProdutoTest {

  AutoCloseable closeable;

  @Mock
  InProdutoGateway gatewayMock;

  @Mock
  Produto produtoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCadastrarProdutoComSucesso() throws BusinessRuleException {

    doReturn(produtoMock).when(gatewayMock).gravarProduto(Mockito.any());
    
    assertDoesNotThrow(() -> {
      CadastrarProduto.cadastrar(gatewayMock, produtoMock);
    });
  }

}
