package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import br.com.fiap.soat.dto.controller.request.ClienteDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CadastrarClienteTest {

  private ClienteDto requisicao;
  private Response resposta;
  private final String url = "http://localhost:8080/clientes/cadastrar/";

  @Given("que existe um cliente a ser cadastrado")
  public void criarRequisicao() {
    requisicao = new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
  }

  @When("o sistema receber a requisição do cliente a ser cadastrado")
  public void enviarRequisicao() {
    resposta = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(requisicao)
      .post(url);
  }

  @Then("deve retornar o cliente cadastrado com o código")
  public void confereResposta() throws Exception {

    assertEquals(HttpStatus.CREATED.value(), resposta.getStatusCode());
    
    var data = getDadosResposta();
    
    assertEquals(true, ((Integer) data.get("codigo")) >= 1L);
    assertEquals(requisicao.getNome(), data.get("nome"));
    assertEquals(requisicao.getEmail(), data.get("email"));
    assertEquals(requisicao.getCpf(), data.get("cpf"));
  }

  // Métodos auxiliares
  private Map<String, Object> getDadosResposta() throws Exception {
    
    ObjectMapper mapper = new ObjectMapper();
    
    Map<String, Object> responseMap = 
        mapper.readValue(resposta.getBody().asString(),
          new TypeReference<Map<String, Object>>() {});
    
    return (Map<String, Object>) responseMap.get("data");
  }
}
