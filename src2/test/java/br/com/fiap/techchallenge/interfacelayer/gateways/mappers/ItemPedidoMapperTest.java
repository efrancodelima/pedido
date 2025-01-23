package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ItemPedidoMapper.
 */
class ItemPedidoMapperTest {
  
  @Test
  void deveMapearItemPedidoParaItemPedidoJpa() throws BusinessRuleException {
    
    var itemPedido = instanciarItemPedido();

    assertDoesNotThrow(() -> {
      var itemPedidoJpa = ItemPedidoMapper.getItemPedidoJpa(itemPedido);

      assertEquals(itemPedido.getProduto().getNome(), itemPedidoJpa.getProdutoJpa().getNome());
      assertEquals(itemPedido.getQuantidade(), (int) itemPedidoJpa.getQuantidade());
      assertEquals(itemPedido.getValorItem(),
          itemPedidoJpa.getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(itemPedidoJpa.getQuantidade())));
    });
  }

  @Test
  void deveMapearItemPedidoJpaParaItemPedido() {

    var itemPedidoJpa = instanciarItemPedidoJpa();

    assertDoesNotThrow(() -> {
      var itemPedido = ItemPedidoMapper.getItemPedido(itemPedidoJpa);
  
      assertEquals(itemPedido.getProduto().getNome(), itemPedidoJpa.getProdutoJpa().getNome());
      assertEquals(itemPedido.getQuantidade(), (int) itemPedidoJpa.getQuantidade());
      assertEquals(itemPedido.getValorItem(),
          itemPedidoJpa.getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(itemPedidoJpa.getQuantidade())));
    });
  }

  @Test
  void deveMapearListItemPedidoParaListItemPedidoJpa() throws BusinessRuleException {

    var listaItensPedido = new ArrayList<ItemPedido>();
    listaItensPedido.add(instanciarItemPedido());

    assertDoesNotThrow(() -> {

      var listaItensPedidoJpa = ItemPedidoMapper.getListItemPedidoJpa(listaItensPedido);

      assertEquals(listaItensPedido.size(), listaItensPedidoJpa.size());
      
      assertEquals(
          listaItensPedido.get(0).getProduto().getNome(),
          listaItensPedidoJpa.get(0).getProdutoJpa().getNome());
      
      assertEquals(
          listaItensPedido.get(0).getQuantidade(),
          (int) listaItensPedidoJpa.get(0).getQuantidade());
      
      assertEquals(
          listaItensPedido.get(0).getValorItem(),
          listaItensPedidoJpa.get(0).getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(listaItensPedidoJpa.get(0).getQuantidade())));
    });
  }

  @Test
  void deveMapearListItemPedidoJpaParaListItemPedido() {

    var listaItensPedidoJpa = new ArrayList<ItemPedidoJpa>();
    listaItensPedidoJpa.add(instanciarItemPedidoJpa());

    assertDoesNotThrow(() -> {
      
      var listaItensPedido = ItemPedidoMapper.getListItemPedido(listaItensPedidoJpa);
  
      assertEquals(listaItensPedido.size(), listaItensPedidoJpa.size());
      
      assertEquals(
          listaItensPedido.get(0).getProduto().getNome(),
          listaItensPedidoJpa.get(0).getProdutoJpa().getNome());
      
      assertEquals(
          listaItensPedido.get(0).getQuantidade(),
          (int) listaItensPedidoJpa.get(0).getQuantidade());
      
      assertEquals(
          listaItensPedido.get(0).getValorItem(),
          listaItensPedidoJpa.get(0).getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(listaItensPedidoJpa.get(0).getQuantidade())));
    });
  }

  // Métodos auxiliares dos testes
  private ItemPedido instanciarItemPedido() throws BusinessRuleException {
    
    var produto = new Produto(1L,
        "Nome do produto", null, BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);
    
    return new ItemPedido(produto, 2);
  }

  private ItemPedidoJpa instanciarItemPedidoJpa() {
    
    var produtoJpa = new ProdutoJpa(1L, "Nome do produto",
        null, BigDecimal.valueOf(22), CategoriaProduto.BEBIDA);

    return new ItemPedidoJpa(produtoJpa, 2);
  }
  
}