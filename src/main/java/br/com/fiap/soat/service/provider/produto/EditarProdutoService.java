package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.dto.controller.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.mapper.produto.ProdutoMapper;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CodigoProdutoValidator;
import br.com.fiap.soat.validator.produto.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para editar produto.
 */
@Component
public class EditarProdutoService {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public EditarProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /** 
   * Editar produto.
   *
   * @param codigoProduto O código do produto que será editado.
   * @param produtoDto O produto com as alterações feitas.
   * @return O produto editado.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public ProdutoJpa execute(Long codigoProduto, ProdutoDto produtoDto)
      throws BadRequestException, NotFoundException {

    CodigoProdutoValidator.validar(codigoProduto);
    ProdutoValidator.validar(produtoDto);

    var produtoExiste = repository.existsById(codigoProduto);
    if (!produtoExiste) {
      throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
    }
    
    var produtoJpa = ProdutoMapper.toEntity(produtoDto);
    produtoJpa.setCodigo(codigoProduto);

    return repository.save(produtoJpa);
  }
}
