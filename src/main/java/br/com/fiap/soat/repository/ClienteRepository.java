package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.ClienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Interface do repositório de clientes.
 */
@Component
public interface ClienteRepository extends JpaRepository<ClienteJpa, Long> {

  /**
   * Busca o cliente pelo CPF.
   *
   * @param cpf O CPF do cliente.
   * @return O cliente encontrado.
   */
  ClienteJpa findByCpf(long cpf);

  /**
   * Verifica se existe um cliente para o CPF informado.
   *
   * @param cpf O CPF do cliente.
   * @return True ou false, conforme o caso.
   */
  boolean existsByCpf(long cpf);

}
