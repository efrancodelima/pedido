package br.com.fiap.techchallenge.businesslayer.entities.produto;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ProdutoExceptions;
import java.math.BigDecimal;

/**
 * Classe Produto.
 */
public class Produto {
  private Long codigo;
  private String nome;
  private String descricao;
  private BigDecimal preco;
  private CategoriaProduto categoria;

  /**
   * Contrutor público de Produto.
   *
   * @param codigo O código do produto.
   * @param nome O nome do produto.
   * @param descricao A descrição do produto.
   * @param preco O preço do produto.
   * @param categoria A categoria do produto.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo construtor.
   */
  public Produto(Long codigo, String nome,
      String descricao, BigDecimal preco, CategoriaProduto categoria) throws BusinessRuleException {

    setCodigo(codigo);
    setNome(nome);
    setDescricao(descricao);
    setPreco(preco);
    setCategoria(categoria);
  }

  // Getters
  public Long getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public CategoriaProduto getCategoria() {
    return categoria;
  }

  // Setters
  private void setCodigo(Long codigo) throws BusinessRuleException {
    validarCodigo(codigo);
    this.codigo = codigo;
  }

  private void setNome(String nome) throws BusinessRuleException {
    nome = nome == null ? null : nome.trim();
    validarNome(nome);
    this.nome = nome;
  }

  private void setDescricao(String descricao) throws BusinessRuleException {

    if (descricao == null || descricao.isBlank()) {
      descricao = null;
    } else {
      descricao = descricao.trim();
    }

    validarDescricao(descricao);
    this.descricao = descricao;
  }

  private void setPreco(BigDecimal preco) throws BusinessRuleException {
    validarPreco(preco);
    this.preco = preco;
  }

  private void setCategoria(CategoriaProduto categoria) throws BusinessRuleException {
    validarCategoria(categoria);
    this.categoria = categoria;
  }

  // Métodos de validação
  private void validarCodigo(Long codigo) throws BusinessRuleException {
    if (codigo != null && codigo < 1) {
      throw new BusinessRuleException(ProdutoExceptions.CODIGO_MIN.getMensagem());
    }
  }

  private void validarNome(String nome) throws BusinessRuleException {
    if (nome == null || nome.isEmpty()) {
      throw new BusinessRuleException(ProdutoExceptions.NOME_VAZIO.getMensagem());
    } else if (nome.length() < 5) {
      throw new BusinessRuleException(ProdutoExceptions.NOME_MIN_CHAR.getMensagem());
    } else if (nome.length() > 20) {
      throw new BusinessRuleException(ProdutoExceptions.NOME_MAX_CHAR.getMensagem());
    }
  }

  private void validarDescricao(String descricao) throws BusinessRuleException {
    if (descricao != null) {
      
      if (descricao.length() < 20) {
        throw new BusinessRuleException(ProdutoExceptions.DESCRICAO_MIN.getMensagem());
      
      } else if (descricao.length() > 150) {
        throw new BusinessRuleException(ProdutoExceptions.DESCRICAO_MAX.getMensagem());
      }
    }
  }

  private void validarPreco(BigDecimal preco) throws BusinessRuleException {
    if (preco == null) {
      throw new BusinessRuleException(ProdutoExceptions.PRECO_NULO.getMensagem());
    } else if (preco.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BusinessRuleException(ProdutoExceptions.PRECO_MIN.getMensagem());
    } else if (preco.compareTo(BigDecimal.valueOf(300)) > 0) {
      throw new BusinessRuleException(ProdutoExceptions.PRECO_MAX.getMensagem());
    }
  }

  private void validarCategoria(CategoriaProduto categoria) throws BusinessRuleException {
    if (categoria == null) {
      throw new BusinessRuleException(ProdutoExceptions.CATEGORIA_NULA.getMensagem());
    }
  }
}
