package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CodigoProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para remover produto.
 */
@Component
public class RemoverProdutoService {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public RemoverProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /** 
   * Remover produto.
   *
   * @param codigoProduto O código do produto que será removido.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public void execute(Long codigoProduto)
      throws BadRequestException, NotFoundException {

    CodigoProdutoValidator.validar(codigoProduto);
    
    var produtoExiste = repository.existsById(codigoProduto);
    if (!produtoExiste) {
      throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
    }
    
    repository.deleteById(codigoProduto);
  }
}
