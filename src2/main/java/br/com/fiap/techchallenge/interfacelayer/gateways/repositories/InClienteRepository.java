package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface InClienteRepository.
 */
public interface InClienteRepository extends JpaRepository<ClienteJpa, Long> {

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
