package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.entity.ProdutoJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class BuscarPedidoTest {

  private PedidoJpa pedido;
  private Response response;
  private final String url = "http://localhost:8080/pedidos/buscar/{numeros}";

  @Dado("que o cliente fez um pedido")
  public void criarRequisicao() {
    pedido = getPedidoJpa();
  }
  
  @Quando("o usuário consultar o pedido")
  public void enviarRequisicao() {
    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .get(url, pedido.getNumero());
  }
  
  @Entao("o sistema deve retornar o pedido feito")
  public void conferirResposta() throws Exception {
    
    assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());
    var pedidoRecebido = responseBody.get("data").get(0);

    System.out.println(pedidoRecebido.asText());

    var numero = pedidoRecebido.get("numero").asLong();
    assertEquals(pedido.getNumero(), numero);
    
    var codigoCliente = pedidoRecebido.get("cliente").get("codigo").asLong();
    assertEquals(pedido.getCliente().getCodigo(), codigoCliente);
    
    var qtdeItens = pedidoRecebido.get("itens").size();
    assertEquals(pedido.getItens().size(), qtdeItens);

    var codigoProduto = pedidoRecebido.get("itens").get(0).get("produto").get("codigo").asLong();
    assertEquals(pedido.getItens().get(0).getProduto().getCodigo(), codigoProduto);
    
    var qtdeProduto = pedidoRecebido.get("itens").get(0).get("quantidade").asInt();
    assertEquals(pedido.getItens().get(0).getQuantidade(), qtdeProduto);
    
    var valorPedido = pedidoRecebido.get("valor").asDouble();
    assertEquals(pedido.getValor(), BigDecimal.valueOf(valorPedido));
  }

  private PedidoJpa getPedidoJpa() {

    var cliente = new ClienteJpa(1L, 23456789092L, 
        "Maria Clara de Oliveira", "maria_oliveira@gmail.com");

    var produto = new ProdutoJpa(1L, "Batata Frita", "Batata frita crocante",
        BigDecimal.valueOf(5.5F), CategoriaProduto.ACOMPANHAMENTO);
    
    var itemPedido = new ItemPedidoJpa(produto, 2);

    var listaItens = new ArrayList<ItemPedidoJpa>();
    listaItens.add(itemPedido);

    var valor = produto.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));

    return new PedidoJpa(1L, cliente, listaItens, LocalDateTime.now(), valor);
  }
}
