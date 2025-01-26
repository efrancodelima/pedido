package br.com.fiap.soat.controller.produto.implementation;

import br.com.fiap.soat.controller.produto.contract.ListarProdutosPorCategoria;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.produto.ListarProdutosPorCategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para listar produtos por categoria.
 */
@RestController
@RequestMapping("/produto")
public class ListarProdutosPorCategoriaImpl implements ListarProdutosPorCategoria {

  private final ListarProdutosPorCategoriaService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para listar produtos por categoria.
   */
  @Autowired
  public ListarProdutosPorCategoriaImpl(ListarProdutosPorCategoriaService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<ProdutoJpa>>>
      listarProdutosPorCategoria(String categoria) {

    try {
      var produtos = service.execute(categoria);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(produtos));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}
