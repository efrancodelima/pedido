package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.controller.PedidoDto;
import br.com.fiap.soat.dto.service.ProdutoDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.NotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear um pedido DTO para uma entidade JPA.
 */
@Component
public class PedidoMapper {

  private PedidoMapper() {}

  /**
   * Mapeia um pedido DTO para uma entidade JPA.
   *
   * @param pedidoDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public PedidoJpa toEntity(PedidoDto pedidoDto, List<ProdutoDto> produtosDto,
         ClienteJpa clienteJpa) throws NotFoundException {

    var pedidoJpa = new PedidoJpa();

    pedidoJpa.setClienteJpa(clienteJpa);
    
    var listaItensJpa = getListaItensJpa(pedidoDto);
    pedidoJpa.setItensJpa(listaItensJpa);

    pedidoJpa.setTimestampCheckout(LocalDateTime.now());

    var valorPedido = calcularValorPedido(listaItensJpa, produtosDto);
    pedidoJpa.setValor(valorPedido);

    return pedidoJpa;
  }

  private List<ItemPedidoJpa> getListaItensJpa(PedidoDto pedido) {

    var listaItensJpa = new ArrayList<ItemPedidoJpa>();
    for (var itemDto : pedido.getItens()) {
      var itemJpa = ItemPedidoMapper.toEntity(itemDto);
      listaItensJpa.add(itemJpa);
    }
    return listaItensJpa;
  }

  private BigDecimal calcularValorPedido(List<ItemPedidoJpa> itensPedido,
      List<ProdutoDto> produtos) {

    var valorPedido = BigDecimal.ZERO;

    for (var item : itensPedido) {
      
      var produto = produtos.stream()
          .filter(p -> p.getCodigo().equals(item.getCodigoProduto())).findFirst();
      
      if (produto.isPresent()) {

        var valorProduto = produto.get().getPreco();
        var valorItem = valorProduto.multiply(BigDecimal.valueOf(item.getQuantidade()));
        valorPedido = valorPedido.add(valorItem);
      }
    }

    return valorPedido;
  }
    
}
