package br.com.fiap.techchallenge.interfacelayer.adapters.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.ProdutoResponseAdapter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

/**
 * Classe de testes para ProdutoResponseAdapter.
 */
class ProdutoResponseAdapterTest {

  AutoCloseable closeable;

  @Mock
  Produto produtoMock;

  @Mock
  List<Produto> listaProdutosMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAdaptarProdutoParaResponseEntityProduto() {

    var httpStatus = HttpStatus.OK;

    assertDoesNotThrow(() -> {
      var resposta = ProdutoResponseAdapter.adaptar(produtoMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(produtoMock, resposta.getBody());
    });
  }

  @Test
  void deveAdaptarListProdutoParaResponseEntityListProduto() {

    var httpStatus = HttpStatus.OK;

    assertDoesNotThrow(() -> {
      var resposta = ProdutoResponseAdapter.adaptar(listaProdutosMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(listaProdutosMock, resposta.getBody());
    });
  }
    
}
