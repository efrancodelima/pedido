package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CodigoValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para buscar um ou mais produtos.
 */
@Component
public class BuscarProdutosService {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public BuscarProdutosService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /**
   * Buscar um ou mais produtos.
   *
   * @param codigoProdutos Uma lista com os códigos dos produtos a serem buscados.
   * @return Um objeto contendo a lista dos produtos encontrados,
   *     em caso de sucesso, ou a mensagem de erro, em caso de falha.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   */
  public List<ProdutoJpa> execute(List<Long> codigoProdutos) throws BadRequestException {
    
    CodigoValidator.validar(codigoProdutos, ProdutoJpa.class);

    List<ProdutoJpa> produtos = new ArrayList<>();

    for (Long codigo : codigoProdutos) {

      var produtoOpt = repository.findById(codigo);

      if (produtoOpt.isPresent()) {
        produtos.add(produtoOpt.get());
      
      } else {
        produtos.add(null);
      }
    }

    return produtos;
  }
  
}
