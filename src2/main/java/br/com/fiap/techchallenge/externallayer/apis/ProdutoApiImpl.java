package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.ProdutoApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.ProdutoControllerImpl;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
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
      throws ApplicationException, BusinessRuleException {

    return controller.cadastrarProduto(produtoDto);
  }

  @Override
  public ResponseEntity<Produto> editarProduto(long codigo, ProdutoDto produtoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.editarProduto(codigo, produtoDto);
  }

  @Override
  public ResponseEntity<Produto> removerProduto(long codigo)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.removerProduto(codigo);
  }

  @Override
  public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoria)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    return controller.buscarProdutosPorCategoria(categoria);
  }
}
