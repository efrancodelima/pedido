package br.com.fiap.techchallenge.applicationlayer.interfaces.gateway;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.util.List;

/**
 * Interface para o gateway do produto.
 */
public interface InProdutoGateway {

  Produto gravarProduto(Produto produto) throws BusinessRuleException;

  Produto atualizarProduto(Produto produto) throws BusinessRuleException;

  void removerProduto(long codigoProduto) throws BusinessRuleException;

  Produto buscarProduto(long codigoProduto) throws BusinessRuleException;

  List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws BusinessRuleException;

  boolean produtoExiste(long codigoProduto) throws BusinessRuleException;

}
