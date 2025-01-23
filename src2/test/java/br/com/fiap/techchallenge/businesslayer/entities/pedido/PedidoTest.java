package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.PedidoExceptions;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para a entidade de negócio Pedido.
 */
class PedidoTest {

  AutoCloseable closeable;

  @Mock
  private Cliente cliente;

  @Mock
  private ItemPedido item;
  private List<ItemPedido> listaItens;

  @Mock
  private StatusPagamento statusPagamento;

  @Mock
  private StatusPedido statusPedido;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    
    listaItens = new ArrayList<>();
    listaItens.add(item);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConstruirUmNovoPedido() {
    assertDoesNotThrow(() -> {
      new Pedido(cliente, listaItens);
    });
  }

  @Test
  void deveConstruirUmPedidoDoBancoDeDados() {
    assertDoesNotThrow(() -> {
      new Pedido(1L, cliente, listaItens, LocalDateTime.now(), statusPagamento, statusPedido);
    });
  }

  @Test
  void deveRetornarOsAtributosDoPedido() {
    
    assertDoesNotThrow(() -> {

      var dataHoraCheckout = LocalDateTime.now();

      var pedido = new Pedido(1L, cliente,
          listaItens, dataHoraCheckout, statusPagamento, statusPedido);

      assertEquals(Long.valueOf(1), pedido.getNumero());
      assertEquals(cliente, pedido.getCliente());
      assertEquals(listaItens, pedido.getItens());
      assertEquals(dataHoraCheckout, pedido.getDataHoraCheckout());
      assertEquals(statusPagamento, pedido.getStatusPagamento());
      assertEquals(statusPedido, pedido.getStatusPedido());
    });
  }

  @Test
  void clienteDevePermitirNulo() {
    assertDoesNotThrow(() -> {
      new Pedido(null, listaItens);
      new Pedido(1L, null, listaItens, LocalDateTime.now(), statusPagamento, statusPedido);
    });
  }

  @Test
  void listaDeItensNaoPodeSerNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(cliente, null);
    });

    assertEquals(PedidoExceptions.ITENS_NULO.getMensagem(), exception.getMessage());

    exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, null, LocalDateTime.now(), statusPagamento, statusPedido);
    });

    assertEquals(PedidoExceptions.ITENS_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void listaDeItensNaoPodeSerVazia() {
    List<ItemPedido> listaVazia = new ArrayList<>();

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(cliente, listaVazia);
    });

    assertEquals(PedidoExceptions.ITENS_VAZIO.getMensagem(), exception.getMessage());

    exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaVazia, LocalDateTime.now(), statusPagamento, statusPedido);
    });

    assertEquals(PedidoExceptions.ITENS_VAZIO.getMensagem(), exception.getMessage());
  }

  @Test
  void nenhumItemPodeSerNulo() {
    listaItens.add(null);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(cliente, listaItens);
    });

    assertEquals(PedidoExceptions.ITEM_NULO.getMensagem(), exception.getMessage());

    exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, LocalDateTime.now(), statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.ITEM_NULO.getMensagem(), exception.getMessage());
  }
    
  @Test
  void numeroNaoPodeSerNuloNoConstrutorAllArgs() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(null, cliente, listaItens, LocalDateTime.now(), statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.NUMERO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void numeroNaoPodeSerMenorQueUmNoConstrutorAllArgs() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(0L, cliente, listaItens, LocalDateTime.now(), statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.NUMERO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void dataHoraCheckoutNaoPodeSerNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, null, statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem(), exception.getMessage());
  }

  @Test
  void dataHoraCheckoutNaoPodeSerMenorQue01jan2020() {
    var dataHoraInvalida = LocalDateTime.of(2019, 12, 31, 0, 0);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, dataHoraInvalida, statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void dataHoraCheckoutNaoPodeSerMaiorQueDataAtual() {
    var tomorrow = LocalDateTime.now().plusDays(1);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, tomorrow, statusPagamento, statusPedido);
    });
    
    assertEquals(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem(), exception.getMessage());
  }

  @Test
  void statusPagamentoNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, LocalDateTime.now(), null, statusPedido);
    });
    
    assertEquals(PedidoExceptions.PAGAMENTO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void statusPedidoNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Pedido(1L, cliente, listaItens, LocalDateTime.now(), statusPagamento, null);
    });
    
    assertEquals(PedidoExceptions.STATUS_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void deveRetornarValorPedido() {
    int valor = 50;
    when(item.getValorItem()).thenReturn(BigDecimal.valueOf(valor));

    assertDoesNotThrow(() -> {

      var pedido = new Pedido(cliente, listaItens);
      assertEquals(BigDecimal.valueOf(valor), pedido.getValorPedido());

    });
  }

  @Test
  void deveAtualizarStatusPagamento() {
    var pedido = getPedido(false);
    var novoStatusPagamento = getStatusPagamento(true);

    assertDoesNotThrow(() -> {
      pedido.setStatusPagamento(novoStatusPagamento);
    });
  }

  @Test
  void naoDevePermitirAtualizarPagamentoJaAprovado() {
    var pedidoPago = getPedido(true);
    
    var exception = assertThrows(BusinessRuleException.class, () -> {
      pedidoPago.setStatusPagamento(statusPagamento);
    });

    assertEquals(PedidoExceptions.PAGAMENTO_JA_APROVADO.getMensagem(), exception.getMessage());
  }

  @Test
  void statusPagamentoNaoPodeSerNuloAoAtualizarPedido() {
    var pedido = getPedido(false);

    var exception = assertThrows(BusinessRuleException.class, () -> {
      pedido.setStatusPagamento(null);
    });

    assertEquals(PedidoExceptions.PAGAMENTO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void deveAtualizarStatusPedido() {
    when(statusPedido.getStatus()).thenReturn(StatusPedidoEnum.RECEBIDO);
    var pedido = getPedido(true);

    assertDoesNotThrow(pedido::atualizarStatusPedido);
  }

  @Test
  void naoDeveAtualizarStatusRecebidoDePedidoNaoPago() {
    when(statusPedido.getStatus()).thenReturn(StatusPedidoEnum.RECEBIDO);
    var pedido = getPedido(false);

    var exception = assertThrows(BusinessRuleException.class, pedido::atualizarStatusPedido);
    assertEquals(PedidoExceptions.PAGAMENTO_NAO_APROVADO.getMensagem(), exception.getMessage());
  }

  @Test
  void naoDeveAtualizarStatusDePedidoFinalizado() {
    when(statusPedido.getStatus()).thenReturn(StatusPedidoEnum.FINALIZADO);
    var pedido = getPedido(true);

    var exception = assertThrows(BusinessRuleException.class, pedido::atualizarStatusPedido);
    assertEquals(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem(), exception.getMessage());
  }

  // Métodos auxiliares dos testes
  Pedido getPedido(boolean pago) {
    var pagamento = pago ? getStatusPagamento(true) : getStatusPagamento(false);
    
    try {
      return new Pedido(1L, cliente, listaItens, LocalDateTime.now(), pagamento, statusPedido);
    } catch (BusinessRuleException e) {
      return null;
    }
  }

  StatusPagamento getStatusPagamento(boolean pago) {
    var status = pago ? StatusPagamentoEnum.APROVADO : StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;

    try {
      return new StatusPagamento(1L, status, LocalDateTime.now());
    } catch (BusinessRuleException e) {
      return null;
    }
  }
}
