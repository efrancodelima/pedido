package br.com.fiap.soat.validator.produto;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

/**
 * Responsável por validar uma lista com códigos de produtos.
 */
public class CodigoProdutoValidator {

  private CodigoProdutoValidator() {}

  /**
   * Valida uma lista com códigos de produtos.
   *
   * @param codigosProdutos Os códigos dos produtos a serem validados.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(List<Long> codigosProdutos) throws BadRequestException {

    if (codigosProdutos == null || codigosProdutos.isEmpty()) {
      throw new BadRequestException(BadRequestMessage.LIST_COD_EMPTY);
    }

    for (Long codigo : codigosProdutos) {
      validar(codigo);
    }
  }

  /**
   * Valida o código do produto.
   *
   * @param codigoProduto O código do produto a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(Long codigoProduto) throws BadRequestException {

    if (codigoProduto == null) {
      throw new BadRequestException(BadRequestMessage.PROD_COD_NULO);
    }

    if (codigoProduto < 1) {
      throw new BadRequestException(BadRequestMessage.PROD_COD_MIN);
    }
  }
}
