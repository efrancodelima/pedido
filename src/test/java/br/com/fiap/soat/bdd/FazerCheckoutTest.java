package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class FazerCheckoutTest {

  private PedidoDto requisicao;
  private Response resposta;
  private final String url = "http://localhost:8080/pedidos/checkout/";

  @Given("que o cliente possui um pedido")
  public void criarRequisicao() {

    var item = new ItemPedidoDto(1L, 1);

    var listaItens = new ArrayList<ItemPedidoDto>();
    listaItens.add(item);

    requisicao = new PedidoDto(null, listaItens);
  }
  
  @When("o pedido for enviado para checkout e a comunicação com os serviços externos falhar")
  public void enviarRequisicao() {
    resposta = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .body(requisicao)
      .post(url);
  }
  
  @Then("o sistema deve retornar o status bad gateway")
  public void conferirResposta() {
    assertEquals(HttpStatus.BAD_GATEWAY.value(), resposta.getStatusCode());
  }
}
