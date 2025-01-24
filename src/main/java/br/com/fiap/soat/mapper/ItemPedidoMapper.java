package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.ItemPedidoDto;
import br.com.fiap.soat.entity.ItemPedidoJpa;

/**
 * Responsável por mapear um item pedido DTO para uma entidade JPA.
 */
public class ItemPedidoMapper {

  private ItemPedidoMapper() {}

  /**
   * Mapeia um item pedido DTO para uma entidade JPA.
   *
   * @param itemDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   */
  public static ItemPedidoJpa toEntity(ItemPedidoDto itemDto) {
    
    var itemJpa = new ItemPedidoJpa();
    itemJpa.setCodigoProduto(itemDto.getCodigoProduto());
    itemJpa.setQuantidade(itemDto.getQuantidade());

    return itemJpa;
  }
}
