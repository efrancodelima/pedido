package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import java.math.BigDecimal;

/**
 * Classe ProdutoRequestAdapter.
 */
public final class ProdutoRequestAdapter {

  private ProdutoRequestAdapter() {}

  /**
   * Adapta um objeto do tipo ProdutoDto para o tipo Produto, sem o código do produto.
   *
   * @param produtoDto O objeto a ser adaptado.
   * @return O objeto adaptado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Produto adaptar(ProdutoDto produtoDto) throws BusinessRuleException {

    String nome = produtoDto.getNome().trim();
    String descricao = produtoDto.getDescricao();
    BigDecimal preco = produtoDto.getPreco();
    CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
    return new Produto(null, nome, descricao, preco, categoria);
  }

  /**
   * Adapta um objeto do tipo ProdutoDto para o tipo Produto, com o código do produto.
   *
   * @param codigo O código do produto a ser adaptado.
   * @param produtoDto O objeto a ser adaptado.
   * @return O objeto adaptado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static Produto adaptar(long codigo, ProdutoDto produtoDto) throws BusinessRuleException {

    String nome = produtoDto.getNome().trim();
    String descricao = produtoDto.getDescricao();
    BigDecimal preco = produtoDto.getPreco();
    CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
    return new Produto(codigo, nome, descricao, preco, categoria);
  }

}
