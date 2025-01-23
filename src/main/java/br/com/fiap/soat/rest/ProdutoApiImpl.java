package br.com.fiap.soat.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para a API produtos.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoApiImpl implements ProdutoApi {

  // Atributos
  private final ProdutoControllerImpl controller;

  /**
   * O construtor público da classe.
   *
   * @param controller O controller de Produto.
   */
  @Autowired
  public ProdutoApiImpl(ProdutoControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto)
      throws BadRequestException, BusinessRuleException {

    return controller.cadastrarProduto(produtoDto);
  }

  @Override
  public ResponseEntity<Produto> editarProduto(long codigo, ProdutoDto produtoDto)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.editarProduto(codigo, produtoDto);
  }

  @Override
  public ResponseEntity<Produto> removerProduto(long codigo)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.removerProduto(codigo);
  }

  @Override
  public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoria)
      throws BadRequestException, BusinessRuleException, ResourceNotFoundException {

    return controller.buscarProdutosPorCategoria(categoria);
  }
}
