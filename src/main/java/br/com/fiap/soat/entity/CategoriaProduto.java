package br.com.fiap.soat.entity;

/**
 * Lista as categorias de produto.
 */
public enum CategoriaProduto {
  
  LANCHE,
  ACOMPANHAMENTO,
  BEBIDA,
  SOBREMESA;

  public static CategoriaProduto fromString(String categoriaStr) {

    categoriaStr = categoriaStr == null ? null : categoriaStr.toUpperCase().trim();

    try {
      return CategoriaProduto.valueOf(categoriaStr);
    } catch (Exception e) {
      return null;
    }
  }
}
