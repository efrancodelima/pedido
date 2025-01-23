package br.com.fiap.techchallenge.businesslayer.entities.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ClienteExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para a entidade de negócio Cliente.
 */
class ClienteTest {

  AutoCloseable closeable;
  
  @Mock
  private Cpf cpfValido;

  private final Long codigoValido = 1L;
  private final String nomeValido = "Nome do cliente";
  private final String emailValido = "email@email.com";

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConstruirUmClienteComSucesso() {
    assertDoesNotThrow(() -> {
      new Cliente(codigoValido, cpfValido, nomeValido, emailValido);
    });
  }

  @Test
  void deveRetornarOsAtributosDoCliente() {
    assertDoesNotThrow(() -> {

      Cliente cliente = new Cliente(codigoValido, cpfValido, nomeValido, emailValido);

      assertEquals(cliente.getCodigo(), codigoValido);
      assertEquals(cliente.getCpf(), cpfValido);
      assertEquals(cliente.getNome(), nomeValido);
      assertEquals(cliente.getEmail(), emailValido);

    });
  }

  @Test
  void codigoClienteNaoDeveSerMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(0L, cpfValido, nomeValido, emailValido);
    });

    assertEquals(ClienteExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());

    exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(-1L, cpfValido, nomeValido, emailValido);
    });
    
    assertEquals(ClienteExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void codigoClienteDeveAceitarNulo() {
    assertDoesNotThrow(() -> {
      new Cliente(null, cpfValido, nomeValido, emailValido);
    });
  }

  @Test
  void cpfClienteNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, null, nomeValido, emailValido);
    });
    assertEquals(ClienteExceptions.CPF_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClienteNaoPodeSerStringVazia() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "", emailValido);
    });
    assertEquals(ClienteExceptions.NOME_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClienteNaoDeveTerMaisDe50Caracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "A".repeat(51), emailValido);
    });
    assertEquals(ClienteExceptions.NOME_MAX_CHAR.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClientePrecisaConterPeloMenosUmaPalavraComPeloMenos3Letras() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "A B C D E", emailValido);
    });
    assertEquals(ClienteExceptions.NOME_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void emailNaoPodeSerStringVazia() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, nomeValido, "");
    });
    assertEquals(ClienteExceptions.EMAIL_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void emailNaoPodeSerInvalido() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, nomeValido, "sem_email");
    });
    assertEquals(ClienteExceptions.EMAIL_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void emailNaoPodeTerMaisQue40Caracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, nomeValido, "abcd".repeat(10).concat("@gmail.com"));
    });
    assertEquals(ClienteExceptions.EMAIL_MAX_CHAR.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeOuEmailUmDosDoisPodeSerNulo() {
    assertDoesNotThrow(() -> {
      new Cliente(codigoValido, cpfValido, null, emailValido);
    });

    assertDoesNotThrow(() -> {
      new Cliente(codigoValido, cpfValido, nomeValido, null);
    });
  }

  @Test
  void nomeEmailNaoPodemSerAmbosNulos() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, null, null);
    });
    assertEquals(ClienteExceptions.NOME_EMAIL_NULOS.getMensagem(), exception.getMessage());
  }

}
