package br.com.fiap.soat.controller.pedido.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.response.PedidoEmProducaoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.service.provider.pedido.ListarPedidosService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ListarPedidosImplTest {

  AutoCloseable closeable;

  @Mock
  ListarPedidosService serviceMock;

  @InjectMocks
  ListarPedidosImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveListarPedidosComSucesso() throws Exception {

    // Arrange
    var listaPedidos = getServiceResponse();
    when(serviceMock.execute()).thenReturn(listaPedidos);

    // Act
    var response = controller.listarPedidos();

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(listaPedidos, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadGateway() throws Exception {

    // Arrange
    var excecao = new BadGatewayException("");
    when(serviceMock.execute()).thenThrow(excecao);

    // Act
    var response = controller.listarPedidos();

    // Assert
    assertEquals(HttpStatus.BAD_GATEWAY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  private List<PedidoEmProducaoDto> getServiceResponse() {
    var lista = new ArrayList<PedidoEmProducaoDto>();
    lista.add(new PedidoEmProducaoDto());
    return lista;
  }
}
