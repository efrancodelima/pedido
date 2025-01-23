package br.com.fiap.soat.mapper.mapper;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ProdutoMapper.
 */
public final class ProdutoMapper {

  private ProdutoMapper() {}

  /**
   * Mapeia um objeto do tipo Produto para o tipo ProdutoJpa.
   *
   * @param produto O objeto a ser mapeado.
   * @return O objeto mapeado.
   */
  public static ProdutoJpa getProdutoJpa(Produto produto) {

    return new ProdutoJpa(produto.getCodigo(), produto.getNome(),
        produto.getDescricao(), produto.getPreco(), produto.getCategoria());
  }

  /**
   * Mapeia um objeto do tipo ProdutoJpa para o tipo Produto.
   *
   * @param produtoJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Produto getProduto(ProdutoJpa produtoJpa) throws BusinessRuleException  {

    return new Produto(produtoJpa.getCodigo(), produtoJpa.getNome(),
        produtoJpa.getDescricao(), produtoJpa.getPreco(), produtoJpa.getCategoria());
  }

  /**
   * Mapeia um objeto do tipo List-ProdutoJpa para o tipo List-Produto.
   *
   * @param produtosJpa O objeto a ser mapeado.
   * @return O objeto mapeado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static List<Produto> getListProduto(List<ProdutoJpa> produtosJpa)
      throws BusinessRuleException {

    List<Produto> produtos = new ArrayList<>();
    
    for (ProdutoJpa produtoJpa : produtosJpa) {
      Produto produto = getProduto(produtoJpa);
      produtos.add(produto);
    }

    return produtos;
  }

}
