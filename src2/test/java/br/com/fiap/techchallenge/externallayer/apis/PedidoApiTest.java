package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InPedidoRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testes de integração a partir dos endpoints da API pedidos.
 */
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PedidoApiTest {
    
  @Autowired
  InPedidoRepository repo;

  @Autowired
  PedidoApiImpl api;

  @Test
  void verificaSeTabelaFoiPrePopulada() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades > 0);
  }

  @Test
  void deveFazerCheckoutComSucesso() {

    assertDoesNotThrow(() -> {

      var response = api.fazerCheckout(instanciarPedidoDto());

      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(true, response.getBody().getNumeroPedido() > 0);
      assertEquals(StatusPedidoEnum.RECEBIDO, response.getBody().getStatus());
    }); 
  }

  @Test
  void deveAtualizarStatusPedido() {

    var codigoPedido = 2L;

    assertDoesNotThrow(() -> {

      var response = api.atualizarStatusPedido(codigoPedido);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(codigoPedido, response.getBody().getNumeroPedido());
      assertEquals(StatusPedidoEnum.PRONTO, response.getBody().getStatus());
    }); 
  }

  @Test
  void deveConsultarStatusPagamento() {

    var codigoPedido = 2L;

    assertDoesNotThrow(() -> {

      var response = api.consultarStatusPagamento(codigoPedido);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(codigoPedido, response.getBody().getNumeroPedido());
      assertEquals(StatusPagamentoEnum.APROVADO, response.getBody().getStatus());
    }); 
  }

  @Test
  void deveListarOsPedidosNaoFinalizados() {

    var qtdePedidosNaoFinalizados = 4;

    assertDoesNotThrow(() -> {

      var response = api.listarPedidos();

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(false, response.getBody().isEmpty());
      assertEquals(qtdePedidosNaoFinalizados, response.getBody().size());
    }); 
  }

  @Test
  void webhookDeveRetornarStatusNoContent() {

    var pagamentoDto = new PagamentoDto(1L, "APPROVED");

    assertDoesNotThrow(() -> {
      var response = api.webhookMercadoPago(pagamentoDto);
      assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }); 
  }

  // Métodos auxiliares dos testes
  private PedidoDto instanciarPedidoDto() {

    var itemPedidoDto = new ItemPedidoDto(6L, 2);
    var listaItensDto = new ArrayList<ItemPedidoDto>();
    listaItensDto.add(itemPedidoDto);

    return new PedidoDto(56789012303L, listaItensDto);
  }

}
