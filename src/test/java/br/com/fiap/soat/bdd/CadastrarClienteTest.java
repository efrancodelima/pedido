package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CadastrarClienteTest {

  private ClienteDto request;
  private Response response;
  private final String url = "http://localhost:8080/clientes/cadastrar/";

  @Dado("que existe um cliente a ser cadastrado")
  public void criarRequisicao() {
    request = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
  }

  @Quando("o sistema receber a requisição do cliente a ser cadastrado")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(request)
      .post(url);
  }

  @Entao("deve retornar o cliente cadastrado com o código")
  public void confereResposta() throws Exception {

    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    
    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());

    var codigo = responseBody.get("data").get("codigo").asLong();
    assertEquals(6L, codigo);
    
    var nome = responseBody.get("data").get("nome").asText();
    assertEquals(request.getNome(), nome);
    
    var email = responseBody.get("data").get("email").asText();
    assertEquals(request.getEmail(), email);
    
    var cpf = responseBody.get("data").get("cpf").asLong();
    assertEquals(request.getCpf(), cpf);
  }
}
