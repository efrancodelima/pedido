package br.com.fiap.soat.service.provider.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BuscarClienteServiceTest {

  AutoCloseable closeable;

  @Mock
  ClienteRepository repositoryMock;

  @InjectMocks
  BuscarClienteService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveBuscarClienteComSucesso() throws Exception {

    // Arrange
    var cliente = new ClienteJpa(1L, 11122233396L, "Nome do cliente", "email@email.com");
    doReturn(cliente).when(repositoryMock).findByCpf(anyLong());

    // Act
    var response = service.execute(11122233396L);

    // Assert
    assertEquals(cliente, response);
  }

  @Test
  void deveLancarExcecaoQuandoNaoEncontrarCliente() {

    // Arrange
    doReturn(null).when(repositoryMock).findByCpf(anyLong());

    // Act and assert
    var excecao = assertThrows(NotFoundException.class, () -> {
      service.execute(11122233396L);
    });

    assertEquals(NotFoundMessage.CPF_CLIENTE.getMessage(), excecao.getMessage());
  }
}
