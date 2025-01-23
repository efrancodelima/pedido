package br.com.fiap.soat.mapper.mapper;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPagamentoJpa;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PedidoMapper.
 */
public final class PedidoMapper {

  private PedidoMapper() {}

  /**
   * Mapeia um objeto do tipo Pedido para o tipo PedidoJpa.
   *
   * @param pedido O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static PedidoJpa getPedidoJpa(Pedido pedido) {

    ClienteJpa clienteJpa = null;
    if (pedido.getCliente() != null) {
      clienteJpa = ClienteMapper.getClienteJpa(pedido.getCliente());
    }

    List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.getListItemPedidoJpa(pedido.getItens());
    LocalDateTime dataHoraCheckout = pedido.getDataHoraCheckout();
    
    StatusPagamentoJpa statusPagamentoJpa = null;
    if (pedido.getStatusPagamento() != null) {
      statusPagamentoJpa = StatusPagamentoMapper
          .getStatusPagamentoJpa(pedido.getStatusPagamento());
    }
    
    var statusPedidoJpa = StatusPedidoMapper.getStatusPedidoJpa(pedido.getStatusPedido());
    
    return new PedidoJpa(pedido.getNumero(), clienteJpa, itensJpa, dataHoraCheckout,
        statusPagamentoJpa, statusPedidoJpa);
  }

  /**
   * Mapeia um objeto do tipo PedidoJpa para o tipo Pedido.
   *
   * @param pedidoJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Pedido getPedido(PedidoJpa pedidoJpa) throws BusinessRuleException {

    long id = pedidoJpa.getNumero();
    Cliente cliente = null;
    List<ItemPedido> itens = ItemPedidoMapper.getListItemPedido(pedidoJpa.getItensJpa());
    LocalDateTime dataHoraCheckout = pedidoJpa.getDataHoraCheckout();
    StatusPagamento statusPagamento = null;
    StatusPedido statusPedido = StatusPedidoMapper.getStatusPedido(pedidoJpa.getStatusPedido());

    if (pedidoJpa.getClienteJpa() != null) {
      cliente = ClienteMapper.getCliente(pedidoJpa.getClienteJpa());
    }

    if (pedidoJpa.getStatusPagamento() != null) {
      statusPagamento = StatusPagamentoMapper
          .getStatusPagamento(pedidoJpa.getStatusPagamento());
    }

    return new Pedido(id, cliente, itens, dataHoraCheckout, statusPagamento, statusPedido);
  }

  /**
   * Mapeia um objeto do tipo List-PedidoJpa para o tipo List-Pedido.
   *
   * @param pedidosJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static List<Pedido> getListPedido(List<PedidoJpa> pedidosJpa)
      throws BusinessRuleException {

    List<Pedido> pedidos = new ArrayList<>();
    for (PedidoJpa pedidoJpa : pedidosJpa) {

      Pedido pedido = getPedido(pedidoJpa);
      pedidos.add(pedido);
    }
    return pedidos;
  }

}
