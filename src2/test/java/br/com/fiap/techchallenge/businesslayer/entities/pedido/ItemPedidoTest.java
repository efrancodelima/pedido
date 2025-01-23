package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ItemPedidoExceptions;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o value object ItemPedido.
 */
class ItemPedidoTest {

  AutoCloseable closeable;

  @Mock
  private Produto produtoValido;
  
  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConstruirUmItemPedidoComSucesso() {
    assertDoesNotThrow(() -> {
      new ItemPedido(produtoValido, 1);
    });
  }

  @Test
  void deveRetornarOsAtributosDeItemPedido() {
    assertDoesNotThrow(() -> {
      when(produtoValido.getPreco()).thenReturn(BigDecimal.valueOf(10));

      var item = new ItemPedido(produtoValido, 1);
    
      assertEquals(produtoValido, item.getProduto());
      assertEquals(1, item.getQuantidade());
      assertEquals(produtoValido.getPreco(), item.getValorItem());
    });
  }

  @Test
  void produtoNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new ItemPedido(null, 1);
    });

    assertEquals(ItemPedidoExceptions.PRODUTO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void quantidadeNaoPodeSerNula() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new ItemPedido(produtoValido, null);
    });

    assertEquals(ItemPedidoExceptions.QTDE_NULA.getMensagem(), exception.getMessage());
  }

  @Test
  void quantidadeNaoPodeSerMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new ItemPedido(produtoValido, 0);
    });

    assertEquals(ItemPedidoExceptions.QTDE_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void quantidadeNaoPodeSerMaiorQue50() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new ItemPedido(produtoValido, 51);
    });
    
    assertEquals(ItemPedidoExceptions.QTDE_MAX.getMensagem(), exception.getMessage());
  }
    
}
