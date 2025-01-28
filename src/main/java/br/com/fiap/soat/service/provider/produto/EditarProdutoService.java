package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.mapper.produto.ProdutoMapper;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.CodigoValidator;
import br.com.fiap.soat.validator.produto.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para editar produto.
 */
@Component
public class EditarProdutoService {

  private final ProdutoRepository repository;

  @Autowired
  public EditarProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  public ProdutoJpa execute(Long codigoProduto, ProdutoDto produtoDto)
      throws BadRequestException, BusinessRulesException, NotFoundException {

    CodigoValidator.validar(codigoProduto, ProdutoJpa.class);
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
