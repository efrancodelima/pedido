package br.com.fiap.techchallenge.applicationlayer.interfaces.gateway;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Interface para o gateway do cliente.
 */
public interface InClienteGateway {

  /**
   * Grava o objeto cliente no banco de dados.
   *
   * @param cliente O objeto cliente que será gravado.
   * @return O objeto cliente que foi gravado.
   * @throws Exception Exceção lançado durante a operação.
   */
  Cliente gravarCliente(Cliente cliente) throws BusinessRuleException;

  /**
   * Busca o cliente pelo CPF.
   *
   * @param cpf O CPF do cliente.
   */
  Cliente buscarClientePorCpf(Cpf cpf) throws BusinessRuleException;

  /**
   * Verifica se o cliente existe na base de dados.
   *
   * @param cpf O CPF do cliente.
   * @return Um boolean indicando se o cliente existe.
   * @throws Exception Exceção lançado durante a operação.
   */
  boolean clienteJaExiste(Cpf cpf);

}
