package br.com.fiap.techchallenge.interfacelayer.controllers;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.BuscarProdutosPorCategoria;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.CadastrarProduto;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.EditarProduto;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.RemoverProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ProdutoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.ProdutoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.interfaces.ProdutoController;
import br.com.fiap.techchallenge.interfacelayer.gateways.ProdutoGateway;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Classe ProdutoControllerImpl.
 */
@Component
public class ProdutoControllerImpl implements ProdutoController {

  // Atributos
  private final ProdutoGateway gateway;

  /**
   * Construtor público de ProdutoController.
   *
   * @param gateway O gateway do repositório de produtos.
   */
  @Autowired
  public ProdutoControllerImpl(ProdutoGateway gateway) {
    this.gateway = gateway;
  }

  // Métodos públicos
  @Override
  public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto)
      throws ApplicationException, BusinessRuleException {

    Produto produto = ProdutoRequestAdapter.adaptar(produtoDto);
    produto = CadastrarProduto.cadastrar(gateway, produto);
    return ProdutoResponseAdapter.adaptar(produto, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Produto> editarProduto(Long codigo, ProdutoDto produtoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException {

    Produto produto = ProdutoRequestAdapter.adaptar(codigo, produtoDto);
    produto = EditarProduto.editar(gateway, produto);
    return new ResponseEntity<>(produto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Produto> removerProduto(Long codigo)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException  {

    RemoverProduto.remover(gateway, codigo);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr)
      throws ApplicationException, ResourceNotFoundException, BusinessRuleException  {

    CategoriaProduto categoria = CategoriaProduto.fromString(categoriaStr);
    List<Produto> produtos = BuscarProdutosPorCategoria.buscar(gateway, categoria);

    if (produtos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return ProdutoResponseAdapter.adaptar(produtos, HttpStatus.OK);
    }
  }

}
