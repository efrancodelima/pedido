package br.com.fiap.soat.service.provider.cliente;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
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

  @Autowired
  public CadastrarClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

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
