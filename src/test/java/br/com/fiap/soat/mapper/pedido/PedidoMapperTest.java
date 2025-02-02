package br.com.fiap.soat.mapper.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ItemPedidoJpa;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ClienteRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PedidoMapperTest {

  AutoCloseable closeable;

  @Mock
  ClienteRepository clienteRepository;

  @Mock
  ItemPedidoMapper itemPedidoMapper;

  @InjectMocks
  PedidoMapper mapper;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveMapearUmPedidoComSucesso() throws Exception {

    // Arrange
    var cliente = getClienteJpa();
    var itemPedido = getItemPedidoJpa();

    when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
    when(itemPedidoMapper.toEntity(any())).thenReturn(itemPedido);

    // Act and assert
    var pedidoJpa = mapper.toEntity(getPedidoDto());

    assertEquals(cliente, pedidoJpa.getCliente());
    assertEquals(1, pedidoJpa.getItens().size());
    assertEquals(itemPedido, pedidoJpa.getItens().get(0));
    assertEquals(itemPedido.getProduto().getPreco(), pedidoJpa.getValor());
  }

  @Test
  void naoDeveLancarExcecaoParaClienteNulo() throws Exception {

    // Arrange
    var pedidoDto = getPedidoDto();
    pedidoDto.setCodigoCliente(null);

    var itemPedido = getItemPedidoJpa();
    when(itemPedidoMapper.toEntity(any())).thenReturn(itemPedido);

    // Act and assert
    assertDoesNotThrow(() -> {
      var pedidoJpa = mapper.toEntity(pedidoDto);
      assertEquals(null, pedidoJpa.getCliente());
    });
  }

  @Test
  void deveLancarExcecaoParaClienteNaoEncontrado() {

    // Arrange
    when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

    // Act and assert
    var excecao = assertThrows(NotFoundException.class, () -> {
      mapper.toEntity(getPedidoDto());
    });

    assertEquals(NotFoundMessage.COD_CLIENTE.getMessage(), excecao.getMessage());
  }

  private PedidoDto getPedidoDto() {

    var item = new ItemPedidoDto(1L, 1);
    
    var listaItens = new ArrayList<ItemPedidoDto>();
    listaItens.add(item);

    return new PedidoDto(1L, listaItens);
  }

  private ItemPedidoJpa getItemPedidoJpa() {
    
    var produtoJpa = new ProdutoJpa(1L, "Nome do produto", null,
        BigDecimal.valueOf(25), CategoriaProduto.BEBIDA);

    return new ItemPedidoJpa(produtoJpa, 1);
  }

  private ClienteJpa getClienteJpa() {
    return new ClienteJpa(1L, 11122233396L, "Nome do cliente", "email@email.com");
  }
}
