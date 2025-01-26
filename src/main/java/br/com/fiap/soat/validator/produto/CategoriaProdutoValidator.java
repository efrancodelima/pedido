package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.entity.CategoriaProduto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar a categoria do produto.
 */
public class CategoriaProdutoValidator {

  private CategoriaProdutoValidator() {}

  /**
   * Valida a categoria do produto.
   *
   * @param categoria A categoria a ser validada.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(String categoria) throws BadRequestException {

    if (categoria == null || categoria.trim().isEmpty()) {
      throw new BadRequestException(BadRequestMessage.CAT_NULA);
    }

    var categoriaEnum = CategoriaProduto.fromString(categoria);
    if (categoriaEnum == null) {
      throw new BadRequestException(BadRequestMessage.CAT_INV);
    }
  }
}
