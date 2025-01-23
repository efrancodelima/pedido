package br.com.fiap.soat.service;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.mapper.CadastrarClienteMapper;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.validator.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service para cadastrar clientes.
 */
public class CadastrarClienteService implements Service<ClienteDto, ClienteJpa> {

  ClienteRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public CadastrarClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  /**
   * Cadastra o cliente.
   *
   * @param requisicao O cliente a ser cadastrado.
   * @return O cliente cadastrado.
   * @throws BadRequestException Exceção lançada pelo método.
   */
  // @Override
  public ClienteJpa execute(ClienteDto requisicao) throws BadRequestException {

    ClienteValidator.validar(requisicao);
    
    var cliente = CadastrarClienteMapper.toEntity(requisicao);

    return repository.save(cliente);
  }
}
