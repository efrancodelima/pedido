package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

/**
 * Responsável por validar uma lista de números positivos.
 */
public class NumberValidator {

  private NumberValidator() {}

  public static void validar(List<Long> numeros, BadRequestMessage listNull,
      BadRequestMessage msgNull, BadRequestMessage msgNotPositive) throws BadRequestException {

    if (numeros == null || numeros.isEmpty()) {
      throw new BadRequestException(listNull);
    }

    for (Long numero : numeros) {
      validar(numero, msgNull, msgNotPositive);
    }
  }

  public static void validar(Long numero, BadRequestMessage msgNull,
      BadRequestMessage msgNotPositive) throws BadRequestException {

    if (numero == null && msgNull != null) {
      throw new BadRequestException(msgNull);
    }
    
    if (numero != null && numero < 1 && msgNotPositive != null) {
      throw new BadRequestException(msgNotPositive);
    }
  }

  public static void validar(Integer numero, BadRequestMessage msgNull,
      BadRequestMessage msgNotPositive) throws BadRequestException {
    
    var numLong = numero == null ? null : Long.valueOf(numero);
    validar(numLong, msgNull, msgNotPositive);
  }
}
