package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de produtos.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoJpa, Long> {

  List<ProdutoJpa> findByCategoria(CategoriaProduto categoria);
}
