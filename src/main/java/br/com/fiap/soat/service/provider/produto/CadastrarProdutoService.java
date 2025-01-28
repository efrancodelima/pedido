package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.mapper.produto.ProdutoMapper;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.produto.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para cadastrar produto.
 */
@Component
public class CadastrarProdutoService {

  private final ProdutoRepository repository;

  @Autowired
  public CadastrarProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  public ProdutoJpa execute(ProdutoDto produtoDto)
      throws BadRequestException, BusinessRulesException {

    ProdutoValidator.validar(produtoDto);
    
    var produtoJpa = ProdutoMapper.toEntity(produtoDto);

    return repository.save(produtoJpa);
  }
}
