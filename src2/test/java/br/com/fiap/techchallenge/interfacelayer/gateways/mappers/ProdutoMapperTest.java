package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ProdutoMapper.
 */
class ProdutoMapperTest {

  @Test
  void deveMapearProdutoParaProdutoJpa() throws BusinessRuleException {

    var produto = new Produto(1L,
        "Nome do produto", null, BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);

    var produtoJpa = ProdutoMapper.getProdutoJpa(produto);

    assertEquals(produto.getCodigo(), produtoJpa.getCodigo());
    assertEquals(produto.getNome(), produtoJpa.getNome());
    assertEquals(produto.getDescricao(), produtoJpa.getDescricao());
    assertEquals(produto.getCategoria(), produtoJpa.getCategoria());
    assertEquals(produto.getPreco(), produtoJpa.getPreco());
  }

  @Test
  void deveMapearProdutoJpaParaProduto() {

    var produtoJpa = new ProdutoJpa(1L, "Nome do produto", null,
        BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);
    
    assertDoesNotThrow(() -> {
      
      var produto = ProdutoMapper.getProduto(produtoJpa);

      assertEquals(produto.getCodigo(), produtoJpa.getCodigo());
      assertEquals(produto.getNome(), produtoJpa.getNome());
      assertEquals(produto.getDescricao(), produtoJpa.getDescricao());
      assertEquals(produto.getCategoria(), produtoJpa.getCategoria());
      assertEquals(produto.getPreco(), produtoJpa.getPreco());
    });
  }

  @Test
  void deveMapearListProdutoJpaParaListProduto() {

    var produtoJpa = new ProdutoJpa(1L, "Nome do produto", null,
        BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);

    var listaProdutosJpa = new ArrayList<ProdutoJpa>();
    listaProdutosJpa.add(produtoJpa);

    assertDoesNotThrow(() -> {

      var listaProdutos = ProdutoMapper.getListProduto(listaProdutosJpa);

      assertEquals(listaProdutosJpa.size(), listaProdutos.size());
      assertEquals(listaProdutosJpa.get(0).getCodigo(), listaProdutos.get(0).getCodigo());
      assertEquals(listaProdutosJpa.get(0).getNome(), listaProdutos.get(0).getNome());
      assertEquals(listaProdutosJpa.get(0).getDescricao(), listaProdutos.get(0).getDescricao());
      assertEquals(listaProdutosJpa.get(0).getCategoria(), listaProdutos.get(0).getCategoria());
      assertEquals(listaProdutosJpa.get(0).getPreco(), listaProdutos.get(0).getPreco());
    });
  }
  
}
