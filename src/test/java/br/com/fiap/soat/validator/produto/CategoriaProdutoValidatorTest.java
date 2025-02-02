package br.com.fiap.soat.validator.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CategoriaProdutoValidatorTest {

  @Test
  void deveValidarCategoriaComSucesso() {
    assertDoesNotThrow(() -> {
      CategoriaProdutoValidator.validar("lanche");
      CategoriaProdutoValidator.validar("Acompanhamento");
      CategoriaProdutoValidator.validar("BEBIDA");
      CategoriaProdutoValidator.validar("sobreMESA");
    });
  }

  @Test
  void deveLancarExcecaoParaCategoriaInvalida() {
    var excecao = assertThrows(BadRequestException.class, () -> {
      CategoriaProdutoValidator.validar("Petisco");
    });
    assertEquals(BadRequestMessage.PROD_CAT_INV.getMessage(), excecao.getMessage());
  }

  @ParameterizedTest(name = "{index} - Categoria: {0}")
  @MethodSource("categoriaInvalidaProvider")
  void deveLancarExcecaoParaStringNulaOuVazia(String categoriaStr) {
    var excecao = assertThrows(BadRequestException.class, () -> {
      CategoriaProdutoValidator.validar(categoriaStr);
    });
    assertEquals(BadRequestMessage.PROD_CAT_NULL.getMessage(), excecao.getMessage());
  }

  static Stream<Arguments> categoriaInvalidaProvider() {
    return Stream.of(
        Arguments.of(null, "Categoria nula."),
        Arguments.of("  ", "Categoria vazia.")
    );
  }
}
