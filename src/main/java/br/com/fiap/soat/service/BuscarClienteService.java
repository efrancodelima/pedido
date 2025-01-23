package br.com.fiap.soat.service;

import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.validator.CpfValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service para buscar clientes pelo número do CPF.
 */
public class BuscarClienteService implements Service<Long, ClienteJpa> {

  ClienteRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public BuscarClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  @Override
  public ClienteJpa execute(Long cpf) throws BadRequestException {
    
    CpfValidator.validar(cpf);
    return repository.findByCpf(cpf);
  }
  
}
