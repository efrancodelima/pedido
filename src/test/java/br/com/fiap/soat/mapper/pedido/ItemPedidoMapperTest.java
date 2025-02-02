package br.com.fiap.soat.mapper.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ItemPedidoMapperTest {

  AutoCloseable closeable;

  @Mock
  ProdutoRepository repositoryMock;

  @InjectMocks
  ItemPedidoMapper mapper;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveMapearUmItemComSucesso() throws Exception {

    // Arrange
    var item = new ItemPedidoDto(1L, 1);
    var produto = new ProdutoJpa(1L, "Nome do produto", null,
        BigDecimal.valueOf(25), CategoriaProduto.BEBIDA);

    when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(produto));

    // Act
    var itemJpa = mapper.toEntity(item);

    // Assert
    assertEquals(item.getQuantidade(), itemJpa.getQuantidade());
    assertEquals(produto.getCodigo(), itemJpa.getProduto().getCodigo());
    assertEquals(produto.getNome(), itemJpa.getProduto().getNome());
    assertEquals(produto.getDescricao(), itemJpa.getProduto().getDescricao());
    assertEquals(produto.getPreco(), itemJpa.getProduto().getPreco());
    assertEquals(produto.getCategoria(), itemJpa.getProduto().getCategoria());
  }

  @Test
  void deveLancarExcecaoParaProdutoNaoEncontrado() {

    // Arrange
    var item = new ItemPedidoDto(1L, 1);
    when(repositoryMock.findById(anyLong())).thenReturn(Optional.empty());

    // Act and assert
    var excecao = assertThrows(NotFoundException.class, () -> {
      mapper.toEntity(item);
    });

    assertEquals(NotFoundMessage.COD_PRODUTO.getMessage(), excecao.getMessage());
  }
  
}
