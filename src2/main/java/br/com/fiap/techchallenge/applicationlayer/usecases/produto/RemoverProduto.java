package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe RemoverProduto.
 */
public final class RemoverProduto {

  private RemoverProduto() {}

  /**
   * Remove o produto, conforme o código recebido.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param codigoProduto O código do produto que será removido.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
  */
  public static void remover(InProdutoGateway gateway, Long codigoProduto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {
        
    Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
    Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

    boolean produtoExiste = gateway.produtoExiste(codigoProduto);
    
    if (!produtoExiste) {
      throw new ResourceNotFoundException(
          EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
    }
    
    gateway.removerProduto(codigoProduto);
    
  }

}
