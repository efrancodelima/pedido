package br.com.fiap.techchallenge.businesslayer.entities.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.CpfExceptions;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para o Cpf.
 */
class CpfTest {

  private final int numeroValido = 111222333;
  private final byte digitoValido = 96;
  private final long numeroComDigitoValido = 11122233396L;

  @Test
  void deveConstruirUmCpfComSucesso() {
    assertDoesNotThrow(() -> {
      new Cpf(numeroValido, digitoValido);
      new Cpf(numeroComDigitoValido);
    });
  }

  @Test
  void deveRetornarOsAtributosDoCpf() {
    assertDoesNotThrow(() -> {

      Cpf cpf = new Cpf(numeroComDigitoValido);

      assertEquals(cpf.getNumeroSemDigito(), numeroValido);
      assertEquals(cpf.getDigitoVerificador(), digitoValido);
      assertEquals(cpf.pegarNumeroComDigito(), numeroComDigitoValido);
      
    });
  }

  @Test
  void deveConverterCpfParaString() {
    assertDoesNotThrow(() -> {
      
      Cpf cpf = new Cpf(numeroComDigitoValido);
      assertEquals(cpf.toString(), ((Long) numeroComDigitoValido).toString());

    });
  }

  @Test
  void naoDeveAceitarDigitoInvalido() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cpf(111222333, (byte) 44);
    });
    assertEquals(CpfExceptions.DIGITO_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void numeroNaoPodeSerMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cpf(0, (byte) 44);
    });
    assertEquals(CpfExceptions.NUMERO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void numeroNaoPodeTerMaisQue9Digitos() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      var numero = (int) Math.pow(10, 9);
      new Cpf(numero, (byte) 44);
    });
    assertEquals(CpfExceptions.NUMERO_MAX.getMensagem(), exception.getMessage());
  }

  @Test
  void numeroNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cpf(null, (byte) 44);
    });
    assertEquals(CpfExceptions.NUMERO_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void digitoVerificadorNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cpf(111222333, null);
    });
    assertEquals(CpfExceptions.DIGITO_NULO.getMensagem(), exception.getMessage());
  }
 
}
