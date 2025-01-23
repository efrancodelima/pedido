package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Interface ProdutoController.
 */
public interface ProdutoController {

  /**
   * Cadastra o produto.
   *
   * @param produtoDto O produto a ser cadastrado.
   * @return O produto cadastrado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto)
      throws ApplicationException, BusinessRuleException;

  /**
   * Edita o produto.
   *
   * @param codigo O código do produto a ser editado.
   * @param produtoDto O produto editado.
   * @return O produto editado.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<Produto> editarProduto(Long codigo, ProdutoDto produtoDto)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Remove o produto.
   *
   * @param codigo O código do produto a ser removido.
   * @return O produto removido.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<Produto> removerProduto(Long codigo)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

  /**
   * Busca os produtos por categoria.
   *
   * @param categoriaStr A categoria dos produtos a serem buscados.
   * @return A lista com os produtos encontrados.
   * @throws ApplicationException Exceção da aplicação lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   * @throws ResourceNotFoundException Exceção de recurso não encontrado lançada pelo método.
   */
  ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr)
      throws ApplicationException, BusinessRuleException, ResourceNotFoundException;

}
