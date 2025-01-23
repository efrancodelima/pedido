package br.com.fiap.techchallenge.applicationlayer.interfaces.gateway;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

import java.util.List;

/**
 * Interface para o gateway do pedido.
 */
public interface InPedidoGateway {

  /**
   * Grava o pedido.
   *
   * @param pedido O pedido que será gravado.
   * @return O pedido que foi gravado.
   * @throws Exception Exceção lançado durante a operação.
   */
  Pedido gravarPedido(Pedido pedido) throws BusinessRuleException;

  void atualizarPedido(Pedido pedido) throws BusinessRuleException;

  Pedido buscarPedido(long numeroPedido) throws BusinessRuleException;

  Pedido buscarPedidoPeloCodigoPagamento(long codigoPagamento) throws BusinessRuleException;

  List<Pedido> buscarTodosOsPedidos() throws BusinessRuleException;

}
