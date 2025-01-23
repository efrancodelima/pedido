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
 * Classe EditarProduto.
 */
public final class EditarProduto {

  private EditarProduto() {}

  /**
   * Atualiza o produto.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param produto O produto editado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static Produto editar(InProdutoGateway gateway, Produto produto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);

    boolean produtoExiste = gateway.produtoExiste(produto.getCodigo());
    
    if (!produtoExiste) {
      throw new ResourceNotFoundException(
          EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
    }
    
    return gateway.atualizarProduto(produto);
  }

}
