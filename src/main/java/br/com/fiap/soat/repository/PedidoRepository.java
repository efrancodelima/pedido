package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PedidoJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface do repositório de pedidos.
 */
public interface PedidoRepository extends JpaRepository<PedidoJpa, Long> {

  /**
   * Busca um pedido pelo código do pagamento.
   *
   * @param codigoPagamento O código do pagamento vinculado ao pedido.
   * @return O pedido encontrado.
   */
  PedidoJpa findByStatusPagamentoCodigo(Long codigoPagamento);

}
