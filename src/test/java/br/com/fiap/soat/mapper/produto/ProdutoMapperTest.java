package br.com.fiap.soat.mapper.produto;

import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProdutoMapperTest {

  @Test
  void deveMapearUmProdutoComSucesso() {

    // Arrange
    var produtoDto = new ProdutoDto("Nome do produto", null, BigDecimal.valueOf(19.9), "BEBIDA");

    // Act
    var produtoJpa = ProdutoMapper.toEntity(produtoDto);

    // Assert
    assertEquals(produtoDto.getNome(), produtoJpa.getNome());
    assertEquals(produtoDto.getDescricao(), produtoJpa.getDescricao());
    assertEquals(produtoDto.getPreco(), produtoJpa.getPreco());
    assertEquals(produtoDto.getCategoria(), produtoJpa.getCategoria().toString());
  }
}
