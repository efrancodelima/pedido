package br.com.fiap.techchallenge.interfacelayer.adapters.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ItemPedidoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ItemPedidoRequestAdapter.
 */
class ItemPedidoRequestAdapterTest {

  @Test
  void deveAdaptarListItemPedidoDtoParaListItemPedido() throws BusinessRuleException {

    // Arrange
    var listaItensDto = new ArrayList<ItemPedidoDto>();
    
    listaItensDto.add(new ItemPedidoDto(1L, 1));

    var listaProdutos = new ArrayList<Produto>();
    
    listaProdutos.add(
        new Produto(1L, "Nome do produto", null,
        BigDecimal.valueOf(22), CategoriaProduto.BEBIDA));

    // Act and assert
    assertDoesNotThrow(() -> {

      var listaItens = ItemPedidoRequestAdapter.adaptar(listaItensDto, listaProdutos);

      assertEquals(listaItensDto.size(), listaItens.size());
      
      assertEquals(
          listaItensDto.get(0).codigoProduto,
          listaItens.get(0).getProduto().getCodigo());
    });

  }
  
}
