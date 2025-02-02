package br.com.fiap.soat.validator.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PedidoValidatorTest {

  @Test
  void deveValidarUmPedidoComSucesso() {
    assertDoesNotThrow(() -> {
      PedidoValidator.validar(getPedidoDto());
    });
  }

  @ParameterizedTest(name = "{index} - Pedido: {0}")
  @MethodSource("listaInvalidaProvider")
  void deveLancarExcecaoSePedidoNaoTiverItem(List<ItemPedidoDto> lista) {
    var pedidoDto = new PedidoDto(1L, lista);

    var excecao = assertThrows(BadRequestException.class, () -> {
      PedidoValidator.validar(pedidoDto);
    });
    assertEquals(BadRequestMessage.PED_ITEM_MIN.getMessage(), excecao.getMessage());
  }

  static Stream<Arguments> listaInvalidaProvider() {
    return Stream.of(
        Arguments.of(null, "Lista de itens nula."),
        Arguments.of(new ArrayList<ItemPedidoDto>(), "Lista de itens vazia.")
    );
  }

  @Test
  void deveLancarExcecaoSeQuantidadeProdutoForNula() {
    var pedidoDto = getPedidoDto();
    pedidoDto.getItens().get(0).setQuantidade(null);

    var excecao = assertThrows(BadRequestException.class, () -> {
      PedidoValidator.validar(pedidoDto);
    });
    assertEquals(BadRequestMessage.PED_ITEM_QTDE_NULL.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoSeQuantidadeProdutoForMenorQueUm() {
    var pedidoDto = getPedidoDto();
    pedidoDto.getItens().get(0).setQuantidade(-1);

    var excecao = assertThrows(BadRequestException.class, () -> {
      PedidoValidator.validar(pedidoDto);
    });
    assertEquals(BadRequestMessage.PED_ITEM_QTDE_MIN.getMessage(), excecao.getMessage());
  }

  private PedidoDto getPedidoDto() {

    var item = new ItemPedidoDto(1L, 1);
    
    var listaItens = new ArrayList<ItemPedidoDto>();
    listaItens.add(item);

    return new PedidoDto(1L, listaItens);
  }
  
}
