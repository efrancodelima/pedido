package br.com.fiap.soat.mapper.cliente;

import br.com.fiap.soat.dto.controller.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;

/**
 * Responsável por mapear um cliente DTO para uma entidade JPA.
 */
public class ClienteMapper {

  private ClienteMapper() {}

  /**
   * Mapeia um cliente DTO para uma entidade JPA.
   *
   * @param clienteDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   */
  public static ClienteJpa toEntity(ClienteDto clienteDto) {
    
    if (clienteDto == null) {
      return null;
    }

    ClienteJpa clienteJpa = new ClienteJpa();
    clienteJpa.setCpf(clienteDto.getCpf());
    clienteJpa.setNome(clienteDto.getNome());
    clienteJpa.setEmail(clienteDto.getEmail());

    return clienteJpa;
  }
}
