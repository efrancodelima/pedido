package br.com.fiap.techchallenge.interfacelayer.gateways;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.mappers.PedidoMapper;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InPedidoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe PedidoGateway.
 */
@Component
public class PedidoGateway implements InPedidoGateway {

  // Atributos
  private final InPedidoRepository pedidoJpaRepository;

  /**
   * Construtor público de PedidoGateway.
   *
   * @param pedidoJpaRepository O repositório JPA para acesso aos dados.
   */
  @Autowired
  public PedidoGateway(InPedidoRepository pedidoJpaRepository) {
    this.pedidoJpaRepository = pedidoJpaRepository;
  }

  // Métodos públicos
  @Override
  public Pedido gravarPedido(Pedido pedido) throws BusinessRuleException  {

    PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
    pedidoJpa = pedidoJpaRepository.save(pedidoJpa);
    return PedidoMapper.getPedido(pedidoJpa);
  }

  @Override
  public void atualizarPedido(Pedido pedido) {

    PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
    pedidoJpaRepository.save(pedidoJpa);
  }

  @Override
  public Pedido buscarPedido(long numeroPedido) throws BusinessRuleException {

    Optional<PedidoJpa> optionalPedido = pedidoJpaRepository.findById(numeroPedido);
    return optionalPedido.isPresent() ? PedidoMapper.getPedido(optionalPedido.get()) : null;
  }

  @Override
  public List<Pedido> buscarTodosOsPedidos() throws BusinessRuleException {
    List<PedidoJpa> pedidosJpa = pedidoJpaRepository.findAll();
    return PedidoMapper.getListPedido(pedidosJpa);
  }

  @Override
  public Pedido buscarPedidoPeloCodigoPagamento(long codigoPagamento) throws BusinessRuleException {
    PedidoJpa pedidoJpa = pedidoJpaRepository.findByStatusPagamentoCodigo(codigoPagamento);
    return pedidoJpa == null ? null : PedidoMapper.getPedido(pedidoJpa);
  }

}
