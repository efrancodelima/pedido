package br.com.fiap.soat.mapper.pedido;

import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear um pedido DTO para uma entidade JPA.
 */
@Component
public class PedidoMapper {

  private final ClienteRepository clienteRepository;
  private final ItemPedidoMapper itemPedidoMapper;

  @Autowired
  private PedidoMapper(ClienteRepository clienteRepository, ItemPedidoMapper itemPedidoMapper) {
    this.clienteRepository = clienteRepository;
    this.itemPedidoMapper = itemPedidoMapper;
  }

  public PedidoJpa toEntity(PedidoDto pedidoDto) throws NotFoundException {

    var pedidoJpa = new PedidoJpa();

    var cliente = buscarCliente(pedidoDto.getCodigoCliente());
    pedidoJpa.setCliente(cliente);
    
    var listaItensJpa = getListaItensJpa(pedidoDto);
    pedidoJpa.setItens(listaItensJpa);

    pedidoJpa.setTimestampCheckout(LocalDateTime.now());

    var valorPedido = calcularValorPedido(listaItensJpa);
    pedidoJpa.setValor(valorPedido);

    return pedidoJpa;
  }

  private ClienteJpa buscarCliente(Long codigoCliente) throws NotFoundException {
    
    if (codigoCliente == null) {
      return null;
    }

    var optionalClienteJpa = clienteRepository.findById(codigoCliente);
    
    if (optionalClienteJpa.isPresent()) {
      return optionalClienteJpa.get();
    } else {
      throw new NotFoundException(NotFoundMessage.COD_CLIENTE);
    }
  }

  private List<ItemPedidoJpa> getListaItensJpa(PedidoDto pedido) throws NotFoundException {

    var listaItensJpa = new ArrayList<ItemPedidoJpa>();

    for (var itemDto : pedido.getItens()) {

      var itemJpa = itemPedidoMapper.toEntity(itemDto);
      listaItensJpa.add(itemJpa);
    }
    return listaItensJpa;
  }

  private BigDecimal calcularValorPedido(List<ItemPedidoJpa> itensPedido) {

    var valorPedido = BigDecimal.ZERO;

    for (var item : itensPedido) { 
      var valorProduto = item.getProduto().getPreco();
      var qtde = BigDecimal.valueOf(item.getQuantidade());
      var valorItem = valorProduto.multiply(qtde);
      valorPedido = valorPedido.add(valorItem);
    }
    return valorPedido;
  }
}
