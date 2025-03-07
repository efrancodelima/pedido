package br.com.fiap.soat.validator.cliente;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Responsável por validar o número do CPF recebido na requisição ao microsserviço.
 */
public class CpfValidator {

  private CpfValidator() {}

  public static void validar(Long numeroCpf) throws BadRequestException {

    // CPF não pode ser nulo
    if (numeroCpf == null) {
      throw new BadRequestException(BadRequestMessage.CLI_CPF_NULL);
    }
    
    //  Verifica a quantidade de dígitos
    if (Long.toString(numeroCpf).length() < 3 || Long.toString(numeroCpf).length() > 11) {
      throw new BadRequestException(BadRequestMessage.CLI_CPF_INV);
    }

    //  Valida o dígito verificador
    var numeroSemDv = (int) (numeroCpf / 100);
    var digitoVerificador = (byte) (numeroCpf % 100);

    if (digitoVerificador != calcularDigitoCpf(numeroSemDv)) {
      throw new BadRequestException(BadRequestMessage.CLI_CPF_INV);
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
