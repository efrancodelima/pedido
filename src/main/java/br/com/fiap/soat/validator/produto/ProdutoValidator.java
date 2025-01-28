package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import java.math.BigDecimal;

/**
 * Responsável por validar um produto (DTO).
 */
public class ProdutoValidator {

  private ProdutoValidator() {}

  
  public static void validar(ProdutoDto produtoDto)
      throws BadRequestException, BusinessRulesException {

    if (produtoDto == null) {
      throw new BadRequestException(BadRequestMessage.PROD_NULL);
    }

    validarNome(produtoDto.getNome());
    validarDescricao(produtoDto.getDescricao());
    validarPreco(produtoDto.getPreco());
    CategoriaProdutoValidator.validar(produtoDto.getCategoria());
  }

  private static void validarNome(String nome) throws BadRequestException, BusinessRulesException {

    if (nome == null) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_NULL);
    }
    
    nome = nome.trim();
    
    if (nome.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.PROD_NOME_NULL);
    
    }
    
    if (nome.length() < 5) {
      throw new BusinessRulesException(BusinessRulesMessage.PROD_NOME_MIN);
    }
    
    if (nome.length() > 20) {
      throw new BusinessRulesException(BusinessRulesMessage.PROD_NOME_MAX);
    }
  }

  private static void validarDescricao(String descricao) throws BusinessRulesException {

    if (descricao != null) {

      descricao = descricao.trim();
      
      if (descricao.length() < 20) {
        throw new BusinessRulesException(BusinessRulesMessage.PROD_DESC_MIN);
      }
      
      if (descricao.length() > 150) {
        throw new BusinessRulesException(BusinessRulesMessage.PROD_DESC_MAX);
      }
    }
  }

  private static void validarPreco(BigDecimal preco)
      throws BadRequestException, BusinessRulesException {
    
    if (preco == null) {
      throw new BadRequestException(BadRequestMessage.PROD_PRECO_NULL);
    }
    
    if (preco.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BusinessRulesException(BusinessRulesMessage.PROD_PRECO_MIN);
    }
    
    if (preco.compareTo(BigDecimal.valueOf(300)) > 0) {
      throw new BusinessRulesException(BusinessRulesMessage.PROD_PRECO_MAX);
    }
  }
}
