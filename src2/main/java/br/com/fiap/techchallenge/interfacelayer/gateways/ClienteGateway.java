package br.com.fiap.techchallenge.interfacelayer.gateways;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.mappers.ClienteMapper;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe ClienteGateway.
 */
@Component
public class ClienteGateway implements InClienteGateway {

  // Atributos
  private final InClienteRepository clienteJpaRepository;

  /**
   * Construtor público de ClienteGateway.
   *
   * @param clienteJpaRepository O repositório JPA para acesso aos dados.
   */
  @Autowired
  public ClienteGateway(InClienteRepository clienteJpaRepository) {
    this.clienteJpaRepository = clienteJpaRepository;
  }


  // Métodos públicos
  @Override
  public Cliente gravarCliente(Cliente cliente) throws BusinessRuleException {

    ClienteJpa clienteJpa = ClienteMapper.getClienteJpa(cliente);

    clienteJpa = clienteJpaRepository.save(clienteJpa);

    return ClienteMapper.getCliente(clienteJpa);
  }

  @Override
  public Cliente buscarClientePorCpf(Cpf cpf) throws BusinessRuleException {

    ClienteJpa clienteJpa = clienteJpaRepository.findByCpf(cpf.pegarNumeroComDigito());

    if (clienteJpa != null) {
      return ClienteMapper.getCliente(clienteJpa);
    } else {
      return null;
    }
  }

  @Override
  public boolean clienteJaExiste(Cpf cpf) {
    return clienteJpaRepository.existsByCpf(cpf.pegarNumeroComDigito());
  }

}
