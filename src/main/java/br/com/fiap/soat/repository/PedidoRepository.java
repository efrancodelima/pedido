package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.PedidoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Interface do repositório de pedidos.
 */
@Component
public interface PedidoRepository extends JpaRepository<PedidoJpa, Long> {}
