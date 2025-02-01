package br.com.fiap.soat.controller.cliente.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.service.provider.cliente.CadastrarClienteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class CadastrarClienteImplTest {

  AutoCloseable closeable;

  @Mock
  CadastrarClienteService serviceMock;

  @InjectMocks
  CadastrarClienteImpl controller;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveCadastrarClienteComSucesso() throws Exception {

    // Arrange
    var clienteJpa = new ClienteJpa();
    when(serviceMock.execute(any())).thenReturn(clienteJpa);

    // Act
    var response = controller.cadastrarCliente(new ClienteDto());

    // Assert
    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    assertEquals(clienteJpa, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.CLI_EMAIL_INV);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.cadastrarCliente(new ClienteDto());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(BadRequestMessage.CLI_EMAIL_INV.getMessage(), response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusUnprocessableEntity() throws Exception {

    // Arrange
    var excecao = new BusinessRulesException(BusinessRulesMessage.CLIENTE_JA_CADASTRADO);
    when(serviceMock.execute(any())).thenThrow(excecao);

    // Act
    var response = controller.cadastrarCliente(new ClienteDto());

    // Assert
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(BusinessRulesMessage.CLIENTE_JA_CADASTRADO.getMessage(), 
        response.getBody().getErrorMsg());
  }
}
