package br.com.fiap.soat.controller.produto.implementation;

import br.com.fiap.soat.controller.produto.contract.EditarProduto;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.produto.EditarProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para cadastro de clientes.
 */
@RestController
@RequestMapping("/produtos")
public class EditarProdutoImpl implements EditarProduto {

  private final EditarProdutoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para cadastrar o cliente.
   */
  @Autowired
  public EditarProdutoImpl(EditarProdutoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<ProdutoJpa>>
      editarProduto(long codigo, ProdutoDto produtoDto) {

    try {
      var produto = service.execute(codigo, produtoDto);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(produto));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BusinessRulesException e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(new ResponseWrapper<>(e.getMessage()));    
    }
  }
}

