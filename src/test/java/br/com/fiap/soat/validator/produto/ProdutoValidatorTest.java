package br.com.fiap.soat.validator.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;


class ProdutoValidatorTest {

  @Test
  void deveValidarUmProdutoComSucesso() {
    assertDoesNotThrow(() -> {
      ProdutoValidator.validar(getProdutoCompleto());
    });
  }
  
  @Test
  void deveLancarExcecaoParaProdutoNulo() {

    var excecao = assertThrows(BadRequestException.class, () -> {
      ProdutoValidator.validar(null);
    });

    assertEquals(BadRequestMessage.PROD_NULL.getMessage(), excecao.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoNomeForNulo() {

    var produto = getProdutoCompleto();
    produto.setNome(null);

    var excecao = assertThrows(BadRequestException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BadRequestMessage.PROD_NOME_NULL.getMessage(), excecao.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoNomeForStringVazia() {

    var produto = getProdutoCompleto();
    produto.setNome("");

    var excecao = assertThrows(BadRequestException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BadRequestMessage.PROD_NOME_NULL.getMessage(), excecao.getMessage());
  }
  
  @Test
  void deveLancarExcecaoQuandoNomeTiverMenos5Char() {

    var produto = getProdutoCompleto();
    produto.setNome("Abc");

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_NOME_MIN.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoNomeTiverMais20Char() {

    var produto = getProdutoCompleto();
    produto.setNome("Abc".repeat(10));

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_NOME_MAX.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoDescricaoTiverMenos20Char() {

    var produto = getProdutoCompleto();
    produto.setDescricao("Descrição");

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_DESC_MIN.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoDescricaoTiverMais150Char() {

    var produto = getProdutoCompleto();
    produto.setDescricao("Descrição".repeat(20));

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_DESC_MAX.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoPrecoForNulo() {

    var produto = getProdutoCompleto();
    produto.setPreco(null);

    var excecao = assertThrows(BadRequestException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BadRequestMessage.PROD_PRECO_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoPrecoNaoForPositivo() {

    var produto = getProdutoCompleto();
    produto.setPreco(BigDecimal.ZERO);

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_PRECO_MIN.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoPrecoForMaior300() {

    var produto = getProdutoCompleto();
    produto.setPreco(BigDecimal.valueOf(300.01));

    var excecao = assertThrows(BusinessRulesException.class, () -> {
      ProdutoValidator.validar(produto);
    });

    assertEquals(BusinessRulesMessage.PROD_PRECO_MAX.getMessage(), excecao.getMessage());
  }

  private ProdutoDto getProdutoCompleto() {
    return new ProdutoDto("Nome do produto", "Descrição do produto",
        BigDecimal.valueOf(35), "LANCHE");
  }
  
}
