package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface InProdutoRepository.
 */
public interface InProdutoRepository extends JpaRepository<ProdutoJpa, Long> {

  /**
   * Lista os produtos por categoria.
   *
   * @param categoria A categoria dos produtos a serem listados.
   * @return A lista dos produtos.
   */
  List<ProdutoJpa> findByCategoria(CategoriaProduto categoria);

}
