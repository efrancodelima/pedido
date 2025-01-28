package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PedidoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de pedidos.
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoJpa, Long> {}
