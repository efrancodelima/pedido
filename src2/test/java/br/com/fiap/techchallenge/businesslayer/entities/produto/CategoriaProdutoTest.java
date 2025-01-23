package br.com.fiap.techchallenge.businesslayer.entities.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.EnumExceptions;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o Enum CategoriaProduto.
 */
class CategoriaProdutoTest {

  @Test
  void deveRetornarEnumComSucesso() {
    assertDoesNotThrow(() -> {
      var resposta = CategoriaProduto.fromString("LanCHe");
      assertEquals(CategoriaProduto.LANCHE, resposta);
    });
  }

  @Test
  void deveLancarExcecaoQuandoCategoriaNaoExistir() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      CategoriaProduto.fromString("uma categoria qualquer");
    });
    assertEquals(EnumExceptions.CATEGORIA_PRODUTO.getMensagem(), exception.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoStringForNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      CategoriaProduto.fromString(null);
    });
    assertEquals(EnumExceptions.CATEGORIA_PRODUTO.getMensagem(), exception.getMessage());
  }
    
}
