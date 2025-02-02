package br.com.fiap.soat.controller.produto.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.provider.produto.EditarProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class EditarProdutoImplTest {

  AutoCloseable closeable;

  @Mock
  EditarProdutoService serviceMock;

  @InjectMocks
  EditarProdutoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveEditarProdutoComSucesso() throws Exception {

    // Arrange
    var produtoJpa = new ProdutoJpa();
    when(serviceMock.execute(anyLong(), any())).thenReturn(produtoJpa);

    // Act
    var response = controller.editarProduto(1L, new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(produtoJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PROD_CAT_INV);
    when(serviceMock.execute(anyLong(), any())).thenThrow(excecao);

    // Act
    var response = controller.editarProduto(1L, new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusNotFound() throws Exception {

    // Arrange
    var excecao = new NotFoundException(NotFoundMessage.COD_PRODUTO);
    when(serviceMock.execute(anyLong(), any())).thenThrow(excecao);

    // Act
    var response = controller.editarProduto(1L, new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusUnprocessableEntity() throws Exception {

    // Arrange
    var excecao = new BusinessRulesException(BusinessRulesMessage.PROD_NOME_MIN);
    when(serviceMock.execute(anyLong(), any())).thenThrow(excecao);

    // Act
    var response = controller.editarProduto(1L, new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }
}
