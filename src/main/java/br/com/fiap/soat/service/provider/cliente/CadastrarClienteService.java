package br.com.fiap.soat.service.provider.cliente;

import br.com.fiap.soat.dto.controller.ClienteDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.mapper.cliente.ClienteMapper;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.validator.cliente.ClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para cadastrar clientes.
 */
@Component
public class CadastrarClienteService {

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
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BusinessRulesException Exceção de regra de negócio lançada pelo método.
   */
  public ClienteJpa execute(ClienteDto clienteDto)
      throws BadRequestException, BusinessRulesException {

    ClienteValidator.validar(clienteDto);
    
    var clienteJaCadastrado = repository.existsByCpf(clienteDto.getCpf());
    if (clienteJaCadastrado) {
      throw new BusinessRulesException(BusinessRulesMessage.CLIENTE_JA_CADASTRADO);
    }
    
    var cliente = ClienteMapper.toEntity(clienteDto);
    return repository.save(cliente);
  }
}
