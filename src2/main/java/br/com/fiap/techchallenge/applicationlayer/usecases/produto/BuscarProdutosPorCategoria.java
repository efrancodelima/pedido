package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.util.List;

/**
 * Classe BuscarProdutosPorCategoria.
 */
public final class BuscarProdutosPorCategoria {

  private BuscarProdutosPorCategoria() {}

  /**
   * Busca os produtos pela categoria informada.
   *
   * @param gateway O gateway para acesso ao repositório do banco de dados.
   * @param categoria A categoria dos produtos que serão buscados.
   * @return A lista com os produtos encontrados.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  public static List<Produto> buscar(InProdutoGateway gateway, CategoriaProduto categoria)
      throws ApplicationException, ResourceNotFoundException, BusinessRuleException {

    Validar.notNull(categoria, EnumApplicationExceptions.CATEGORIA_NULA);

    List<Produto> produtos = gateway.buscarProdutosPorCategoria(categoria);
    Validar.listNotEmpty(produtos, EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA);

    return produtos;
  }

}
