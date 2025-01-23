package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;

/**
 * Interface ClienteController.
 */
public interface ClienteController {

  /**
   * Cadasstra o cliente.
   *
   * @param clienteDto O cliente a ser cadastrado.
   * @return O cliente cadastrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto)
      throws ApplicationException, BusinessRuleException;

  /**
   * Busca o cliente pelo CPF.
   *
   * @param cpfLong O CPF do cliente a ser buscado.
   * @return O cliente encontrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<Cliente> buscarClientePeloCpf(Long cpfLong)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;
}
