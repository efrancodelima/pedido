package br.com.fiap.soat.mapper.pedido;

import br.com.fiap.soat.dto.controller.ItemPedidoDto;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear um item pedido DTO para uma entidade JPA.
 */
@Component
public class ItemPedidoMapper {

  private final ProdutoRepository produtoRepository;

  @Autowired
  private ItemPedidoMapper(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public ItemPedidoJpa toEntity(ItemPedidoDto itemDto) throws NotFoundException {

    var produto = buscarProduto(itemDto.getCodigoProduto());

    var itemJpa = new ItemPedidoJpa();

    itemJpa.setProduto(produto);
    itemJpa.setQuantidade(itemDto.getQuantidade());

    return itemJpa;
  }

  private ProdutoJpa buscarProduto(Long codigoProduto) throws NotFoundException {

    var produtoOpt = produtoRepository.findById(codigoProduto);

    if (!produtoOpt.isPresent()) {
      throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
    }
    return produtoOpt.get();
  }
}
