package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface InPedidoRepository.
 */
public interface InPedidoRepository extends JpaRepository<PedidoJpa, Long> {

  /**
   * Busca um pedido pelo código do pagamento.
   *
   * @param codigoPagamento O código do pagamento vinculado ao pedido.
   * @return O pedido encontrado.
   */
  PedidoJpa findByStatusPagamentoCodigo(Long codigoPagamento);

}
