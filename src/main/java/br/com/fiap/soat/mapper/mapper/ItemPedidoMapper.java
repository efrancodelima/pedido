package br.com.fiap.soat.mapper.mapper;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ItemPedidoMapper.
 */
public final class ItemPedidoMapper {

  private ItemPedidoMapper() {}

  /**
   * Mapeia um objeto do tipo ItemPedido para o tipo ItemPedidoJpa.
   *
   * @param item O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static ItemPedidoJpa getItemPedidoJpa(ItemPedido item) {
    ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(item.getProduto());
    return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
  }

  /**
   * Mapeia um objeto do tipo ItemPedidoJpa para o tipo ItemPedido.
   *
   * @param itemJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static ItemPedido getItemPedido(ItemPedidoJpa itemJpa)
      throws BusinessRuleException {
    
    Produto produto = ProdutoMapper.getProduto(itemJpa.getProdutoJpa());
    return new ItemPedido(produto, itemJpa.getQuantidade());
  }

  /**
   * Mapeia um objeto do tipo List-ItemPedido para o tipo List-ItemPedidoJpa.
   *
   * @param itens O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static List<ItemPedidoJpa> getListItemPedidoJpa(List<ItemPedido> itens) {
    
    List<ItemPedidoJpa> result = new ArrayList<>();
    
    for (ItemPedido item : itens) {
      ItemPedidoJpa itemJpa = getItemPedidoJpa(item);
      result.add(itemJpa);
    }

    return result;
  }

  /**
   * Mapeia um objeto do tipo List-ItemPedidoJpa para o tipo List-ItemPedido.
   *
   * @param itensJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static List<ItemPedido> getListItemPedido(List<ItemPedidoJpa> itensJpa)
      throws BusinessRuleException {
    
    List<ItemPedido> result = new ArrayList<>();
    
    for (ItemPedidoJpa itemJpa : itensJpa) {
      ItemPedido item = getItemPedido(itemJpa);
      result.add(item);
    }
    
    return result;
  }

}
