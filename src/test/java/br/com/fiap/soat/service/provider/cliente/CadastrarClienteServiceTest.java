package br.com.fiap.soat.service.provider.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CadastrarClienteServiceTest {

  AutoCloseable closeable;

  @Mock
  ClienteRepository repositoryMock;

  @InjectMocks
  CadastrarClienteService service;

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
    var clienteDto = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
    var clienteJpa = new ClienteJpa(1L, 11122233396L, "Nome do cliente", "email@email.com");
    
    doReturn(null).when(repositoryMock).findByCpf(anyLong());
    doReturn(clienteJpa).when(repositoryMock).save(any());

    // Act
    var response = service.execute(clienteDto);

    // Assert
    assertEquals(clienteJpa, response);
  }

  @Test
  void deveLancarExcecaoQuandoClienteJaEstiverCadastrado() {

    // Arrange
    var clienteDto = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
    
    doReturn(true).when(repositoryMock).existsByCpf(anyLong());

    // Act and assert
    var excecao = assertThrows(BusinessRulesException.class, () -> {
      service.execute(clienteDto);
    });

    assertEquals(BusinessRulesMessage.CLIENTE_JA_CADASTRADO.getMessage(), excecao.getMessage());
  }
}
