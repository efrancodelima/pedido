package br.com.fiap.techchallenge.applicationlayer.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o service Validar.
 */
class ValidarTest {

  AutoCloseable closeable;

  @Mock
  Cliente clienteValido;

  @Mock
  Produto produtoValido;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void metodoNotNullDeveLancarExcecaoDeRegraDeAplicacao() {
    Cliente cliente = null;
    var msgApplication = EnumApplicationExceptions.CLIENTE_NULO;

    var exception = assertThrows(ApplicationException.class, () -> {
      Validar.notNull(cliente, msgApplication);
    });

    assertEquals(msgApplication.getMensagem(), exception.getMessage());
  }

  @Test
  void metodoNotNullNaoDeveLancarExcecaoDeRegraDeAplicacao() {
    Cliente cliente = clienteValido;
    var msgApplication = EnumApplicationExceptions.CLIENTE_NULO;

    assertDoesNotThrow(() -> {
      Validar.notNull(cliente, msgApplication);
    });
  }

  @Test
  void metodoNotNullDeveLancarExcecaoDeRcursoNaoEncontrado() {
    Cliente cliente = null;
    var msgNotFound = EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO;

    var exception = assertThrows(ResourceNotFoundException.class, () -> {
      Validar.notNull(cliente, msgNotFound);
    });

    assertEquals(msgNotFound.getMensagem(), exception.getMessage());
  }

  @Test
  void metodoNotNullNaoDeveLancarExcecaoDeRcursoNaoEncontrado() {
    Cliente cliente = clienteValido;
    var msgNotFound = EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO;

    assertDoesNotThrow(() -> {
      Validar.notNull(cliente, msgNotFound);
    });
  }

  @Test
  void metodoMaiorQueZeroDeveLancarExcecaoDeAplicacao() {
    Long numero = Long.valueOf(-5);
    var msgApplication = EnumApplicationExceptions.PRODUTO_CODIGO_MIN;

    var exception = assertThrows(ApplicationException.class, () -> {
      Validar.maiorQueZero(numero, msgApplication);
    });

    assertEquals(msgApplication.getMensagem(), exception.getMessage());
  }

  @Test
  void metodoMaiorQueZeroNaoDeveLancarExcecaoDeAplicacao() {
    Long numero = Long.valueOf(1);
    var msgApplication = EnumApplicationExceptions.PRODUTO_CODIGO_MIN;

    assertDoesNotThrow(() -> {
      Validar.maiorQueZero(numero, msgApplication);
    });
  }

  @Test
  void metodoListNotEmptyDeveLancarExcecaoDeRecursoNaoEncontrado() {
    List<Produto> listaProdutos = new ArrayList<>();
    var msgNotFound = EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA;

    var exception = assertThrows(ResourceNotFoundException.class, () -> {
      Validar.listNotEmpty(listaProdutos, msgNotFound);
    });

    assertEquals(msgNotFound.getMensagem(), exception.getMessage());
  }

  @Test
  void metodoListNotEmptyNaoDeveLancarExcecaoDeRecursoNaoEncontrado() {
    List<Produto> listaProdutos = new ArrayList<>();
    listaProdutos.add(produtoValido);
    
    var msgNotFound = EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA;

    assertDoesNotThrow(() -> {
      Validar.listNotEmpty(listaProdutos, msgNotFound);
    });
  }
  
}
