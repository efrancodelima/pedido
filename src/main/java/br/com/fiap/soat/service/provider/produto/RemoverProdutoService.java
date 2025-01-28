package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CodigoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para remover produto.
 */
@Component
public class RemoverProdutoService {

  private final ProdutoRepository repository;

  @Autowired
  public RemoverProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  public void execute(Long codigoProduto)
      throws BadRequestException, NotFoundException {

    CodigoValidator.validar(codigoProduto, ProdutoJpa.class);
    
    var produtoExiste = repository.existsById(codigoProduto);
    if (!produtoExiste) {
      throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
    }
    
    repository.deleteById(codigoProduto);
  }
}
