package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.repository.ClienteRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear um pedido DTO para uma entidade JPA.
 */
@Component
public class PedidoMapper {

  private final ClienteRepository clienteRepository;

  @Autowired
  private PedidoMapper(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  /**
   * Mapeia um pedido DTO para uma entidade JPA.
   *
   * @param pedidoDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   */
  public PedidoJpa toEntity(PedidoDto pedidoDto) {

    var pedidoJpa = new PedidoJpa();

    // Seta o cliente
    var clienteJpa = clienteRepository.findById(pedidoDto.getCodigoCliente());
    if (clienteJpa.isPresent()) {
      pedidoJpa.setClienteJpa(clienteJpa.get());
    }

    // Seta a lista de itens
    var listaItensJpa = new ArrayList<ItemPedidoJpa>();
    for (var itemDto : pedidoDto.getItens()) {
      var itemJpa = ItemPedidoMapper.toEntity(itemDto);
      listaItensJpa.add(itemJpa);
    }
    pedidoJpa.setItensJpa(listaItensJpa);

    // Seta o timestamp do chekout
    pedidoJpa.setTimestampCheckout(LocalDateTime.now());

    return pedidoJpa;
  }
    
}
