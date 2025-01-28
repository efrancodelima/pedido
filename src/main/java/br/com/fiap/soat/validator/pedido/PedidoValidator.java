package br.com.fiap.soat.validator.pedido;

import br.com.fiap.soat.dto.controller.ItemPedidoDto;
import br.com.fiap.soat.dto.controller.PedidoDto;
import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRulesException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRulesMessage;
import br.com.fiap.soat.validator.produto.CodigoValidator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Responsável por validar o pedido recebido na requisição ao microsserviço.
 */
@Component
public class PedidoValidator {

  private PedidoValidator() {}

  
  public static void validar(PedidoDto pedidoDto)
      throws BadRequestException, BusinessRulesException {

    CodigoValidator.validar(pedidoDto.getCodigoCliente(), ClienteJpa.class);
    validarItensPedido(pedidoDto.getItens());
  }

  private static void validarItensPedido(List<ItemPedidoDto> listaItens)
      throws BadRequestException, BusinessRulesException {

    if (listaItens == null || listaItens.isEmpty()) {
      throw new BusinessRulesException(BusinessRulesMessage.PED_ITEM_MIN);
    }

    for (ItemPedidoDto item : listaItens) {

      if (item.getQuantidade() == null) {
        throw new BadRequestException(BadRequestMessage.PED_ITEM_QTDE_NULL);
      }

      if (item.getQuantidade() < 1) {
        throw new BadRequestException(BadRequestMessage.PED_ITEM_QTDE_MIN);
      }

      CodigoValidator.validar(item.getCodigoProduto(), ProdutoJpa.class);
    }
  }
}
