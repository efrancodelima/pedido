package br.com.fiap.soat.entity;

import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.messages2.EnumExceptions;

/**
 * Lista as categorias de produto.
 */
public enum CategoriaProduto {
  LANCHE,
  ACOMPANHAMENTO,
  BEBIDA,
  SOBREMESA;

  /**
   * Recebe uma string com o nome da categoria do produto e retorna o enum correspondente.
   *
   * @param categoriaStr String com o nome da categoria do produto.
   * @return O enum com a categoria do produto.
   * @throws BusinessRuleException Exceção lançada durante a operação.
   */
  public static CategoriaProduto fromString(String categoriaStr) throws BusinessRuleException {

    categoriaStr = categoriaStr == null ? null : categoriaStr.toUpperCase().trim();

    try {
      return CategoriaProduto.valueOf(categoriaStr);

    } catch (Exception e) {
      throw new BusinessRuleException(EnumExceptions.CATEGORIA_PRODUTO.getMensagem());
    }
  }
}
