package br.com.fiap.soat.service.provider.produto;

import br.com.fiap.soat.dto.controller.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
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

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public CadastrarProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /**
   * Cadastrar produto.
   *
   * @param produtoDto O produto a ser cadastrado.
   * @return O produto cadastrado.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   */
  // @Override
  public ProdutoJpa execute(ProdutoDto produtoDto) throws BadRequestException {

    ProdutoValidator.validar(produtoDto);
    
    var produtoJpa = ProdutoMapper.toEntity(produtoDto);

    return repository.save(produtoJpa);
  }
}
