package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
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
 * Classe de testes para o use case RemoverProduto.
 */
class RemoverProdutoTest {

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
  void deveRemoverProdutoComSucesso() throws BusinessRuleException {

    doReturn(true).when(gatewayMock).produtoExiste(Mockito.anyLong());
    doNothing().when(gatewayMock).removerProduto(Mockito.anyLong());
    
    assertDoesNotThrow(() -> {
      RemoverProduto.remover(gatewayMock, 1L);
    });
  }

  @Test
  void deveLancarExcecaoQuandoProdutoNaoExistir() throws BusinessRuleException {

    doReturn(false).when(gatewayMock).produtoExiste(Mockito.anyLong());
    
    var exception = assertThrows(ResourceNotFoundException.class, () -> {
      RemoverProduto.remover(gatewayMock, 1L);
    });

    assertEquals(
        EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem(), 
        exception.getMessage());
  }

}
