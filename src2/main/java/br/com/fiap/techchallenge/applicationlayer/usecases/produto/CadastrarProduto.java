package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe CadastrarProduto.
 */
public final class CadastrarProduto {

  private CadastrarProduto() {}

  /**
   * Cadastra o produto.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param produto O produto que será cadastrado.
   * @return O produto que foi cadastrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Produto cadastrar(InProdutoGateway gateway, Produto produto)
      throws ApplicationException, BusinessRuleException {

    Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);
    return gateway.gravarProduto(produto);
  }

}
