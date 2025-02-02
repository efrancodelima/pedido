package br.com.fiap.soat.controller.produto.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.provider.produto.RemoverProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class RemoverProdutoImplTest {

  AutoCloseable closeable;

  @Mock
  RemoverProdutoService serviceMock;

  @InjectMocks
  RemoverProdutoImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveRemoverProdutoComSucesso() throws Exception {

    // Arrange
    doNothing().when(serviceMock).execute(anyLong());

    // Act
    var response = controller.removerProduto(1L);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PROD_COD_MIN);
    doThrow(excecao).when(serviceMock).execute(anyLong());

    // Act
    var response = controller.removerProduto(1L);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusNotFound() throws Exception {

    // Arrange
    var excecao = new NotFoundException(NotFoundMessage.COD_PRODUTO);
    doThrow(excecao).when(serviceMock).execute(anyLong());

    // Act
    var response = controller.removerProduto(1L);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }
}
