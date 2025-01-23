package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;

/**
 * Responsável por validar o número do CPF recebido na requisição ao microsserviço.
 */
public class CpfValidator {

  private CpfValidator() {}

  /**
   * Valida o número do CPF.
   *
   * @param cpf O número a ser validado.
   * @throws BadRequestException Exceção do tipo bad request lançada durante a validação.
   */
  public static void validar(Long cpf) throws BadRequestException {
    
    //  Verifica a quantiodade de dígitos
    if (Long.toString(cpf).length() < 3 || Long.toString(cpf).length() > 11) {
      throw new BadRequestException("O CPF informado é inválido.");
    }

    //  Valida o dígito verificador
    var numeroSemDv = (int) (cpf / 100);
    var digitoVerificador = (byte) (cpf % 100);

    if (digitoVerificador != calcularDigitoCpf(numeroSemDv)) {
      throw new BadRequestException("O CPF informado é inválido.");
    }
  }

  private static int calcularDigitoCpf(int numeroSemDv) {
    // Transforma a entrada em um array
    int[] numeroArr = new int[11];
    for (int i = 8; i >= 0; i--) {
      numeroArr[i] = numeroSemDv % 10;
      numeroSemDv /= 10;
    }

    // Calcula o primeiro dígito verificador
    int soma = 0;
    for (int i = 0; i < 9; i++) {
      soma += numeroArr[i] * (10 - i);
    }
    int primeiroDigitoVerificador = 11 - (soma % 11);
    if (primeiroDigitoVerificador >= 10) {
      primeiroDigitoVerificador = 0;
    }
    numeroArr[9] = primeiroDigitoVerificador;

    // Calcula o segundo dígito verificador
    soma = 0;
    for (int i = 0; i < 10; i++) {
      soma += numeroArr[i] * (11 - i);
    }
    int segundoDigitoVerificador = 11 - (soma % 11);
    if (segundoDigitoVerificador >= 10) {
      segundoDigitoVerificador = 0;
    }
    numeroArr[10] = segundoDigitoVerificador;

    return (primeiroDigitoVerificador * 10) + segundoDigitoVerificador;
  }
    
}
