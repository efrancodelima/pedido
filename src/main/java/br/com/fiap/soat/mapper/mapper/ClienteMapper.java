package br.com.fiap.soat.mapper.mapper;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;

/**
 * Classe ClienteMapper.
 */
public final class ClienteMapper {

  private ClienteMapper() {}

  /**
   * Mapeia um objeto do tipo Cliente para o tipo ClienteJpa.
   *
   * @param cliente O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static ClienteJpa getClienteJpa(Cliente cliente) {

    return new ClienteJpa(cliente.getCodigo(),
        cliente.getCpf().pegarNumeroComDigito(), cliente.getNome(), cliente.getEmail());
  }

  /**
   * Mapeia um objeto do tipo ClienteJpa para o tipo Cliente.
   *
   * @param clienteJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Cliente getCliente(ClienteJpa clienteJpa) throws BusinessRuleException {

    Cpf cpf = new Cpf(clienteJpa.getCpf());
    return new Cliente(clienteJpa.getCodigo(), cpf, clienteJpa.getNome(), clienteJpa.getEmail());
  }

}
