package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CategoriaProdutoValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para listar produtos por categoria.
 */
@Component
public class ListarProdutosPorCategoriaService {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public ListarProdutosPorCategoriaService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /**
   * Listar produtos por categoria.
   *
   * @param categoria A categoria dos produtos a serem listados.
   * @return Um objeto contendo a lista dos produtos encontrados,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   */
  public List<ProdutoJpa> execute(String categoria) throws BadRequestException {
    
    CategoriaProdutoValidator.validar(categoria);
    return repository.findByCategoria(CategoriaProduto.fromString(categoria));
  }
}
