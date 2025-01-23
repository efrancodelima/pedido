package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InProdutoRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ProdutoApiTest {
   
  @Autowired
  InProdutoRepository repo;

  @Autowired
  ProdutoApiImpl api;

  @Test
  void verificaSeTabelaFoiPrePopulada() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades > 0);
  }

  @Test
  void deveCadastrarProduto() throws Exception {
    
    ProdutoDto produtoDto = instanciarProdutoDto();
    var response = api.cadastrarProduto(produtoDto);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(produtoDto.getNome(), response.getBody().getNome());
    assertEquals(produtoDto.getPreco(), response.getBody().getPreco());
  }

  @Test
  void deveEditarProduto() {
    
    var codigoProduto = 1L;
    var produtoEditado = instanciarProdutoDto();
    produtoEditado.setNome("Cold Dog");

    assertDoesNotThrow(() -> {

      var response = api.editarProduto(codigoProduto, produtoEditado);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(codigoProduto, response.getBody().getCodigo());
      assertEquals(produtoEditado.getNome(), response.getBody().getNome());
    });
  }

  @Test
  void deveRemoverProduto() {
    
    assertDoesNotThrow(() -> {

      var response = api.removerProduto(1L);
      assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    });
  }

  @Test
  void deveBuscarProdutosPorCategoria() {
    
    var categoria = "LANCHE";

    assertDoesNotThrow(() -> {

      var response = api.buscarProdutosPorCategoria(categoria);
      
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(false, response.getBody().isEmpty());
      assertEquals(categoria, response.getBody().get(0).getCategoria().toString());
    });
  }

  // Métodos auxiliares dos testes
  private ProdutoDto instanciarProdutoDto() {
    return instanciarProdutoDto("BEBIDA");
  }

  private ProdutoDto instanciarProdutoDto(String categoria) {
    return new ProdutoDto("Nome do produto", null, BigDecimal.valueOf(5), categoria);
  }

}
