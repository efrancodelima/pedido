package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe BuscarProduto.
 */
public final class BuscarProduto {

  private BuscarProduto() {}

  /**
   * Busca o produto pelo código.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param codigoProduto O código do produto a ser buscado.
   * @return O produto encontrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static Produto buscar(InProdutoGateway gateway, Long codigoProduto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
    Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

    Produto produto = gateway.buscarProduto(codigoProduto);
    Validar.notNull(produto, EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

    return produto;
  }

}
