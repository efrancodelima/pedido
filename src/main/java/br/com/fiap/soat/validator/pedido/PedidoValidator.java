package br.com.fiap.soat.validator.pedido;

import br.com.fiap.soat.dto.controller.request.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.request.PedidoDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.validator.NumberValidator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Responsável por validar o pedido recebido na requisição ao microsserviço.
 */
@Component
public class PedidoValidator {

  private PedidoValidator() {}

  
  public static void validar(PedidoDto pedidoDto) throws BadRequestException {

    NumberValidator.validar(pedidoDto.getCodigoCliente(), null, BadRequestMessage.CLI_COD_MIN);
    validarItensPedido(pedidoDto.getItens());
  }

  private static void validarItensPedido(List<ItemPedidoDto> listaItens)
      throws BadRequestException {

    if (listaItens == null || listaItens.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.PED_ITEM_MIN);
    }

    for (ItemPedidoDto item : listaItens) {

      NumberValidator.validar(item.getQuantidade(),
          BadRequestMessage.PED_ITEM_QTDE_NULL, BadRequestMessage.PED_ITEM_QTDE_MIN);

      NumberValidator.validar(item.getCodigoProduto(),
          BadRequestMessage.PROD_COD_NULL, BadRequestMessage.PROD_COD_MIN);
    }
  }
}
