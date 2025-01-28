package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar a categoria do produto.
 */
public class CategoriaProdutoValidator {

  private CategoriaProdutoValidator() {}

  public static void validar(String categoria) throws BadRequestException {

    if (categoria == null || categoria.trim().isEmpty()) {
      throw new BadRequestException(BadRequestMessage.PROD_CAT_NULL);
    }

    var categoriaEnum = CategoriaProduto.fromString(categoria);
    if (categoriaEnum == null) {
      throw new BadRequestException(BadRequestMessage.PROD_CAT_INV);
    }
  }
}
