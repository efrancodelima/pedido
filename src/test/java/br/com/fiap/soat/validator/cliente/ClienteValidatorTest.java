package br.com.fiap.soat.validator.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import org.junit.jupiter.api.Test;

class ClienteValidatorTest {

  @Test
  void deveValidarUmClienteDtoComSucesso() {
    var cliente = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");

    assertDoesNotThrow(() -> {
      ClienteValidator.validar(cliente);
    });
  }

  @Test
  void deveLancarExcecaoSeClienteForNulo() {
    var excecao = assertThrows(BadRequestException.class, () -> {
      ClienteValidator.validar(null);
    });

    assertEquals(BadRequestMessage.CLI_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoSeNomeEmailForemNulos() {
    var cliente = new ClienteDto(11122233396L, null, null);

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ClienteValidator.validar(cliente);
    });

    assertEquals(BusinessRulesMessage.CLI_NOME_EMAIL_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void deveAceitarNomeNuloQuandoEmailNaoNulo() {
    var cliente = new ClienteDto(11122233396L, null, "email@email.com");

    assertDoesNotThrow(() -> {
      ClienteValidator.validar(cliente);
    });
  }

  @Test
  void deveAceitarEmailNuloQuandoNomeNaoNulo() {
    var cliente = new ClienteDto(11122233396L, "Nome do cliente", null);

    assertDoesNotThrow(() -> {
      ClienteValidator.validar(cliente);
    });
  }

  @Test
  void deveLancarExcecaoParaNomeComMaisDe50Chars() {
    var cliente = new ClienteDto(11122233396L, "Roberto ".repeat(10), "email@email.com");

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ClienteValidator.validar(cliente);
    });

    assertEquals(BusinessRulesMessage.CLI_NOME_MAX.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaNomeInvalido() {
    var cliente = new ClienteDto(11122233396L, "ab c d e ", "email@email.com");

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ClienteValidator.validar(cliente);
    });

    assertEquals(BusinessRulesMessage.CLI_NOME_INV.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaEmailInvalido() {
    var cliente = new ClienteDto(11122233396L, "Nome do cliente", "email.email.com");

    var excecao = assertThrows(BadRequestException.class, () -> {
      ClienteValidator.validar(cliente);
    });

    assertEquals(BadRequestMessage.CLI_EMAIL_INV.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaEmailComMaisDe40Chars() {
    var cliente = new ClienteDto(11122233396L, "Nome do cliente",
        "email".repeat(10) + "@email.com");

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ClienteValidator.validar(cliente);
    });

    assertEquals(BusinessRulesMessage.CLI_EMAIL_MAX.getMessage(), excecao.getMessage());
  }
}
