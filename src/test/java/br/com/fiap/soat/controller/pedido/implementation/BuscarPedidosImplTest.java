package br.com.fiap.soat.controller.pedido.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.service.provider.pedido.BuscarPedidosService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class BuscarPedidosImplTest {

  AutoCloseable closeable;

  @Mock
  BuscarPedidosService serviceMock;

  @InjectMocks
  BuscarPedidosImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveBuscarUmPedidoComSucesso() throws Exception {

    // Arrange
    var listaPedidos = getListaPedidosJpa();
    when(serviceMock.execute(any())).thenReturn(listaPedidos);

    // Act
    var response = controller.buscarPedidos(getListaNumero());

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(listaPedidos, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.PED_COD_NULL);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.buscarPedidos(getListaNumero());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(BadRequestMessage.PED_COD_NULL.getMessage(), response.getBody().getErrorMsg());
  }
  
  private List<Long> getListaNumero() {
    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }

  private List<PedidoJpa> getListaPedidosJpa() {
    
    var lista = new ArrayList<PedidoJpa>();
    lista.add(new PedidoJpa());
    return lista;
  }
}
