package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;

/**
 * Mapeia o objeto DTO para uma entidade JPA.
 */
public class CadastrarClienteMapper {

  private CadastrarClienteMapper() {}

  /**
   * Mapeia a requisição para uma entidade.
   *
   * @param dto A requisição a ser mapeada.
   * @return A entidade mapeada.
   */
  public static ClienteJpa toEntity(ClienteDto dto) {
    
    if (dto == null) {
      return null;
    }

    ClienteJpa clienteJpa = new ClienteJpa();
    clienteJpa.setCpf(dto.cpf);
    clienteJpa.setNome(dto.nome);
    clienteJpa.setEmail(dto.email);

    return clienteJpa;
  }
}
