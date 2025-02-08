package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.dto.controller.request.ProdutoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CadastrarProdutoTest {

  private ProdutoDto request;
  private Response response;
  private final String url = "http://localhost:8080/produtos/cadastrar/";

  @Dado("que existe um produto a ser cadastrado")
  public void criarRequisicao() {
    request = new ProdutoDto("Nome do produto", "Descrição do produto",
        BigDecimal.valueOf(35), "BEBIDA");
  }

  @Quando("o sistema receber a requisição de cadastro")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(request)
      .post(url);
  }

  @Entao("deve retornar o produto cadastrado com o código")
  public void confereResposta() throws Exception {

    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    
    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());

    var codigo = responseBody.get("data").get("codigo").asLong();
    assertEquals(17L, codigo);
    
    var nome = responseBody.get("data").get("nome").asText();
    assertEquals(request.getNome(), nome);
    
    var descricao = responseBody.get("data").get("descricao").asText();
    assertEquals(request.getDescricao(), descricao);
    
    var preco = responseBody.get("data").get("preco").asText();
    assertEquals(request.getPreco().toString(), preco);
    
    var categoria = responseBody.get("data").get("categoria").asText();
    assertEquals(request.getCategoria().toString(), categoria);
  }
}
