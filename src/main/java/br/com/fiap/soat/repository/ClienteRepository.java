package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.ClienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de clientes.
 */
@Repository
public interface ClienteRepository extends JpaRepository<ClienteJpa, Long> {

  ClienteJpa findByCpf(long cpf);

  boolean existsByCpf(long cpf);
}
