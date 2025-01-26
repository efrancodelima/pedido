package br.com.fiap.soat.validator.pedido;

import br.com.fiap.soat.dto.controller.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.PedidoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Responsável por validar o pedido recebido na requisição ao microsserviço.
 */
@Component
public class PedidoValidator {

  private PedidoValidator() {}

  /**
   * valida um objeto do tipo PedidoDto.
   *
   * @param pedidoDto O objeto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   * @throws BadGatewayException Exceção da aplicação lançada durante a validação.
   * @throws NotFoundException Exceção do tipo not found lançada durante a validação.
   */
  public static void validar(PedidoDto pedidoDto) throws BadRequestException {

    validarCodigoCliente(pedidoDto.getCodigoCliente());
    validarItensPedido(pedidoDto.getItens());
  }

  private static void validarCodigoCliente(Long codigoCliente) throws BadRequestException {

    if (codigoCliente != null && codigoCliente < 1) {
      throw new BadRequestException(BadRequestMessage.CODIGO_CLIENTE);
    }
  }

  private static void validarItensPedido(List<ItemPedidoDto> listaItens)
      throws BadRequestException {

    if (listaItens == null || listaItens.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.ITEM_MIN);
    }

    for (ItemPedidoDto item : listaItens) {
      if (item.getQuantidade() == null || item.getQuantidade() < 1) {
        throw new BadRequestException(BadRequestMessage.QTDE_ITEM);
      }

      if (item.getCodigoProduto() == null || item.getCodigoProduto() < 1) {
        throw new BadRequestException(BadRequestMessage.CODIGO_PRODUTO);
      }
    }
  }

}
