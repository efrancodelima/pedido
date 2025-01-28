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

  @Autowired
  public BuscarProdutosService(ProdutoRepository repository) {
    this.repository = repository;
  }

  public List<ProdutoJpa> execute(List<Long> codigoProdutos) throws BadRequestException {
    
    CodigoValidator.validar(codigoProdutos, ProdutoJpa.class);

    var produtos = new ArrayList<ProdutoJpa>();

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
