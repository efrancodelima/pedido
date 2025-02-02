package br.com.fiap.soat.validator.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.validator.NumberValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

  @Test
  void deveValidarCodigoComSucesso() {
    var codigos = getListaCodigos();

    assertDoesNotThrow(() -> {
      NumberValidator.validar(codigos, BadRequestMessage.PROD_LIST_COD_NULL,
          BadRequestMessage.PROD_COD_NULL, BadRequestMessage.PROD_COD_MIN);
    });
  }

  @Test
  void deveLancarExcecaoParaListaNula() {

    var excecao = assertThrows(BadRequestException.class, () -> {
      NumberValidator.validar(null, BadRequestMessage.PROD_LIST_COD_NULL,
          null, null);
    });

    assertEquals(BadRequestMessage.PROD_LIST_COD_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaListaVazia() {

    var excecao = assertThrows(BadRequestException.class, () -> {
      NumberValidator.validar(new ArrayList<Long>(), BadRequestMessage.PROD_LIST_COD_NULL,
          null, null);
    });

    assertEquals(BadRequestMessage.PROD_LIST_COD_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void naoDeveLancarExcecaoParaNumeroNulo() {
    assertDoesNotThrow(() -> {
      NumberValidator.validar((Long) null, null, BadRequestMessage.CLI_COD_MIN);
    });
  }

  @Test
  void naoDeveLancarExcecaoParaNumeroNegativo() {
    assertDoesNotThrow(() -> {
      NumberValidator.validar(-1L, BadRequestMessage.PROD_COD_NULL, null);
    });
  }

  private List<Long> getListaCodigos() {
    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }
  
}
