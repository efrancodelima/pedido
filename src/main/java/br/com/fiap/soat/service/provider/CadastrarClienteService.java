package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.mapper.ClienteMapper;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para cadastrar clientes.
 */
@Component
public class CadastrarClienteService implements Service<ClienteDto, ClienteJpa> {

  private final ClienteRepository repository;

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
   * @param clienteDto O cliente a ser cadastrado.
   * @return O cliente cadastrado.
   * @throws BadRequestException Exceção lançada pelo método.
   */
  // @Override
  public ClienteJpa execute(ClienteDto clienteDto) throws BadRequestException {

    ClienteValidator.validar(clienteDto);
    
    var cliente = ClienteMapper.toEntity(clienteDto);

    return repository.save(cliente);
  }
}
