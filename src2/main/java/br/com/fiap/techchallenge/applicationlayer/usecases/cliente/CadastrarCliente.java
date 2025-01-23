package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe CadastrarCliente.
 */
public final class CadastrarCliente {

  private CadastrarCliente() {}

  /**
   * Cadastrar cliente.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param cliente O cliente que será cadastrado.
   * @return O cliente que foi cadastrado.
   * @throws ApplicationException Exceção da aplicação lançada pélo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pélo método.
   */
  public static Cliente cadastrar(InClienteGateway gateway, Cliente cliente)
      throws ApplicationException, BusinessRuleException {

    Validar.notNull(cliente, EnumApplicationExceptions.CLIENTE_NULO);

    boolean clienteJaExiste = gateway.clienteJaExiste(cliente.getCpf());
    
    if (clienteJaExiste) {
      throw new ApplicationException(EnumApplicationExceptions.CLIENTE_JA_EXISTE.getMensagem());
    }

    return gateway.gravarCliente(cliente);
  }

}
