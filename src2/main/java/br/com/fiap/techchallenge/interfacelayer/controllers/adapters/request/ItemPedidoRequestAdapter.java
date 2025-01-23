package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ItemPedidoRequestAdapter.
 */
public final class ItemPedidoRequestAdapter {

  private ItemPedidoRequestAdapter() {}

  /**
   * Adapta uma lista do tipo List&lt;ItemPedidoDto&gt; para o tipo List&lt;ItemPedido&gt;.
   *
   * @param itens A lista de itens do pedido a ser adaptada.
   * @param produtos A lista de produtos dos itens do pedido.
   * @return A lista de itens do pedido adaptada.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static List<ItemPedido> adaptar(List<ItemPedidoDto> itens, List<Produto> produtos)
      throws BusinessRuleException {

    List<ItemPedido> result = new ArrayList<>();

    for (int i = 0; i < itens.size(); i++) {
      ItemPedido item = new ItemPedido(produtos.get(i), itens.get(i).getQuantidade());
      result.add(item);
    }
    return result;
  }
}
