package br.com.fiap.techchallenge.interfacelayer.adapters.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ProdutoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ProdutoRequestAdapter.
 */
class ProdutoRequestAdapterTest {

  @Test
  void deveAdaptarProdutoDtoParaProduto() {

    var produtoDto = new ProdutoDto("Nome do produto", null,
        BigDecimal.valueOf(22), "BEBIDA");

    assertDoesNotThrow(() -> {
      
      var produto = ProdutoRequestAdapter.adaptar(produtoDto);

      assertEquals(produtoDto.nome, produto.getNome());
      assertEquals(produtoDto.descricao, produto.getDescricao());
      assertEquals(produtoDto.preco, produto.getPreco());
      assertEquals(produtoDto.categoria, produto.getCategoria().toString());

    });
      
  }

  @Test
  void deveAdaptarProdutoDtoParaProdutoComCodigo() {

    var codigoProduto = 55L;
    var produtoDto = new ProdutoDto("Nome do produto", null,
        BigDecimal.valueOf(22), "BEBIDA");

    assertDoesNotThrow(() -> {
      
      var produto = ProdutoRequestAdapter.adaptar(codigoProduto, produtoDto);

      assertEquals((Long) codigoProduto, produto.getCodigo());
      assertEquals(produtoDto.nome, produto.getNome());
      assertEquals(produtoDto.descricao, produto.getDescricao());
      assertEquals(produtoDto.preco, produto.getPreco());
      assertEquals(produtoDto.categoria, produto.getCategoria().toString());

    });
      
  }
  
}
