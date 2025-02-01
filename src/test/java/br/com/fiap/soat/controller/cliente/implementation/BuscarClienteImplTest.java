package br.com.fiap.soat.controller.cliente.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.service.provider.cliente.BuscarClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class BuscarClienteImplTest {

  AutoCloseable closeable;

  @Mock
  BuscarClienteService serviceMock;

  @InjectMocks
  BuscarClienteImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveBuscarUmClienteComSucesso() throws Exception {

    // Arrange
    var clienteJpa = new ClienteJpa();
    when(serviceMock.execute(any())).thenReturn(clienteJpa);

    // Act
    var response = controller.buscarClientePorCpf(11122233396L);

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(clienteJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.CLI_CPF_INV);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.buscarClientePorCpf(11122233396L);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(BadRequestMessage.CLI_CPF_INV.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusNotFound() throws Exception {

    // Arrange
    var excecao = new NotFoundException(NotFoundMessage.CPF_CLIENTE);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.buscarClientePorCpf(11122233396L);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(NotFoundMessage.CPF_CLIENTE.getMessage(), response.getBody().getErrorMsg());
  }
}
