package br.com.fiap.soat.service.provider.cliente;

import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import br.com.fiap.soat.validator.cliente.CpfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para buscar clientes pelo número do CPF.
 */
@Component
public class BuscarClienteService {

  private final ClienteRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public BuscarClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  /**
   * Busca o cliente pelo CPF.
   *
   * @param cpf O CPF do cliente a ser buscado.
   * @return O cliente encontrado.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public ClienteJpa execute(Long cpf) throws BadRequestException, NotFoundException {
    
    CpfValidator.validar(cpf);
    var cliente = repository.findByCpf(cpf);

    if (cliente == null) {
      throw new NotFoundException(NotFoundMessage.CPF_CLIENTE);
    }
    return cliente;
  }
  
}
