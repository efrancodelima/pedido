package br.com.fiap.techchallenge.businesslayer.entities.produto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ProdutoExceptions;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para a entidade de negócio Produto.
 */
class ProdutoTest {

  @Test
  void deveConstruirUmProdutoComSucesso() {
    assertDoesNotThrow(() -> {
      new Produto(1L, "Nome do produto",
        "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
  }
  
  @Test
  void deveRetornarOsAtributosDoProduto() {
    
    var codigo = Long.valueOf(1);
    var nome = "Nome do produto";
    var descricao = "Descrição do produto";
    var preco = BigDecimal.valueOf(50);
    var categoria = CategoriaProduto.LANCHE;

    assertDoesNotThrow(() -> {
      var produto = new Produto(codigo, nome, descricao, preco, categoria);

      assertEquals(codigo, produto.getCodigo());
      assertEquals(nome, produto.getNome());
      assertEquals(descricao, produto.getDescricao());
      assertEquals(preco, produto.getPreco());
      assertEquals(categoria, produto.getCategoria());
    });
  }

  @Test
  void devePermitirCodigoNulo() {
    assertDoesNotThrow(() -> {
      new Produto(null, "Nome do produto",
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
  }

  @Test
  void naoDevePermitirCodigoMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(0L, "Nome do produto",
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });

    assertEquals(ProdutoExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirNomeNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, null,
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
  
    assertEquals(ProdutoExceptions.NOME_VAZIO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirNomeStringVazia() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "",
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.NOME_VAZIO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirNomeComMenosDeCincoCaracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "abc",
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.NOME_MIN_CHAR.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirNomeComMaisDeVinteCaracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "abc".repeat(10),
      "Descrição do produto", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.NOME_MAX_CHAR.getMensagem(), exception.getMessage());
  }

  @Test
  void devePermitirDescricaoNula() {
    assertDoesNotThrow(() -> {
      new Produto(1L, "Nome do produto",
      null, BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
  }

  @Test
  void naoDevePermitirDescricaoComMenosDeVinteCaracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "abc", BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.DESCRICAO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirDescricaoComMaisDe150Caracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "abc".repeat(100), BigDecimal.valueOf(50), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.DESCRICAO_MAX.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirPrecoNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "descrição do produto", null, CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.PRECO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirPrecoIgualOuMenorQueZero() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "descrição do produto", BigDecimal.ZERO, CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.PRECO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirPrecoMaiorQue300Reais() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "descrição do produto", BigDecimal.valueOf(300.01), CategoriaProduto.LANCHE);
    });
    
    assertEquals(ProdutoExceptions.PRECO_MAX.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDevePermitirCategoriaNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Produto(1L, "Nome do produto",
      "descrição do produto", BigDecimal.valueOf(50), null);
    });
    
    assertEquals(ProdutoExceptions.CATEGORIA_NULA.getMensagem(), exception.getMessage());
  }

//   @Test
//   void xxxxx() {
//     fail();
//   }

//   @Test
//   void xxxxx() {
//     fail();
//   }

//   @Test
//   void xxxxx() {
//     fail();
//   }

//   @Test
//   void xxxxx() {
//     fail();
//   }
    
}
