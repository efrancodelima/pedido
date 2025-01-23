package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe BuscarClientePeloCpf.
 */
public final class BuscarClientePeloCpf {

  private BuscarClientePeloCpf() {}

  

  /**
  * Buscar cliente pelo Cpf.
  *
  * @param gateway O gateway para acesso ao repositório do banco de dados.
  * @param cpf O Cpf do cliente a ser buscado.
  * @return O cliente encontrado para o Cpf informado.
  * @throws ApplicationException Exceção da aplicação lançada pélo método.
  * @throws BusinessRuleException Exceção de regra de negócio lançada pélo método.
  * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pélo método.
  */
  public static Cliente buscar(InClienteGateway gateway, Cpf cpf)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Validar.notNull(cpf, EnumApplicationExceptions.CPF_NULO);

    Cliente cliente = gateway.buscarClientePorCpf(cpf);
    Validar.notNull(cliente, EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO);

    return cliente;
  }

}
