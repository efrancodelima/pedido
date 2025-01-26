package br.com.fiap.soat.controller.produto.implementation;

import br.com.fiap.soat.controller.produto.contract.RemoverProduto;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.produto.RemoverProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para remover produto.
 */
@RestController
@RequestMapping("/produto")
public class RemoverProdutoImpl implements RemoverProduto {

  private final RemoverProdutoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para remover o produto.
   */
  @Autowired
  public RemoverProdutoImpl(RemoverProdutoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<Void>> removerProduto(long codigo) {
    
    try {
      service.execute(codigo);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}

