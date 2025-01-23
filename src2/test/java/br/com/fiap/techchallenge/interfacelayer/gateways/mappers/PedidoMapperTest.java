package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPagamentoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPedidoJpa;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para PedidoMapper.
 */
class PedidoMapperTest {

  @Test
  void deveMapearPedidoParaPedidoJpa() throws BusinessRuleException {

    // Arrange
    var pedido = instanciarPedidoCompleto();

    // Act
    var pedidoJpa = PedidoMapper.getPedidoJpa(pedido);

    // Assert
    assertEquals(pedido.getNumero(), pedidoJpa.getNumero());
    assertEquals(pedido.getStatusPedido().getStatus(), pedidoJpa.getStatusPedido().getStatus());
    
    assertEquals(
        pedido.getCliente().getCpf().pegarNumeroComDigito(), 
        (long) pedidoJpa.getClienteJpa().getCpf());
    

    assertEquals(pedido.getItens().size(), pedidoJpa.getItensJpa().size());
    assertEquals(2, (int) pedidoJpa.getItensJpa().get(0).getQuantidade());
    assertEquals(BigDecimal.valueOf(5), pedidoJpa.getItensJpa().get(0).getProdutoJpa().getPreco());
  }

  @Test
  void deveMapearPedidoJpaParaPedido() {

    var pedidoJpa = instanciarPedidoJpaCompleto();

    assertDoesNotThrow(() -> {

      var pedido = PedidoMapper.getPedido(pedidoJpa);

      assertEquals(pedidoJpa.getNumero(), pedido.getNumero());

      assertEquals(pedidoJpa.getClienteJpa().getNome(), pedido.getCliente().getNome());
      assertEquals(
          (long) pedidoJpa.getClienteJpa().getCpf(),
          pedido.getCliente().getCpf().pegarNumeroComDigito());
      
      assertEquals(
          pedidoJpa.getStatusPedido().getStatus(),
          pedido.getStatusPedido().getStatus());
      
      assertEquals(
          pedidoJpa.getStatusPagamento().getCodigo(),
          pedido.getStatusPagamento().getCodigo());
      
      assertEquals(
          pedidoJpa.getStatusPagamento().getStatus(),
          pedido.getStatusPagamento().getStatus());
      
      
      assertEquals(pedidoJpa.getItensJpa().size(), pedido.getItens().size());
      
      assertEquals(
          (int) pedidoJpa.getItensJpa().get(0).getQuantidade(),
          pedido.getItens().get(0).getQuantidade());

      assertEquals(
          pedidoJpa.getItensJpa().get(0).getProdutoJpa().getPreco(),
          pedido.getItens().get(0).getProduto().getPreco());
    });
    
  }

  @Test
  void deveMapearListPedidoJpaParaListPedido() {

    var pedidoJpa = instanciarPedidoJpaCompleto();
    var listaPedidosJpa = new ArrayList<PedidoJpa>();
    listaPedidosJpa.add(pedidoJpa);

    assertDoesNotThrow(() -> {
      var listaPedidos = PedidoMapper.getListPedido(listaPedidosJpa);

      assertEquals(listaPedidosJpa.size(), listaPedidos.size());
      assertEquals(listaPedidosJpa.get(0).getNumero(), listaPedidos.get(0).getNumero());
    });
    
  }

  // Métodos auxiliares dos testes
  private Pedido instanciarPedidoCompleto() throws BusinessRuleException {

    var cpf = new Cpf(11122233396L);
    
    var cliente = new Cliente(1L, cpf, "Nome do cliente", "email@email.com");

    var produto = new Produto(1L,
        "Nome do produto", null, BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);
    
    var itemPedido = new ItemPedido(produto, 2);

    var listaItens = new ArrayList<ItemPedido>();
    listaItens.add(itemPedido);
    
    var statusPagamento = new StatusPagamento(1L,
        StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, LocalDateTime.now());

    var statusPedido = new StatusPedido(StatusPedidoEnum.RECEBIDO, LocalDateTime.now());

    return new Pedido(1L, cliente, listaItens,
        LocalDateTime.now(), statusPagamento, statusPedido);
  }

  private PedidoJpa instanciarPedidoJpaCompleto() {

    var clienteJpa = new ClienteJpa(1L, 11122233396L, 
        "Nome do cliente", "email@email.com");
    
    var produtoJpa = new ProdutoJpa(1L, "Nome do produto",
        null, BigDecimal.valueOf(22), CategoriaProduto.BEBIDA);

    var itemPedidoJpa = new ItemPedidoJpa(produtoJpa, 2);

    var listaItensJpa = new ArrayList<ItemPedidoJpa>();
    listaItensJpa.add(itemPedidoJpa);

    var statusPagamentoJpa = new StatusPagamentoJpa(1L,
        StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, LocalDateTime.now());

    var statusPedidoJpa = new StatusPedidoJpa(StatusPedidoEnum.RECEBIDO, LocalDateTime.now());

    return new PedidoJpa(1L, clienteJpa, listaItensJpa,
        LocalDateTime.now(), statusPagamentoJpa, statusPedidoJpa);
  }
}
