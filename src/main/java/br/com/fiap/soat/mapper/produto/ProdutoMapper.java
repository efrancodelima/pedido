package br.com.fiap.soat.mapper.produto;

import br.com.fiap.soat.dto.controller.ProdutoDto;
import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;

/**
 * Responsável por mapear um produto Dto para uma entidade JPA.
 */
public class ProdutoMapper {

  private ProdutoMapper() {}

  /**
   * Mapeia um produto DTO para uma entidade JPA.
   *
   * @param produtoDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   */
  public static ProdutoJpa toEntity(ProdutoDto produtoDto) {
    
    if (produtoDto == null) {
      return null;
    }

    var produtoJpa =  new ProdutoJpa();

    produtoJpa.setNome(produtoDto.getNome());
    produtoJpa.setDescricao(produtoDto.getDescricao());
    produtoJpa.setPreco(produtoDto.getPreco());

    var categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
    produtoJpa.setCategoria(categoria);

    return produtoJpa;
  }
}
