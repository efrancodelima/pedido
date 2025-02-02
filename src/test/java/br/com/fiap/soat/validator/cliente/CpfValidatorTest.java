package br.com.fiap.soat.validator.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CpfValidatorTest {

  @Test
  void deveValidarUmNumeroCpfComSucesso() {
    assertDoesNotThrow(() -> {
      CpfValidator.validar(11122233396L);
    });
  }
  
  @Test
  void deveLancarExcecaoSeCpfForNulo() {
    var excecao = assertThrows(BadRequestException.class, () -> {
      CpfValidator.validar(null);
    });
    assertEquals(BadRequestMessage.CLI_CPF_NULL.getMessage(), excecao.getMessage());
  }

  @DisplayName("Deve lançar exceção para CPFs inválidos")
  @ParameterizedTest(name = "{index} - CPF: {0}")
  @MethodSource("cpfInvalidoProvider")
  void deveLancarExcecaoParaCpfInvalido(long cpfInvalido, String descricao) {
    var excecao = assertThrows(BadRequestException.class, () -> {
      CpfValidator.validar(cpfInvalido);
    });
    assertEquals(BadRequestMessage.CLI_CPF_INV.getMessage(), excecao.getMessage());
  }

  static Stream<Arguments> cpfInvalidoProvider() {
    return Stream.of(
        Arguments.of(99L, "Número de CPF com menos de três dígitos."),
        Arguments.of(111222333444L, "Número de CPF com mais de onze dígitos."),
        Arguments.of(11122233300L, "Dígito verificador inválido.") // o dígito correto era 96
    );
  }
}
