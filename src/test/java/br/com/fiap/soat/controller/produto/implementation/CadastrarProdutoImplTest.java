package br.com.fiap.soat.controller.produto.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.service.provider.produto.CadastrarProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class CadastrarProdutoImplTest {

  AutoCloseable closeable;

  @Mock
  CadastrarProdutoService serviceMock;

  @InjectMocks
  CadastrarProdutoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCadastrarProdutoComSucesso() throws Exception {

    // Arrange
    var produtoJpa = new ProdutoJpa();
    when(serviceMock.execute(any())).thenReturn(produtoJpa);

    // Act
    var response = controller.cadastrarProduto(new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    assertEquals(produtoJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PROD_CAT_INV);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.cadastrarProduto(new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusUnprocessableEntity() throws Exception {

    // Arrange
    var excecao = new BusinessRulesException(BusinessRulesMessage.PROD_NOME_MIN);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.cadastrarProduto(new ProdutoDto());

    // Assert
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }
}
