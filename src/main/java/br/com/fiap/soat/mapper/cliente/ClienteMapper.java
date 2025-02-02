package br.com.fiap.soat.mapper.cliente;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;

/**
 * Responsável por mapear um cliente DTO para uma entidade JPA.
 */
public class ClienteMapper {

  private ClienteMapper() {}

  public static ClienteJpa toEntity(ClienteDto clienteDto) {
    
    ClienteJpa clienteJpa = new ClienteJpa();
    clienteJpa.setCpf(clienteDto.getCpf());
    clienteJpa.setNome(clienteDto.getNome());
    clienteJpa.setEmail(clienteDto.getEmail());

    return clienteJpa;
  }
}
