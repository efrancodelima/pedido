package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;

/**
 * Classe ClienteRequestAdapter.
 */
public final class ClienteRequestAdapter {

  private ClienteRequestAdapter() {}

  /**
   * Adapta um objeto do tipo ClienteDto para o tipo Cliente.
   *
   * @param clienteDto O objeto a ser adaptado.
   * @return O objeto adaptado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Cliente adaptar(ClienteDto clienteDto) throws BusinessRuleException {
    Cpf cpf = new Cpf(clienteDto.getCpf());
    String nome = clienteDto.getNome();
    String email = clienteDto.getEmail();
    return new Cliente(null, cpf, nome, email);
  }

}
