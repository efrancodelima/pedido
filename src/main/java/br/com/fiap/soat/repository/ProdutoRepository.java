package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Interface do repositório de produtos.
 */
@Component
public interface ProdutoRepository extends JpaRepository<ProdutoJpa, Long> {

  /**
   * Lista os produtos por categoria.
   *
   * @param categoria A categoria dos produtos a serem listados.
   * @return A lista dos produtos.
   */
  List<ProdutoJpa> findByCategoria(CategoriaProduto categoria);
}
