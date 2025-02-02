package br.com.fiap.soat.controller.pedido.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.dto.service.response.RegistroProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.provider.pedido.FazerCheckoutService;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class FazerCheckoutImplTest {

  AutoCloseable closeable;

  @Mock
  FazerCheckoutService serviceMock;

  @InjectMocks
  FazerCheckoutImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveFazerCheckoutComSucesso() throws Exception {

    // Arrange
    var registro = new RegistroProducaoDto();
    when(serviceMock.execute(any())).thenReturn(registro);

    // Act
    var response = controller.fazerCheckout(getRequisicao());

    // Assert
    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    assertEquals(registro, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadGateway() throws Exception {

    // Arrange
    var excecao = new BadGatewayException("");
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.fazerCheckout(getRequisicao());

    // Assert
    assertEquals(HttpStatus.BAD_GATEWAY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PED_ITEM_QTDE_MIN);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.fazerCheckout(getRequisicao());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusNotFound() throws Exception {

    // Arrange
    var excecao = new NotFoundException(NotFoundMessage.COD_PRODUTO);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.fazerCheckout(getRequisicao());

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusUnprocessableEntity() throws Exception {

    // Arrange
    var excecao = new BusinessRulesException(BusinessRulesMessage.PED_ITEM_MIN);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.fazerCheckout(getRequisicao());

    // Assert
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  private PedidoDto getRequisicao() {

    var item = new ItemPedidoDto(1L, 1);
    
    var listaItens = new ArrayList<ItemPedidoDto>();
    listaItens.add(item);

    return new PedidoDto(1L, listaItens);
  }
}
