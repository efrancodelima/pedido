package br.com.fiap.soat.controller.produto.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.service.provider.produto.ListarProdutosPorCategoriaService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ListarProdutosPorCategoriaImplTest {

  AutoCloseable closeable;

  @Mock
  ListarProdutosPorCategoriaService serviceMock;

  @InjectMocks
  ListarProdutosPorCategoriaImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveListarProdutosComSucesso() throws Exception {

    // Arrange
    var listaProdutosJpa = getListaProdutosJpa();
    when(serviceMock.execute(anyString())).thenReturn(listaProdutosJpa);

    // Act
    var response = controller.listarProdutosPorCategoria("SOBREMESA");

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(listaProdutosJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PROD_CAT_INV);
    when(serviceMock.execute(anyString())).thenThrow(excecao);

    // Act
    var response = controller.listarProdutosPorCategoria("CEREAL");

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  private List<ProdutoJpa> getListaProdutosJpa() {
    var lista = new ArrayList<ProdutoJpa>();
    lista.add(new ProdutoJpa());
    return lista;
  }
}
