package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.dto.controller.ProdutoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.math.BigDecimal;

/**
 * Responsável por validar um produto (DTO).
 */
public class ProdutoValidator {

  private ProdutoValidator() {}

  /**
   * Valida um produto (DTO).
   *
   * @param produtoDto O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   */
  public static void validar(ProdutoDto produtoDto) throws BadRequestException {

    if (produtoDto == null) {
      throw new BadRequestException(BadRequestMessage.PROD_NULO);
    }

    validarNome(produtoDto.getNome());
    validarDescricao(produtoDto.getDescricao());
    validarPreco(produtoDto.getPreco());
    CategoriaProdutoValidator.validar(produtoDto.getCategoria());
  }

  private static void validarNome(String nome) throws BadRequestException {

    if (nome == null) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_NULO);
    }
    
    nome = nome.trim();
    
    if (nome.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_NULO);
    
    }
    
    if (nome.length() < 5) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_MIN);
    }
    
    if (nome.length() > 20) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_MAX);
    }
  }

  private static void validarDescricao(String descricao) throws BadRequestException {

    if (descricao != null) {

      descricao = descricao.trim();
      
      if (descricao.length() < 20) {
        throw new BadRequestException(BadRequestMessage.PROD_DESC_MIN);
      }
      
      if (descricao.length() > 150) {
        throw new BadRequestException(BadRequestMessage.PROD_DESC_MAX);
      }
    }
  }

  private static void validarPreco(BigDecimal preco) throws BadRequestException {
    
    if (preco == null) {
      throw new BadRequestException(BadRequestMessage.PROD_PRECO_NULO);
    }
    
    if (preco.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BadRequestException(BadRequestMessage.PROD_PRECO_MIN);
    }
    
    if (preco.compareTo(BigDecimal.valueOf(300)) > 0) {
      throw new BadRequestException(BadRequestMessage.PROD_PRECO_MAX);
    }
  }
}
