package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.entity.ClienteJpa;
import br.com.fiap.soat.entity.PedidoJpa;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

/**
 * Responsável por validar uma lista de códigos de entidade.
 */
public class CodigoValidator {

  private CodigoValidator() {}

  public static void validar(List<Long> numeros, Class<?> entidadeJpa)
      throws BadRequestException {

    if (numeros == null || numeros.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.LIST_COD_EMPTY);
    }

    for (Long numero : numeros) {
      validar(numero, entidadeJpa);
    }
  }

  public static void validar(Long numero, Class<?> entidadeJpa) throws BadRequestException {

    var cliente = entidadeJpa.equals(ClienteJpa.class);
    var pedido = entidadeJpa.equals(PedidoJpa.class);
    var produto = entidadeJpa.equals(ProdutoJpa.class);

    BadRequestException exceptionNull = null;
    BadRequestException exceptionNotPositive = null;
    
    if (cliente) {
      exceptionNotPositive = new BadRequestException(BadRequestMessage.PED_COD_MIN);
    
    } else if (pedido) {
      exceptionNull = new BadRequestException(BadRequestMessage.PED_COD_NULL);
      exceptionNotPositive = new BadRequestException(BadRequestMessage.PED_COD_MIN);
    
    } else if (produto) {
      exceptionNull = new BadRequestException(BadRequestMessage.PROD_COD_NULL);
      exceptionNotPositive = new BadRequestException(BadRequestMessage.PROD_COD_MIN);
    
    } else {
      return;
    }
     
    if (numero == null) {
      if (!cliente) {
        throw exceptionNull;
      }
    } else if (numero < 1) {
      throw exceptionNotPositive;
    }
  }
}
