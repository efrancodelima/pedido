package br.com.fiap.soat.mapper.produto;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;

/**
 * Responsável por mapear um produto Dto para uma entidade JPA.
 */
public class ProdutoMapper {

  private ProdutoMapper() {}

  public static ProdutoJpa toEntity(ProdutoDto produtoDto) {
    
    var produtoJpa =  new ProdutoJpa();

    produtoJpa.setNome(produtoDto.getNome());
    produtoJpa.setDescricao(produtoDto.getDescricao());
    produtoJpa.setPreco(produtoDto.getPreco());

    var categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
    produtoJpa.setCategoria(categoria);

    return produtoJpa;
  }
}
